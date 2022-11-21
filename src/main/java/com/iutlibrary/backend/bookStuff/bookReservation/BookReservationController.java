package com.iutlibrary.backend.bookStuff.bookReservation;


import com.iutlibrary.backend.exception.ApiRequestException;
import com.iutlibrary.backend.utility.enums.ReservationStatus;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class BookReservationController {
    private final BookReservationService bookReservationService;

    // for return in librarian
    @GetMapping("/reservation/all")
    public List<BookReservation> getAllPending(){
        return bookReservationService.findAllPending();
    }


    // details of one reservation --> when librarian clicks the row in return
    @GetMapping("/reservation/one")
    public BookReservation getOneReservation(@RequestParam("ISBN") Long isbn,
                                             @RequestParam("studentId") String studentId,
                                             @RequestParam("status") ReservationStatus status){
        return bookReservationService.findByISBN(isbn, studentId, status)
                .orElseThrow(()->new ApiRequestException("Reservation with given parameters is not found in the database."));
    }

    // search by status: click combo box
    @GetMapping("/reservation/status/")
    public List<BookReservation> getByStatus(@RequestParam("status") ReservationStatus status){
        return bookReservationService.findByStatus(status);
    }

    // search by filter: type in search bar
    @GetMapping("/reservation/barcode")
    public BookReservation getByBarcode(@RequestParam("barcode") Long barcode){
        return bookReservationService.findByBarcode(barcode);
    }

    // active books of student
    @GetMapping("/reservation/student/active")
    public List<BookReservation> getActiveReservationsOfStudent(@RequestParam("studentId") String studentId){
        return bookReservationService.findActiveById(studentId, ReservationStatus.COMPLETED);
    }

    // history books of student
    @GetMapping("/reservation/student/history")
    public List<BookReservation> getCompletedReservationsOfStudent(@RequestParam("studentId") String studentId){
        return bookReservationService.findCompletedById(studentId);
    }

}
