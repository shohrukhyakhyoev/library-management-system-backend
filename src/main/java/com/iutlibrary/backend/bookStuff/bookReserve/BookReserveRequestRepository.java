package com.iutlibrary.backend.bookStuff.bookReserve;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * Serves as a data layer for manipulation over data of book reserve request.
 * It allows application to contact with database with the help of
 * functions together with the specified SQL Query. To accomplish it
 * interface extends JpaRepository class. By this we use Spring Data
 * JPA Framework to map objects in database table. It is called ORM.
 */
public interface BookReserveRequestRepository extends JpaRepository<BookReserveRequest, Long> {

    /**
     * Gets reserve request of a particular student with specific params.
     *
     * @param ISBN represents a book's isbn.
     * @param studentId represents a student's id.
     * @return BookReserveRequest object.
     */
    @Query("SELECT b FROM BookReserveRequest b WHERE b.ISBN = ?1 AND b.studentId = ?2")
    Optional<BookReserveRequest> findBookRequestByISBN(Long ISBN, String studentId);

    /**
     * Gets all reserve requests whose title data field matches to the given param.
     *
     * @param title represents a book's title.
     * @return list of BookReserveRequest objects.
     */
    @Query("SELECT b FROM BookReserveRequest b WHERE b.bookTitle LIKE concat('%', :title, '%') ")
    List<BookReserveRequest> findBookRequestByBookTitle(String title);

    /**
     * Gets all reserve requests whose isbn data field matches to the given param.
     *
     * @param isbn represents a book's isbn.
     * @return list of BookReserveRequest objects.
     */
    @Query("SELECT b FROM BookReserveRequest b WHERE b.ISBN = ?1")
    List<BookReserveRequest> findAllByISBN(Long isbn);

    /**
     * Gets all reserve requests of one student.
     *
     * @param studentId represents a student's id.
     * @return list of BookReserveRequest objects.
     */
    @Query("SELECT b FROM BookReserveRequest b WHERE b.studentId = ?1")
    List<BookReserveRequest> findByAllStudentId(String studentId);

    /**
     * Deletes a reserve request object from the database.
     *
     * @param ISBN represents a book's isbn.
     * @return a long value representing number of rows affecred after execution of sql query.
     */
    long deleteBookReserveRequestByISBN(Long ISBN);

}

