package com.iutlibrary.backend.student;

import com.iutlibrary.backend.bookStuff.book.Book;
import com.iutlibrary.backend.fine.Fine;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

}
