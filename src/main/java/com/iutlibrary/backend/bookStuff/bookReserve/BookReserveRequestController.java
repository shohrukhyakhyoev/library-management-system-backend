package com.iutlibrary.backend.bookStuff.bookReserve;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Serves as a controller for requests associated with manipulation over a book reservation's data.
 * It listens to requests and then calls a certain function from the service layer.
 * Each method is mapped to a certain request type.
 */
@RestController
@RequestMapping("/api/v1/reserve")
@AllArgsConstructor
public class BookReserveRequestController {

    /**
     * @field bookReserveService an injected data field with the type of BookReserveService.
     * Each method mapped to a particular client-request call a certain function from
     * service layer (BookReserveService) using this data field.
     */
    @Autowired
    private final BookReserveService bookReserveService;

    /**
     * Listens to a request asking all reserve requests of all students.
     *
     * @return list of BookReserveRequest objects.
     */
    @GetMapping("/all")
    public List<BookReserveRequest> getAllBookReserveRequest(){
        return bookReserveService.getAllBookReserveRequest();
    }

    /**
     * Listens to a request asking to search for reserve requests by title.
     *
     * @param title represents book's title.
     * @return list of BookReserveRequest
     */
    @GetMapping("/search/title")
    private List<BookReserveRequest> getAllByTitle(@RequestParam("title") String title){
        return bookReserveService.findBookRequestByBookTitle(title);
    }

    /**
     * Listens to a request asking to search for reserve requests by isbn.
     *
     * @param isbn represents book's isbn.
     * @return list of BookReserveRequest
     */
    @GetMapping("/search/ISBN")
    private List<BookReserveRequest> getAllByISBN(@RequestParam("ISBN") Long isbn){
        return bookReserveService.findBookRequestByOnlyISBN(isbn);
    }

    /**
     * Listens to a request asking to search for reserve requests by studentId.
     *
     * @param studentId represents student's id.
     * @return list of BookReserveRequest
     */
    @GetMapping("/search/student")
    private List<BookReserveRequest> getAllByStudentId(@RequestParam("studentId") String studentId){
        return bookReserveService.findBookRequestByStudentId(studentId);
    }

}