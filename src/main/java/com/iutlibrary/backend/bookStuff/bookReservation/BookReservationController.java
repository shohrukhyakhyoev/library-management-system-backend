package com.iutlibrary.backend.bookStuff.bookReservation;


import com.iutlibrary.backend.exception.ApiRequestException;
import com.iutlibrary.backend.utility.enums.ReservationStatus;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/reservation")
public class BookReservationController {
    private final BookReservationService bookReservationService;

    // for return in librarian
    @GetMapping("/all")
    public List<BookReservation> getAllPending(){
        return bookReservationService.findAllPending();
    }


    // details of one reservation --> when librarian clicks the row in return
    @GetMapping("/one")
    public BookReservation getOneReservation(@RequestParam("ISBN") Long isbn,
                                             @RequestParam("studentId") String studentId,
                                             @RequestParam("status") ReservationStatus status){
        return bookReservationService.findByISBN(isbn, studentId, status)
                .orElseThrow(()->new ApiRequestException("Reservation with given parameters is not found in the database."));
    }


    // when id of student is clicked in students block of librarian's dashboard
    @GetMapping("/student/all")
    public List<BookReservation> getAllOfStudent(@RequestParam("studentId") String studentId){
        return bookReservationService.findAllOfStudent(studentId);
    }

    // search by filter: status: active & overdue in general of all students
    @GetMapping("/search")
    public List<BookReservation> getByStatus(@RequestParam("status") ReservationStatus status){
        return bookReservationService.findByStatus(status);
    }

    // search by filter: barcode
    @GetMapping("/search")
    public BookReservation getByBarcode(@RequestParam("barcode") Long barcode){
        return bookReservationService.findByBarcode(barcode);
    }

    // search by filter: title
    @GetMapping("/search")
    public List<BookReservation> getByTitle(@RequestParam("title") String title){
        return bookReservationService.findByTitle(title);
    }

    // for table in students' block in librarian dashboard
    @GetMapping("/search/active")
    public List<BookReservation> getActiveReservationsOfStudent(@RequestParam("studentId") String studentId){
        return bookReservationService.findActiveById(studentId);
    }

    // for table in students' block in librarian dashboard
    @GetMapping("/search/overdue")
    public List<BookReservation> getOverdueReservationsOfStudent(@RequestParam("studentId") String studentId){
        return bookReservationService.findOverdueById(studentId);
    }

    // active books of student for profile dashboard
    @GetMapping("/student/active")
    public List<BookReservation> getAllInUseOfStudent(@RequestParam("studentId") String studentId){
        return bookReservationService.findInUseById(studentId, ReservationStatus.COMPLETED);
    }

    // history books of student and for students block search by status completed
    @GetMapping("/student/history")
    public List<BookReservation> getCompletedReservationsOfStudent(@RequestParam("studentId") String studentId){
        return bookReservationService.findCompletedById(studentId);
    }

    // for students block search by title
    @GetMapping("/student/title")
    public List<BookReservation> getAllOfStudentByTitle(@RequestParam("title") String title,
                                                        @RequestParam("studentId") String studentId){
        return bookReservationService.findByTitleOfStudent(studentId, title);
    }

}
