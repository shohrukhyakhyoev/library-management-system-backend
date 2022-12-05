package com.iutlibrary.backend.bookStuff.book;

import com.iutlibrary.backend.bookStuff.bookItem.BookItemService;
import com.iutlibrary.backend.exception.ApiRequestException;
import com.iutlibrary.backend.image.ImageService;
import com.iutlibrary.backend.utility.enums.BookStatus;
import com.iutlibrary.backend.bookStuff.bookItem.BookItem;
import com.iutlibrary.backend.utility.enums.ReservationStatus;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class BookService {
    @Autowired
    private final BookRepository repository;
    @Autowired
    private final BookItemService bookItemService;
    @Autowired
    private final ImageService imageService;

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

    public List<Book> getAll() {
        return repository.findAll();
    }

    public List<Book> findByTitle(String title) {
        return repository.findByTitle(title);
    }

    public List<Book> findByAuthor(String author) {
        return repository.findByAuthor(author);
    }

    public List<Book> findBySubject(String subject) {
        return repository.findBySubject(subject);
    }

    public Book findByISBN(Long isbn) {
        return repository.findByISBN(isbn)
                .orElseThrow(()-> new ApiRequestException("Book with given ISBN does not exist!"));
    }

    public void updateAvailabilityStatus(Long isbn) {
        Book book = repository.findByISBN(isbn).orElseThrow
                (()-> new ApiRequestException("Book, which isAvailable field was gonna be updated, does not exist!"));

        List<BookItem> bookItems = bookItemService.findTopByISBNAndStatus(isbn, BookStatus.AVAILABLE);

        if (bookItems.isEmpty()) {
            book.setAvailable(Boolean.TRUE);
        } else {
            book.setAvailable(Boolean.FALSE);
        }
    }
}

