package com.iutlibrary.backend.student;

import com.iutlibrary.backend.appUserDetails.Account;
import com.iutlibrary.backend.appUserDetails.AccountService;
import com.iutlibrary.backend.bookStuff.book.Book;
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

@Service
@Transactional
@AllArgsConstructor
public class StudentService {

    private final BookReservationService bookReservationService;
    private final BookReserveService bookReserveService;
    private final BookItemService bookItemService;
    private final RequestBookService requestBookService;
    private final FineService fineService;
    private final AccountService accountService;
    private final EmailSender emailSender;


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
                    findActiveById(studentId, ReservationStatus.COMPLETED).size();
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

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

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
                                    Constants.FINE_FOR_OVERDUE);

            emailSender.send(student.getEmail(), "Dear Student, \n\nYou submitted " + bookReservation.getTitle() + " book within Overdue." +
                    " Thus, a fine of " + Constants.FINE_FOR_OVERDUE + " is registered to you. \n\nRegards, \n IUT Library" , "New Fine");

        }

        bookReservation.setStatus(ReservationStatus.COMPLETED);

        fineService.addNewFine(
                        bookReservation.getStudentId(),
                        "Lost",
                        bookReservation.getISBN(),
                        bookReservation.getBarcode(),
                        Constants.FINE_FOR_LOST);

        emailSender.send(student.getEmail(), "Dear Student, \n\nYou reported the lost of " + bookReservation.getTitle() + "." +
                " Thus, a fine of " + Constants.FINE_FOR_LOST + " is registered to you. \n\nRegards, \n IUT Library" , "New Fine");

        bookItem.setStatus(BookStatus.LOST);

        List<Account> librarians = accountService.findAllByRole(AppUserRole.LIBRARIAN);
        librarians.forEach(librarian ->
                emailSender.send(librarian.getEmail(), "Dear Librarian, \n\n" + "Student with id " + bookReservation.getStudentId() +
                        " has reported the lost of the issued book. Here are book details: \n " + "Book ISBN: " +bookReservation.getISBN() +
                        "\nBookItem barcode: " + bookReservation.getBarcode() + "\nRegards, \nIUT Library", "Student Book Lost Report"));


        return new ResponseEntity<>("Your report has been successfully sent." +
                "You are fined to " + Constants.FINE_FOR_LOST + " sums.", HttpStatus.OK);
    }


    // test if this works with message type of String.
    public ResponseEntity<Object> askLibrarian(String topic, String message, String email) {
        List<Account> librarians = accountService.findAllByRole(AppUserRole.LIBRARIAN);
        librarians.forEach(librarian ->
                emailSender.send(librarian.getEmail(), message, topic + ": from " + email));

        return new ResponseEntity<>("Email has been successfully sent!", HttpStatus.OK);
    }
}
