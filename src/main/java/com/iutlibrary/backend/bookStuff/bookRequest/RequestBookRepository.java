package com.iutlibrary.backend.bookStuff.bookRequest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Serves as a data layer for manipulation over data of book request.
 * It allows application to contact with database with the help of
 * functions together with the specified SQL Query. To accomplish it
 * interface extends JpaRepository class. By this we use Spring Data
 * JPA Framework to map objects in database table. It is called ORM.
 *
 * @author shohrukhyakhyoev
 */
@Repository
public interface RequestBookRepository extends JpaRepository<RequestBook, Long> {

    /**
     * Gets details a request book whose data fields match to the given params.
     *
     * @param studentId represents a student's id.
     * @param ISBN represents a book's isbn.
     * @return RequestBook object.
     */
    @Query("SELECT  rb FROM RequestBook rb WHERE rb.studentId = ?1 AND rb.ISBN = ?2")
    Optional<RequestBook> findByStudentIdAndISBN(String studentId, Long ISBN);

    /**
     * Gets details of all requests whose isbn value matches with the given param.
     *
     * @param ISBN represents a book's isbn value.
     * @return list of RequestBook request.
     */
    @Query("SELECT rb FROM RequestBook rb WHERE rb.ISBN = ?1")
    List<Optional<RequestBook>> findRequestBookByISBN(Long ISBN);

}
