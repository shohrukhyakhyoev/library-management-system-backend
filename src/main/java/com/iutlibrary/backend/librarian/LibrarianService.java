package com.iutlibrary.backend.librarian;


import com.iutlibrary.backend.appUserDetails.Account;
import com.iutlibrary.backend.appUserDetails.AccountService;
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
import com.iutlibrary.backend.utility.enums.BookStatus;
import com.iutlibrary.backend.utility.enums.ReservationStatus;
import com.iutlibrary.backend.utility.Constants;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.*;

@Service
@Transactional
@AllArgsConstructor
public class LibrarianService {

    @Autowired
    private final BookItemService bookItemService;
    @Autowired
    private final BookReservationService bookReservationService;
    @Autowired
    private final BookReserveService bookReserveService;
    @Autowired
    private final RequestBookService requestBookService;
    @Autowired
    private final FineService fineService;
    @Autowired
    private final EmailSender emailSender;
    @Autowired
    private final AccountService accountService;

    public ResponseEntity<Object> issueBook(String decision, BookReserveRequest bookReserveRequest) {
        String response;

        BookItem bookItem = bookItemService.findByBarcode(bookReserveRequest.getBarcode())
                .orElseThrow(()-> new ApiRequestException("Reserved book item cannot be found in the database."));


        if (Objects.equals(decision, "Accept")) {

            bookReservationService.addNewReservation(new BookReservation(
                    bookReserveRequest.getBookTitle(),
                    bookReserveRequest.getISBN(),
                    bookReserveRequest.getBarcode(),
                    bookReserveRequest.getStudentId(),
                    ReservationStatus.PENDING,
                    LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth()),
                    Constants.DEADLINE));

            bookItem.setStatus(BookStatus.RESERVED);

            response = bookReserveRequest.getBookTitle() +
                    " book is successfully issued to " +
                    "student with ID: " + bookReserveRequest.getStudentId();

        } else {
            List<Optional<RequestBook>> requestBook = requestBookService.
                    findRequestBookByISBN(bookReserveRequest.getISBN());

            if (!requestBook.isEmpty()) {
                bookReserveService.addNewBookReserveRequest(new BookReserveRequest(requestBook.get(0).get().getStudentId(),
                        bookReserveRequest.getBookTitle(), bookReserveRequest.getISBN(), bookReserveRequest.getBarcode()));

                // todo check if delete works
                requestBookService.delete(requestBook.get(0).get());

                Account student = accountService.findByMemberId(requestBook.get(0).get().getStudentId());
                emailSender.send(student.getEmail(), "Dear Student,\n" + bookReserveRequest.getBookTitle() + " is available now." +
                        " As you have requested this book before, it is reserved for you." + "\n\nRegards, \nIUT Library", "Available Book.");


                response = "Issue of " + bookReserveRequest.getBookTitle() +
                        " to student with ID: " + bookReserveRequest.getStudentId() + " is denied. The book is" +
                        " reserved to student with ID: " + requestBook.get(0).get().getStudentId() + ".";
            } else {
                bookItem.setStatus(BookStatus.AVAILABLE);

                response = "Issue of " + bookReserveRequest.getBookTitle() +
                        " to student with ID: " + bookReserveRequest.getStudentId() + " is denied. The book status is" +
                        " updated to AVAILABLE.";
            }
        }

        // todo check if delete works
        bookReserveService.delete(bookReserveRequest);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<Object> returnBook(BookReservation bookReservation) {

        BookItem bookItem = bookItemService.findByBarcode(bookReservation.getBarcode())
                .orElseThrow(()-> new ApiRequestException("Book item cannot be found in the database."));

        String response;

        if (bookReservation.getStatus().equals(ReservationStatus.OVERDUE)) {
            fineService.addNewFine(
                    bookReservation.getStudentId(),
                    "Overdue",
                    bookReservation.getISBN(),
                    bookReservation.getBarcode(),
                    Constants.FINE_FOR_OVERDUE
                    );

            Account student = accountService.findByMemberId(bookReservation.getStudentId());
            emailSender.send(student.getEmail(), "Dear Student, \n\nYou submitted " + bookReservation.getTitle() + " book within Overdue." +
                    " Thus, a fine of " + Constants.FINE_FOR_OVERDUE + " is registered to you. \n\nRegards, \n IUT Library" , "New Fine");

            response = "Student with ID: " + bookReservation.getStudentId() + " has " +
                    "returned book: " + bookReservation.getTitle() + " with OVERDUE. Fine is calculated (" + Constants.FINE_FOR_LOST + " sums) " +
                    "and is informed to student by notification";
        } else {
            response = "Student with ID: " + bookReservation.getStudentId() +
                    " has returned book: " + bookReservation.getTitle() + " in due time.";
        }

        bookReservation.setStatus(ReservationStatus.COMPLETED);

        List<Optional<RequestBook>> requestBook = requestBookService.
                findRequestBookByISBN(bookReservation.getISBN());


        if (!requestBook.isEmpty()) {
            bookReserveService.addNewBookReserveRequest(new BookReserveRequest(requestBook.get(0).get().getStudentId(),
                    bookReservation.getTitle(), bookReservation.getISBN(), bookReservation.getBarcode()));

            bookItem.setStatus(BookStatus.REQUESTED);
            Account student = accountService.findByMemberId(requestBook.get(0).get().getStudentId());

            emailSender.send(student.getEmail(), "Dear Student,\n" + bookReservation.getTitle() + " is available now." +
                    " As you have requested this book before, it is reserved for you." + "\n\nRegards, \nIUT Library", "Available Book.");

            response += " The book is reserved to student with ID: " + requestBook.get(0).get().getStudentId();

            // todo check if delete works
            requestBookService.delete(requestBook.get(0).get());

        } else {
            bookItem.setStatus(BookStatus.AVAILABLE);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
