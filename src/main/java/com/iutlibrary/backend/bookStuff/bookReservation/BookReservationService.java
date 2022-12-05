package com.iutlibrary.backend.bookStuff.bookReservation;

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

@Service
@Transactional
@AllArgsConstructor
public class BookReservationService {

    @Autowired
    private final BookReservationRepository repository;

    public void addNewReservation(BookReservation bookReservation) {
        repository.save(bookReservation);
    }

    public List<BookReservation> findAllPending() {
        // updates status if it is overdue before showing in table
        // #TODO must be updated everywhere (below functions)
        List<BookReservation> reservations = repository.findAllPendingReservations(ReservationStatus.COMPLETED);

        reservations.forEach(bookReservation -> {
            if (LocalDate.now().compareTo(Constants.DEADLINE) > 0){
                bookReservation.setStatus(ReservationStatus.OVERDUE);
            }
        });
        return reservations;
    }

    public List<BookReservation> findByStatus(ReservationStatus status){
        return repository.findBookReservationByStatus(status);
    }

    public Optional<BookReservation> findByISBN(Long isbn, String studentId, ReservationStatus completed) {
        return repository.findActiveBookReservationByISBN(isbn, studentId, completed);
    }

    public BookReservation findByBarcode(Long barcode) {
        return repository.findBookReservationByBarcode(barcode,  ReservationStatus.COMPLETED);
    }

    public List<BookReservation> findByTitle(String title) {
        return repository.findBookReservationByTitle(title);
    }

    public List<BookReservation> findAllOfStudent(String studentId) {
        return repository.findAllByStudentId(studentId);
    }

    public List<BookReservation> findInUseById(String studentId, ReservationStatus completed) {
        return repository.findInUseByMemberId(studentId, completed);
    }

    public List<BookReservation> findActiveById(String studentId) {
        return repository.findActiveBookReservationByMemberId(studentId, ReservationStatus.PENDING);
    }

    public List<BookReservation> findOverdueById(String studentId) {
        return repository.findOverdueByMemberId(studentId, ReservationStatus.OVERDUE);
    }

    public List<BookReservation> findCompletedById(String studentId) {
        return repository.findCompletedReservationsOfStudent(studentId, ReservationStatus.COMPLETED);
    }

    public void updateStatus(ReservationStatus status, Long barcode){
        repository.updateStatus(status, barcode);
    }

    public List<BookReservation> findByTitleOfStudent(String studentId, String title) {
        return repository.findAllOfStudentByTitle(studentId, title);
    }
}
