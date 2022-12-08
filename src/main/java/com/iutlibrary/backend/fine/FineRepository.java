package com.iutlibrary.backend.fine;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

/**
 * Serves as a data layer for manipulation over data of fine.
 * It allows application to contact with database with the help of
 * functions together with the specified SQL Query. To accomplish it
 * interface extends JpaRepository class. By this we use Spring Data
 * JPA Framework to map objects in database table. It is called ORM.
 */
public interface FineRepository extends JpaRepository<Fine, Long> {

    /**
     * Gets all fines details of one student.
     *
     * @param studentId represents a student's id.
     * @return list of Fine objects.
     */
    @Query("SELECT f FROM Fine f WHERE f.studentId = ?1")
    List<Fine> findFineByStudentId(String studentId);

    /**
     * Gets all fines details of a particular reason.
     *
     * @param reason represents a fine's reason.
     * @return list of Fine objects.
     */
    @Query("SELECT f FROM Fine f WHERE f.reason = ?1")
    List<Fine> getAllByReason(String reason);

    /**
     * Gets all fines details of one isbn.
     *
     * @param isbn represents a book's isbn.
     * @return list of Fine objects.
     */
    @Query("SELECT f FROM Fine f WHERE f.ISBN = ?1")
    List<Fine> findByISBN(Long isbn);
}
