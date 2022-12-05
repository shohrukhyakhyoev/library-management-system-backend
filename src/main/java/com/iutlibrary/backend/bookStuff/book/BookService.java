package com.iutlibrary.backend.bookStuff.book;

import com.iutlibrary.backend.bookStuff.bookItem.BookItemService;
import com.iutlibrary.backend.image.ImageService;
import com.iutlibrary.backend.utility.enums.BookStatus;
import com.iutlibrary.backend.bookStuff.bookItem.BookItem;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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


    public Book findByISBN(Long isbn) {
        return repository.findByISBN(isbn).get();
    }

    public Boolean getNumberOfAvailableBooks(Long isbn) {
        if (!bookItemService.findTopByISBNAndStatus(isbn, BookStatus.AVAILABLE)
                .isEmpty()) {
            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }
}

