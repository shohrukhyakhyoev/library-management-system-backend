package com.iutlibrary.backend.bookStuff.book;

import com.iutlibrary.backend.utility.enums.BookStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Book b WHERE b.ISBN = ?1")
    Optional<Book> findByISBN(Long isbn);

    @Query("SELECT b FROM Book b WHERE lower(b.title) LIKE lower(concat('%', :title, '%')) ")
    List<Book> findByTitle(String title);

    @Query("SELECT b FROM Book b WHERE lower(b.author) LIKE lower(concat('%', :author, '%')) ")
    List<Book> findByAuthor(String author);

}