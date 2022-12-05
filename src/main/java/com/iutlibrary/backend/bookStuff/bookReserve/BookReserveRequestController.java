package com.iutlibrary.backend.bookStuff.bookReserve;

import com.iutlibrary.backend.bookStuff.bookRequest.RequestBookRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reserve")
@AllArgsConstructor
public class BookReserveRequestController {

    @Autowired
    private final BookReserveService bookReserveService;

    @GetMapping("/all")
    public List<BookReserveRequest> getAllBookReserveRequest(){
        return bookReserveService.getAllBookReserveRequest();
    }

    @GetMapping("/search")
    private List<BookReserveRequest> getAllByTitle(@RequestParam("title") String title){
        return bookReserveService.findBookRequestByBookTitle(title);
    }

    @GetMapping("/search")
    private List<BookReserveRequest> getAllByISBN(@RequestParam("ISBN") Long isbn){
        return bookReserveService.findBookRequestByOnlyISBN(isbn);
    }

    @GetMapping("/search")
    private List<BookReserveRequest> getAllByStudentId(@RequestParam("studentId") String studentId){
        return bookReserveService.findBookRequestByStudentId(studentId);
    }

}