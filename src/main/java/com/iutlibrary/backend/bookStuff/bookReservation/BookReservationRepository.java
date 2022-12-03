package com.iutlibrary.backend.bookStuff.bookReservation;

import com.iutlibrary.backend.utility.enums.BookStatus;
import com.iutlibrary.backend.utility.enums.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookReservationRepository extends JpaRepository<BookReservation, Long> {

    // TODO : check if <> works or not
    @Query("SELECT b FROM BookReservation b WHERE b.ISBN = ?1 AND b.studentId = ?2 " +
            "AND b.status <> ?3")
    Optional<BookReservation> findActiveBookReservationByISBN(Long ISBN, String studentId, ReservationStatus status);

    @Query("SELECT b FROM BookReservation b WHERE b.studentId = ?1 AND b.status <> ?2")
    List<BookReservation> findActiveBookReservationByMemberId(String studentId, ReservationStatus completed);

    @Query("SELECT b FROM BookReservation b WHERE b.studentId = ?1 AND b.status = ?2")
    List<BookReservation> findCompletedReservationsOfStudent(String studentId, ReservationStatus completed);

    @Query("SELECT b FROM BookReservation b WHERE b.status <> ?1")
    List<BookReservation> findAllPendingReservations(ReservationStatus status);

    @Query("SELECT b FROM BookReservation b WHERE b.status = ?1")
    List<BookReservation> findBookReservationByStatus(ReservationStatus status);

    @Query("SELECT b FROM BookReservation b WHERE b.barcode = ?1 AND b.status <> ?2")
    BookReservation findBookReservationByBarcode(Long barcode, ReservationStatus completed);



    @Modifying
    @Query("update BookReservation b set b.status = ?1 where b.barcode = ?2")
    void updateStatus(ReservationStatus status, Long barcode);


}
