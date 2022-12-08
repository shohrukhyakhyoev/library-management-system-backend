package com.iutlibrary.backend.bookStuff.book;

import com.iutlibrary.backend.bookStuff.bookItem.BookItemService;
import com.iutlibrary.backend.exception.ApiRequestException;
import com.iutlibrary.backend.image.ImageService;
import com.iutlibrary.backend.utility.enums.BookStatus;
import com.iutlibrary.backend.bookStuff.bookItem.BookItem;
import com.iutlibrary.backend.utility.enums.ReservationStatus;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


/**
 * Serves as a service layer for requests associated with manipulation over book's data.
 *
 * @author shohrukhyakhyoev
 */
@Service
@Transactional
@AllArgsConstructor
public class BookService {

    /**
     * @field   repository  used to interact with data layer.
     * @field   bookItemService used to interact with BookItem's service layer.
     * @field   imageService used to interact with Image's service layer.
     */
    @Autowired
    private final BookRepository repository;
    @Autowired
    private final BookItemService bookItemService;
    @Autowired
    private final ImageService imageService;

    /**
     * Adds new book into the database.
     * Firstly, it checks whether this book already exists in the database or not. If exists,
     * it adds given number of book items (copies); else it firstly, adds a general book details
     * itself before adding n number of book items into the database.
     *
     * @param book Book object.
     * @param total represents number of book items to be added into the database.
     */
    public void registerNewBook(Book book, int total) {

        Long isbn = book.getISBN();

        if (!repository.findByISBN(isbn).isPresent()) {
            book.setImageData(imageService.getImage(isbn));
            repository.save(book);
            imageService.deleteImage(isbn);
        }

        for (int i=1; i<=total; i++){
            bookItemService.addNewBookItem(new BookItem(BookStatus.AVAILABLE, isbn));
        }

        updateAvailabilityStatus(isbn);
    }

    /**
     * Gets details of all books in the database.
     *
     * @return list of Book objects.
     */
    public List<Book> getAll() {
        return repository.findAll();
    }

    /**
     * Gets details of all books with the given title in the database.
     *
     * @param title represents book's title
     * @return list of Book objects.
     */
    public List<Book> findByTitle(String title) {
        return repository.findByTitle(title);
    }

    /**
     * Gets details of all books with the given author in the database.
     *
     * @param author represents book's author
     * @return list of Book objects.
     */
    public List<Book> findByAuthor(String author) {
        return repository.findByAuthor(author);
    }

    /**
     * Gets details of all books with the given subject in the database.
     *
     * @param subject represents book's subject
     * @return list of Book objects.
     */
    public List<Book> findBySubject(String subject) {
        return repository.findBySubject(subject);
    }

    /**
     * Gets details of a book with the given isbn in the database.
     *
     * @param isbn represents book's isbn
     * @return Book object.
     * @throws ApiRequestException
     */
    public Book findByISBN(Long isbn) {
        return repository.findByISBN(isbn)
                .orElseThrow(()-> new ApiRequestException("Book with given ISBN does not exist!"));
    }

    /**
     * Updates an existing book.
     *
     * @param book Book object that contains new updated data fields.
     * So method overwrite values of existing book with new given details.
     * @return ResponseEntity object.
     * @throws ApiRequestException
     */
    public ResponseEntity<String> updateBook(Book book){
        if (repository.updateBook(book.getTitle(), book.getAuthor(), book.getLanguage(),
                book.getNumberOfPages(), book.getSubject(), book.getPublicationDate(),
                book.getISBN()) == 0){
            throw new ApiRequestException("Book's details are unsuccessfully updated!");
        }

        return new ResponseEntity<>("Book's details are updated successfully", HttpStatus.OK);
    }


    /**
     * Updates availability status of a book.
     * It is called each when book is reserved/requested/issued or returned.
     *
     * @param isbn represents book's isbn.
     * @throws ApiRequestException
     */
    public void updateAvailabilityStatus(Long isbn) {
        Book book = repository.findByISBN(isbn).orElseThrow
                (()-> new ApiRequestException("Book, which isAvailable field was gonna be updated, does not exist!"));

        List<BookItem> bookItems = bookItemService.findTopByISBNAndStatus(isbn, BookStatus.AVAILABLE);

        if (bookItems.isEmpty()) {
            book.setAvailable(Boolean.FALSE);
        } else {
            book.setAvailable(Boolean.TRUE);
        }
    }
}

