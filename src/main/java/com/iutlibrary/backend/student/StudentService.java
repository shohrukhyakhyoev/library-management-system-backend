package com.iutlibrary.backend.student;

import com.iutlibrary.backend.appUserDetails.Account;
import com.iutlibrary.backend.appUserDetails.AccountService;
import com.iutlibrary.backend.bookStuff.book.Book;
import com.iutlibrary.backend.bookStuff.book.BookService;
import com.iutlibrary.backend.bookStuff.bookItem.BookItem;
import com.iutlibrary.backend.bookStuff.bookItem.BookItemService;
import com.iutlibrary.backend.bookStuff.bookRequest.RequestBook;
import com.iutlibrary.backend.bookStuff.bookRequest.RequestBookService;
import com.iutlibrary.backend.bookStuff.bookReservation.BookReservation;
import com.iutlibrary.backend.bookStuff.bookReservation.BookReservationService;
import com.iutlibrary.backend.bookStuff.bookReserve.BookReserveRequest;
import com.iutlibrary.backend.bookStuff.bookReserve.BookReserveService;
import com.iutlibrary.backend.email.EmailSender;
import com.iutlibrary.backend.exception.ApiRequestException;
import com.iutlibrary.backend.fine.FineService;
import com.iutlibrary.backend.utility.enums.AppUserRole;
import com.iutlibrary.backend.utility.enums.BookStatus;
import com.iutlibrary.backend.utility.enums.ReservationStatus;
import com.iutlibrary.backend.utility.Constants;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

import static org.assertj.core.util.Strings.concat;

/**
 * Serves as a service layer for requests associated with manipulation over student's data.
 */
@Service
@Transactional
@AllArgsConstructor
public class StudentService {

    /**
     * @field bookService used to interact with a book's service.
     * @field bookItemService used to interact with a book item's service.
     * @field bookReservationService used to interact with a book reservation's service.
     * @field requestService used to interact with a book request's service.
     * @field fineService used to interact with a fine's service.
     * @field emailSender used to send an email.
     * @field accountService used to interact with a account's service.
     * @field bookReserveService used to interact with a reserve request coming from a student.
     */
    @Autowired
    private final BookReservationService bookReservationService;
    @Autowired
    private final BookReserveService bookReserveService;
    @Autowired
    private final BookItemService bookItemService;
    @Autowired
    private final RequestBookService requestBookService;
    @Autowired
    private final FineService fineService;
    @Autowired
    private final AccountService accountService;
    @Autowired
    private final EmailSender emailSender;
    @Autowired
    private final BookService bookService;

