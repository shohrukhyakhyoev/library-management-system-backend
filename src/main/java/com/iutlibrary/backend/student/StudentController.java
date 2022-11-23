package com.iutlibrary.backend.student;

import com.iutlibrary.backend.bookStuff.book.Book;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/student")
@CrossOrigin(maxAge = 3600)
public class StudentController {

    private final StudentService studentService;

    // #TODO is it really PostMapping?
    @PostMapping("/reserveBook")
    public ResponseEntity<Object> reserveBook(@RequestBody Book book,
                                              @RequestParam(name = "studentId") String studentId)  {
        return studentService.reserveBook(book, studentId);
    }

    @PutMapping("/reportLost")
    public ResponseEntity<Object> reportLost(@RequestBody Book book,
                           @RequestParam(name = "studentId") String studentId){
        return studentService.reportLost(book, studentId);
    }


    // need also student email to add in topic so that librarian knows who sent the message
    @PostMapping("/ask")
    public ResponseEntity<Object> askLibrarian(@RequestParam("topic") String topic,
                                               @RequestParam("email") String email,
                                               @RequestBody String message) {
        return studentService.askLibrarian(topic, message, email);
    }


}
