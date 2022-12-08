package com.iutlibrary.backend.bookStuff.book;


import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Serves as a controller for requests associated with manipulation over book's data.
 * It listens to requests and then calls a certain function from the service layer.
 * Each method is mapped to a certain request type.
 *
 * @author shohrukhyakhyoev
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/book")
public class BookController {
    /**
     * @field bookService an injected data field with the type of BookService.
     * Each method mapped to a particular client-request call a certain function from
     * service layer (BookService) using this data field.
     */
    private final BookService bookService;

    /**
     * Listens to requests asking to add a new book into the database.
     *
     * @param book Book object containing all details of a new book.
     * @param total number of book items to be added.
     */
    @PostMapping("/add")
    public void registerNewBook(@RequestBody Book book,
                                @RequestParam("total") int total) {
        bookService.registerNewBook(book, total);
    }

    /**
     * Listens to requests asking details of all books in the database.
     *
     * @return list of Book objects.
     */
    @GetMapping("/all")
    public List<Book> getAllBook() {
        return bookService.getAll();
    }

    /**
     * Listens to requests asking details of one particular book.
     *
     * @param isbn represents special id of book which is unique.
     * @return Book object.
     */
    @GetMapping("/one")
    public Book getBook(@RequestParam("ISBN") Long isbn){
        return bookService.findByISBN(isbn);
    }

    /**
     * Listens to a request asking a number of books in the database.
     *
     * @return integer value representing total number of books in the database.
     */
    @GetMapping("/quantity")
    public Integer getQuantityOfBooks(){
        return bookService.getAll().size();
    }

    /**
     * Listens to a request asking to search for book details by title.
     *
     * @param title represents a book's title.
     * @return List of Book objects.
     */
    @GetMapping("/search/title")
    public List<Book> findByTitle(@RequestParam("title") String title){
        return bookService.findByTitle(title);
    }

    /**
     * Listens to a request asking to search for book details by author.
     *
     * @param author represents a book's author.
     * @return List of Book objects.
     */
    @GetMapping("/search/author")
    public List<Book> findByAuthor(@RequestParam("author") String author){
        return bookService.findByAuthor(author);
    }

    /**
     * Listens to a request asking to search for book details by subject.
     *
     * @param subject represents a book's subject.
     * @return List of Book objects.
     */
    @GetMapping("/search/subject")
    public List<Book> findBySubject(@RequestParam("subject") String subject){
        return bookService.findBySubject(subject);
    }

    /**
     * Listens to a request asking to update details of a certain book.
     *
     * @param book Book object.
     * @return ResponseEntity object.
     */
    @PutMapping("/update")
    public ResponseEntity<String> updateBookDetails(Book book){
        return bookService.updateBook(book);
    }

}