    /**
     * Reserves a book for a student.
     * System firstly checks following things and if at least one condition is satisfied,
     * system returns an error message:
     *
     * if student has a fine.
     * if student  has already made a request this book.
     * if student  has already made a reserve request for this book.
     * if student already has a pending reservation with this book.
     * if student has already borrowed 5 books.
     *
     * If no condition is met above, system then checks whether this book is available or not.
     * If it is, then it creates a new book reservation of the asked book item for this student
     * and updates the reserved book item's status to RESERVED.
     *
     * If book is not available, then system creates a request record for a student and shows a
     * message to a student saying that a book item is not available for now, but once it is,
     * student will be informed via email.
     *
     * @param book Book object representing all details of a book.
     * @param studentId a string value representing a student's id.
     * @return ResponseEntity object.
     */
    public ResponseEntity<Object> reserveBook(Book book, String studentId){

        String response;

        if (!fineService.getAllFinesOfStudent(studentId).isEmpty()){
            throw new ApiRequestException("You have fine. Please, pay them first.");
        }

        if (requestBookService.findByStudentIdAndISBN(studentId, book.getISBN()).isPresent()){
            throw new ApiRequestException("You have already requested this book.");
        }

        if (bookReserveService.findBookRequestByISBN(book.getISBN(),
                studentId).isPresent()){
            throw new ApiRequestException("You have already reserved this book.");
        }

        if (bookReservationService.findByISBN(book.getISBN(),
                studentId, ReservationStatus.COMPLETED).isPresent()){
            throw new ApiRequestException("You already have reservation status with this book.");
        }

        List<BookItem> searchedBookItems = bookItemService.findTopByISBNAndStatus(
                book.getISBN(), BookStatus.AVAILABLE);

        if (!searchedBookItems.isEmpty()) {
            int totalActiveBooks = bookReservationService.
                    findInUseById(studentId, ReservationStatus.COMPLETED).size();
            if (totalActiveBooks >= Constants.MAX_BOOKS_ISSUED_TO_A_USER) {
                throw new ApiRequestException("You have already reserved the maximum allowed number of book items.");
            }

            searchedBookItems.get(0).setStatus(BookStatus.REQUESTED);
            bookReserveService.addNewBookReserveRequest(new BookReserveRequest(studentId,
                    book.getTitle(), book.getISBN(), searchedBookItems.get(0).getBarcode()));

            response = "You have successfully reserved book.";
        } else {
            requestBookService.addNewRequest(new RequestBook(studentId, book.getISBN()));
            response = "This book is not available now. You will be " +
                    "informed by notification once the book is available again.";
        }

        bookService.updateAvailabilityStatus(searchedBookItems.get(0).getISBN());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Reports a loss of book borrowed by a student.
     *
     * System terminates a reservation of that book and registers a fine if status is overdue.
     * Then system updates status of reservation to COMPLETED and registers a fine for a lost of
     * a book.
     *
     * Student is then notified about the fines registered to them. In addition, email notification
     * is sent to librarians informing about a book lost.
     *
     * Finally, the borrowed book item's status is updated to LOST.
     *
     * @param book a Book object representing book's details.
     * @param studentId a string value object representing a student's id.
     * @return ResponseEntity object.
     */
    public ResponseEntity<Object> reportLost(Book book, String studentId) {

        // todo make sure <> in query works
        BookReservation bookReservation = bookReservationService.findByISBN(
                book.getISBN(), studentId, ReservationStatus.COMPLETED)
                .orElseThrow(() -> new ApiRequestException("Reservation with given parameters is not found in the database."));

        BookItem bookItem = bookItemService.findByBarcode(bookReservation.getBarcode())
                .orElseThrow(()-> new ApiRequestException("BookItem issued to you cannot be found in the database."));

        Account student = accountService.findByMemberId(bookReservation.getStudentId());

        if (bookReservation.getStatus().equals(ReservationStatus.OVERDUE)){
            fineService.addNewFine(bookReservation.getStudentId(),
                            "Overdue",
                                    bookReservation.getISBN(),
                                    bookReservation.getBarcode(),
                                    Constants.FINE_FOR_OVERDUE,
                                    bookReservation.getTitle());

            emailSender.send(student.getEmail(), "Dear Student, \n\nYou submitted " + bookReservation.getTitle() + " book within Overdue." +
                    " Thus, a fine of " + Constants.FINE_FOR_OVERDUE + " is registered to you. \n\nRegards, \n IUT Library" , "New Fine");

        }

        bookReservationService.updateStatus(ReservationStatus.COMPLETED, bookReservation.getBarcode());

        fineService.addNewFine(
                        bookReservation.getStudentId(),
                        "Lost",
                        bookReservation.getISBN(),
                        bookReservation.getBarcode(),
                        Constants.FINE_FOR_LOST,
                        bookReservation.getTitle());

        emailSender.send(student.getEmail(), "Dear Student, \n\nYou reported the lost of " + bookReservation.getTitle() + "." +
                " Thus, a fine of " + Constants.FINE_FOR_LOST + " is registered to you. \n\nRegards,\nIUT Library" , "New Fine");

        bookItem.setStatus(BookStatus.LOST);
        bookService.updateAvailabilityStatus(book.getISBN());


        List<Account> librarians = accountService.findAllByRole(AppUserRole.LIBRARIAN);
        librarians.forEach(librarian ->
                emailSender.send(librarian.getEmail(), "Dear Librarian, \n\n" + "Student with id " + bookReservation.getStudentId() +
                        " has reported the lost of the issued book. Here are book details: \n " + "Book ISBN: " +bookReservation.getISBN() +
                        "\nBookItem barcode: " + bookReservation.getBarcode() + "\nRegards,\nIUT Library", "Student Book Lost Report"));


        return new ResponseEntity<>("Your report has been successfully sent." +
                "You are fined to " + Constants.FINE_FOR_LOST + " sums.", HttpStatus.OK);
    }


    /**
     * Sends a message coming from a student asking something from a librarian.
     * System sends message to all librarians.
     *
     * @param email a string value representing a receiver's mail address.
     * @param message a string value representing message itself.
     * @param topic a string value representing topic of a message.
     * @return ResponseEntity object.
     */
    public ResponseEntity<Object> askLibrarian(String topic, String message, String email) {
        List<Account> librarians = accountService.findAllByRole(AppUserRole.LIBRARIAN);
        librarians.forEach(librarian ->
                emailSender.send(librarian.getEmail(), message, concat(topic + ": from " + email)));

        return new ResponseEntity<>("Email has been successfully sent!", HttpStatus.OK);
    }
}
