package com.iutlibrary.backend.student;

import com.iutlibrary.backend.bookStuff.book.Book;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * Serves as a controller for requests coming from the student associated with manipulation
 * over a book's data. It listens to requests and then calls a certain function from the
 * service layer. Each method is mapped to a certain request type.
 */
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/student") public class StudentController {

    /**
     * @field studentService an injected data field with the type of StudentService.
     * Each method mapped to a particular client-request call a certain function from
     * service layer (StudentService) using this data field.
     */
    private final StudentService studentService;

    /**
     * Listens to a request asking to reserve a book for a student.
     *
     * @param book Book object represents a book's details.
     * @param studentId a string value representing a student's id.
     * @return ResponseEntity object.
     */
    @PostMapping("/reserveBook")
    public ResponseEntity<Object> reserveBook(@RequestBody Book book,
                                              @RequestParam(name = "studentId") String studentId)  {
        return studentService.reserveBook(book, studentId);
    }

    /**
     * Listens to a request asking to report a lost of a book that is being hold by a student.
     *
     * @param book Book object represents a book's details.
     * @param studentId a string value representing a student's id.
     * @return ResponseEntity object.
     */
    @PutMapping("/reportLost")
    public ResponseEntity<Object> reportLost(@RequestBody Book book,
                           @RequestParam(name = "studentId") String studentId){
        return studentService.reportLost(book, studentId);
    }

    /**
     * Listens to a request asking to send a message coming from a student to a librarian.
     *
     * @param topic a string value representing topic of a message.
     * @param email a string value representing email of a student.
     * @param message a string value representing message of a student.
     * @return ResponseEntity object.
     */
    @PostMapping("/ask")
    public ResponseEntity<Object> askLibrarian(@RequestParam("topic") String topic,
                                               @RequestParam("email") String email,
                                               @RequestBody String message) {
        return studentService.askLibrarian(topic, message, email);
    }
}
