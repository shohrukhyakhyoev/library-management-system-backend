package com.iutlibrary.backend.bookStuff.book;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/book")
@CrossOrigin(maxAge = 3600)
public class BookController {
    private final BookService bookService;


    @PostMapping("/add")
    public void registerNewBook(@RequestBody Book book,
                                @RequestParam("total") int total) {
        bookService.registerNewBook(book, total);
    }

    @GetMapping("/all")
    public List<Book> getAllBook() {
        return bookService.getAll();
    }

    @GetMapping("/one")
    public Book getBook(@RequestParam("ISBN") Long isbn){
        return bookService.findByISBN(isbn);
    }

    @GetMapping("/search/title")
    public List<Book> findByTitle(@RequestParam("title") String title){
        return bookService.findByTitle(title);
    }

    @GetMapping("/search/author")
    public List<Book> findByAuthor(@RequestParam("author") String author){
        return bookService.findByAuthor(author);
    }
}
