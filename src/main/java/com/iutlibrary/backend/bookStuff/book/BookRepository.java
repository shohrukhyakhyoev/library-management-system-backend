package com.iutlibrary.backend.bookStuff.book;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

/**
 * Serves as a data layer for manipulation over data of book.
 * It allows application to contact with database with the help of
 * functions together with the specified SQL Query. To accomplish it
 * interface extends JpaRepository class. By this we use Spring Data
 * JPA Framework to map objects in database table. It is called ORM.
 *
 * @author shohrukhyakhyoev
 */
public interface BookRepository extends JpaRepository<Book, Long> {

    /**
     * Gets details of a book with given isbn.
     *
     * @param isbn represents book's isbn.
     * @return Book object.
     */
    @Query("SELECT b FROM Book b WHERE b.ISBN = ?1")
    Optional<Book> findByISBN(Long isbn);

    /**
     * Gets details of all books by given title.
     *
     * @param title represents book's title.
     * @return list of Book objects.
     */
    @Query("SELECT b FROM Book b WHERE lower(b.title) LIKE lower(concat('%', :title, '%')) ")
    List<Book> findByTitle(String title);

    /**
     * Gets details of all books by given author.
     *
     * @param author represents book's author.
     * @return list of Book objects.
     */
    @Query("SELECT b FROM Book b WHERE lower(b.author) LIKE lower(concat('%', :author, '%')) ")
    List<Book> findByAuthor(String author);

    /**
     * Gets details of all books by given subject.
     *
     * @param subject represents book's subject.
     * @return list of Book objects.
     */
    @Query("SELECT b FROM Book b WHERE lower(b.subject) LIKE lower(concat('%', :subject, '%')) ")
    List<Book> findBySubject(String subject);

    /**
     * Updates details of a book with specified ISBN.
     *
     * @param title represents book's title.
     * @param author represents book's author.
     * @param language represents book's language.
     * @param numberOfPages represents book's numberOfPages.
     * @param subject represents book's subject.
     * @param year represents book's year.
     * @param ISBN represents book's ISBN.
     * @return a long value representing a number of rows affected after the query execution.
     */
    @Modifying
    @Query("UPDATE Book b SET b.title=?1, b.author=?2, b.language=?3, b.numberOfPages=?4, b.subject=?5, b.publicationDate=?6 WHERE b.ISBN = ?7")
    long updateBook(String title, String author, String language, Integer numberOfPages, String subject, Long year, Long ISBN);
}
