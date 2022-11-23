package com.iutlibrary.backend.bookStuff.bookReserve;

import com.iutlibrary.backend.bookStuff.bookRequest.RequestBookRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reserve")
@AllArgsConstructor
@CrossOrigin(maxAge = 3600)
public class BookReserveRequestController {

    @Autowired
    private final BookReserveService bookReserveService;


    @GetMapping("/all")
    public List<BookReserveRequest> getAllBookReserveRequest(){
        return bookReserveService.getAllBookReserveRequest();
    }

}