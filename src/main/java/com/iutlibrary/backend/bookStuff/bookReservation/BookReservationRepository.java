package com.iutlibrary.backend.bookStuff.bookReservation;


import com.iutlibrary.backend.utility.enums.ReservationStatus;
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
public interface BookReservationRepository extends JpaRepository<BookReservation, Long> {

    /**
     * Gets a book reservation whose certain data fields match to the given params.
     *
     * @param ISBN represents a book's status.
     * @param studentId represents student's id.
     * @param status  represents a book reservation's status.
     * @return BookReservation object.
     * */
    @Query("SELECT b FROM BookReservation b WHERE b.ISBN = ?1 AND b.studentId = ?2 " +
            "AND b.status <> ?3")
    Optional<BookReservation> findActiveBookReservationByISBN(Long ISBN, String studentId, ReservationStatus status);


    /**
     * Gets all active book reservations of one student.
     *
     * @param studentId represents a student's id.
     * @return list of BookReservation objects.
     */
    @Query("SELECT b FROM BookReservation b WHERE b.studentId = ?1 AND b.status = ?2")
    List<BookReservation> findActiveBookReservationByMemberId(String studentId, ReservationStatus active);

    /**
     * Gets all complete book reservations of one student.
     *
     * @param studentId represents a student's id.
     * @return list of BookReservation objects.
     */
    @Query("SELECT b FROM BookReservation b WHERE b.studentId = ?1 AND b.status = ?2")
    List<BookReservation> findCompletedReservationsOfStudent(String studentId, ReservationStatus completed);

    /**
     * Gets all book reservations with pending reservation status.
     *
     * @return list of BookReservation objects.
     */
    @Query("SELECT b FROM BookReservation b WHERE b.status <> ?1")
    List<BookReservation> findAllPendingReservations(ReservationStatus status);

    /**
     * Gets all book reservations whose status matches to the given param.
     *
     * @param status represents a book reservation status.
     * @return list of BookReservation objects.
     */
    @Query("SELECT b FROM BookReservation b WHERE b.status = ?1")
    List<BookReservation> findBookReservationByStatus(ReservationStatus status);

    /**
     * Gets a book reservation whose certain data field match to the given param.
     *
     * @param barcode represents a book item's barcode.
     * @return BookReservation object.
     */
    @Query("SELECT b FROM BookReservation b WHERE b.barcode = ?1 AND b.status <> ?2")
    Optional<BookReservation> findBookReservationByBarcode(Long barcode, ReservationStatus completed);

    /**
     * Gets all book reservations whose title data field matches to the given param.
     *
     * @param title represents a book's title.
     * @return list of BookReservation objects.
     */
    @Query("SELECT b FROM BookReservation b WHERE b.title LIKE concat('%', :title, '%') ")
    List<BookReservation> findBookReservationByTitle(String title);

    /**
     * Updates a book reservation's status.
     *
     * @param status represents a new book reservation status value.
     * @param barcode represents a book item's barcode.
     */
    @Modifying
    @Query("update BookReservation b set b.status = ?1 where b.barcode = ?2")
    void updateStatus(ReservationStatus status, Long barcode);

    /**
     * Gets all book reservations of one student.
     *
     * @param studentId represents a student's id.
     * @return list of BookReservation objects.
     */
    @Query("SELECT b FROM BookReservation b WHERE b.studentId=?1")
    List<BookReservation> findAllByStudentId(String studentId);

    /**
     * Gets all book reservations of one student whose status are either pending or overdue.
     *
     * @param studentId represents a student's id.
     * @param completed represents a book reservation status.
     * @return list of BookReservation objects.
     */
    @Query("SELECT b FROM BookReservation b WHERE b.studentId = ?1 AND b.status <> ?2")
    List<BookReservation> findInUseByMemberId(String studentId, ReservationStatus completed);

    /**
     * Gets all overdue book reservations of one student.
     *
     * @param studentId represents a student's id.
     * @return list of BookReservation objects.
     */
    @Query("SELECT b FROM BookReservation b WHERE b.studentId =?1 AND b.status = ?2")
    List<BookReservation> findOverdueByMemberId(String studentId, ReservationStatus overdue);

    /**
     * Gets all book reservations of one student whose title matches to the given param.
     *
     * @param studentId represents a student's id.
     * @param title represents a book's title.
     * @return list of BookReservation objects.
     */
    @Query("SELECT b FROM BookReservation b WHERE b.studentId = ?1 AND b.title LIKE concat('%', ?2, '%')")
    List<BookReservation> findAllOfStudentByTitle(String studentId, String title);
}
