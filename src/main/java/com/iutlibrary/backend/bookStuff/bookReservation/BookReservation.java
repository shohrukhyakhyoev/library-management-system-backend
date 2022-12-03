package com.iutlibrary.backend.bookStuff.bookReservation;

import com.iutlibrary.backend.utility.enums.ReservationStatus;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.time.LocalDate;

@Entity
@Table
public class BookReservation {
    @Id
    @SequenceGenerator(
            name = "bookReservation_sequence",
            sequenceName = "bookReservation_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "bookReservation_sequence"
    )
    private Long id;
    private Long barcode;
    private Long ISBN;
    private String title;
    @Enumerated(EnumType.STRING)
    private ReservationStatus status;
    private String studentId;
    private LocalDate creationDate;
    private LocalDate returnDate;

    public BookReservation() {
    }

    public BookReservation(String title, Long ISBN, Long bookItemBarcode,
                           String studentId, ReservationStatus status,
                           LocalDate creationDate,
                           LocalDate returnDate) {
        this.title = title;
        this.ISBN = ISBN;
        this.barcode = bookItemBarcode;
        this.studentId = studentId;
        this.status = status;
        this.creationDate = creationDate;
        this.returnDate = returnDate;
    }

    public Long getBarcode() {
        return barcode;
    }

    public void setBarcode(Long barcode) {
        this.barcode = barcode;
    }

    public Long getISBN() {
        return ISBN;
    }

    public void setISBN(Long ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

}
