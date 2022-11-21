package com.iutlibrary.backend.bookStuff.book;

import com.iutlibrary.backend.bookStuff.bookItem.BookItemService;
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

    public void registerNewBook(Book book) {
        // once librarian enters isbn, if it exists: autofill other input fields
        if (!repository.findByISBN(book.getISBN()).isPresent()) {
            repository.save(book);
        }

        bookItemService.addNewBookItem(new BookItem(BookStatus.AVAILABLE, book.getISBN()));
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
}

