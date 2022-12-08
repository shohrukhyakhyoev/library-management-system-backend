package com.iutlibrary.backend.bookStuff.bookReservation;

import com.iutlibrary.backend.exception.ApiRequestException;
import com.iutlibrary.backend.utility.enums.ReservationStatus;
import com.iutlibrary.backend.utility.Constants;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Serves as a service layer for requests associated with manipulation over book reservation's data.
 */
@Service
@Transactional
@AllArgsConstructor
public class BookReservationService {
    /**
     * @field   repository  used to interact with data layer.
     */
    @Autowired
    private final BookReservationRepository repository;

    /**
     * Aadds new book reservation into the database.
     *
     * @param bookReservation represetns BookReservation object.
     */
    public void addNewReservation(BookReservation bookReservation) {
        repository.save(bookReservation);
    }

    /**
     * Gets all book reservations with pending reservation status.
     *
     * @return list of BookReservation objects.
     */
    public List<BookReservation> findAllPending() {
        // #TODO must be updated everywhere (below functions)
        List<BookReservation> reservations = repository.findAllPendingReservations(ReservationStatus.COMPLETED);

        reservations.forEach(bookReservation -> {
            if (LocalDate.now().compareTo(Constants.DEADLINE) > 0){
                bookReservation.setStatus(ReservationStatus.OVERDUE);
            }
        });
        return reservations;
    }

    /**
     * Gets all book reservations whose status matches to the given param.
     *
     * @param status represents a book reservation status.
     * @return list of BookReservation objects.
     */
    public List<BookReservation> findByStatus(ReservationStatus status){
        return repository.findBookReservationByStatus(status);
    }

    /**
     * Gets a book reservation whose certain data fields match to the given params.
     *
     * @param isbn represents a book's status.
     * @param studentId represents student's id.
     * @param completed  represents a book reservation's status.
     * @return BookReservation object.
     */
    public Optional<BookReservation> findByISBN(Long isbn, String studentId, ReservationStatus completed) {
        return repository.findActiveBookReservationByISBN(isbn, studentId, completed);
    }

    /**
     * Gets a book reservation whose certain data field match to the given param.
     *
     * @param barcode represents a book item's barcode.
     * @return BookReservation object.
     * @throws ApiRequestException
     */
    public BookReservation findByBarcode(Long barcode) {
        return repository.findBookReservationByBarcode(barcode, ReservationStatus.COMPLETED)
                .orElseThrow(()-> new ApiRequestException("Reservation with given barcode does not exist!"));
    }

    /**
     * Gets all book reservations whose title data field matches to the given param.
     *
     * @param title represents a book's title.
     * @return list of BookReservation objects.
     */
    public List<BookReservation> findByTitle(String title) {
        return repository.findBookReservationByTitle(title);
    }

    /**
     * Gets all book reservations of one student.
     *
     * @param studentId represents a student's id.
     * @return list of BookReservation objects.
     */
    public List<BookReservation> findAllOfStudent(String studentId) {
        return repository.findAllByStudentId(studentId);
    }

    /**
     * Gets all book reservations of one student whose status are either pending or overdue.
     *
     * @param studentId represents a student's id.
     * @param completed represents a book reservation status.
     * @return list of BookReservation objects.
     */
    public List<BookReservation> findInUseById(String studentId, ReservationStatus completed) {
        return repository.findInUseByMemberId(studentId, completed);
    }

    /**
     * Gets all active book reservations of one student.
     *
     * @param studentId represents a student's id.
     * @return list of BookReservation objects.
     */
    public List<BookReservation> findActiveById(String studentId) {
        return repository.findActiveBookReservationByMemberId(studentId, ReservationStatus.PENDING);
    }

    /**
     * Gets all overdue book reservations of one student.
     *
     * @param studentId represents a student's id.
     * @return list of BookReservation objects.
     */
    public List<BookReservation> findOverdueById(String studentId) {
        return repository.findOverdueByMemberId(studentId, ReservationStatus.OVERDUE);
    }

    /**
     * Gets all complete book reservations of one student.
     *
     * @param studentId represents a student's id.
     * @return list of BookReservation objects.
     */
    public List<BookReservation> findCompletedById(String studentId) {
        return repository.findCompletedReservationsOfStudent(studentId, ReservationStatus.COMPLETED);
    }

    /**
     * Updates a book reservation's status.
     *
     * @param status represents a new book reservation status value.
     * @param barcode represents a book item's barcode.
     */
    public void updateStatus(ReservationStatus status, Long barcode){
        repository.updateStatus(status, barcode);
    }

    /**
     * Gets all book reservations of one student whose title matches to the given param.
     *
     * @param studentId represents a student's id.
     * @param title represents a book's title.
     * @return list of BookReservation objects.
     */
    public List<BookReservation> findByTitleOfStudent(String studentId, String title) {
        return repository.findAllOfStudentByTitle(studentId, title);
    }
}
