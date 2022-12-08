package com.iutlibrary.backend.bookStuff.bookReservation;


import com.iutlibrary.backend.exception.ApiRequestException;
import com.iutlibrary.backend.utility.enums.ReservationStatus;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Serves as a controller for requests associated with manipulation over a book reservation's data.
 * It listens to requests and then calls a certain function from the service layer.
 * Each method is mapped to a certain request type.
 *
 * @author shohrukhyakhyoev
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/reservation")
public class BookReservationController {
    /**
     * @field bookReservationService an injected data field with the type of BookService.
     * Each method mapped to a particular client-request call a certain function from
     * service layer (BookService) using this data field.
     */
    private final BookReservationService bookReservationService;

    /**
     * Listens to a request asking to get all pending book reservations.
     *
     * @return list of BookReservation objects.
     */
    @GetMapping("/all")
    public List<BookReservation> getAllPending(){
        return bookReservationService.findAllPending();
    }


    /**
     * Listens to a request asking to get one book reservation details.
     *
     * @param isbn represents a book's isbn.
     * @param studentId represents a student's id.
     * @param status represents a book reservation's status.
     * @return BookReservation object.
     * @throws ApiRequestException
     */
    @GetMapping("/one")
    public BookReservation getOneReservation(@RequestParam("ISBN") Long isbn,
                                             @RequestParam("studentId") String studentId,
                                             @RequestParam("status") ReservationStatus status){
        return bookReservationService.findByISBN(isbn, studentId, status)
                .orElseThrow(()->new ApiRequestException("Reservation with given parameters is not found in the database."));
    }


    /**
     * Listens to a request asking to get all reservations of one student.
     *
     * @param studentId represents a student's id.
     * @return list of BookReservation objects.
     */
    @GetMapping("/student/all")
    public List<BookReservation> getAllOfStudent(@RequestParam("studentId") String studentId){
        return bookReservationService.findAllOfStudent(studentId);
    }

    /**
     * Listens to a request asking to search for book reservations of a particular status.
     *
     * @param status represents a book reservation's status.
     * @return list of BookReservation objects.
     */
    @GetMapping("/search/status")
    public List<BookReservation> getByStatus(@RequestParam("status") ReservationStatus status){
        return bookReservationService.findByStatus(status);
    }

    /**
     * Listens to a request asking to search for a book reservations by a barcode.
     *
     * @param barcode represents a book item's barcode.
     * @return list of BookReservation objects.
     */
    @GetMapping("/search/barcode")
    public BookReservation getByBarcode(@RequestParam("barcode") Long barcode){
        return bookReservationService.findByBarcode(barcode);
    }

    /**
     * Listens to a request asking to search for book reservations by title.
     *
     * @param title represents a book's title.
     * @return list of BookReservation objects.
     */
    @GetMapping("/search/title")
    public List<BookReservation> getByTitle(@RequestParam("title") String title){
        return bookReservationService.findByTitle(title);
    }

    /**
     * Listens to a request asking to get book reservations of a particular student of status of active.

     * @param studentId represents a student's id.
     * @return list of BookReservation objects.
     */
    @GetMapping("/dashboard/active")
    public List<BookReservation> getActiveReservationsOfStudent(@RequestParam("studentId") String studentId){
        return bookReservationService.findActiveById(studentId);
    }

    /**
     * Listens to a request asking to search for book reservations of an overdue status.
     *
     * @param studentId represents a student's id.
     * @return list of BookReservation objects.
     */
    @GetMapping("/search/overdue")
    public List<BookReservation> getOverdueReservationsOfStudent(@RequestParam("studentId") String studentId){
        return bookReservationService.findOverdueById(studentId);
    }

    /**
     * Listens to a request asking to search for book reservations of in-use books by a particular student.
     *
     * @param studentId represents a student's id.
     * @return list of BookReservation objects.
     */
    @GetMapping("/student/active")
    public List<BookReservation> getAllInUseOfStudent(@RequestParam("studentId") String studentId){
        return bookReservationService.findInUseById(studentId, ReservationStatus.COMPLETED);
    }

    /**
     * Listens to a request asking to search for book reservations of a completed status.
     *
     * @param studentId represents a student's id.
     * @return list of BookReservation objects.
     */
    @GetMapping("/student/history")
    public List<BookReservation> getCompletedReservationsOfStudent(@RequestParam("studentId") String studentId){
        return bookReservationService.findCompletedById(studentId);
    }

    /**
     * Listens to a request asking to search for book reservations by title.
     *
     * @param title represents a book's title.
     * @param studentId represents student's id.
     * @return list of BookReservation objects.
     */
    @GetMapping("/student/title")
    public List<BookReservation> getAllOfStudentByTitle(@RequestParam("title") String title,
                                                        @RequestParam("studentId") String studentId){
        return bookReservationService.findByTitleOfStudent(studentId, title);
    }

}
