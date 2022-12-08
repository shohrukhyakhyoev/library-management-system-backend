package com.iutlibrary.backend.bookStuff.bookReservation;

import com.iutlibrary.backend.utility.enums.ReservationStatus;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.time.LocalDate;

/**
 * Represetns a book reservation.
 */
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
    private String author;

    /**
     * Non-args constructor.
     */
    public BookReservation() {
    }

    /**
     * Creates a new book reservation object with specified data fields.
     *
     * @param title represents a book's title.
     * @param ISBN represents a book's isbn.
     * @param bookItemBarcode represents a book item's barcode.
     * @param studentId represents a student's id.
     * @param status represents a book reservation's status.
     * @param creationDate represents a creation date of a reservation.
     * @param returnDate represents a return date of a reservation.
     * @param author represents an author of a book.
     */
    public BookReservation(String title, Long ISBN, Long bookItemBarcode,
                           String studentId, ReservationStatus status,
                           LocalDate creationDate,
                           LocalDate returnDate,
                           String author) {
        this.title = title;
        this.ISBN = ISBN;
        this.barcode = bookItemBarcode;
        this.studentId = studentId;
        this.status = status;
        this.creationDate = creationDate;
        this.returnDate = returnDate;
        this.author = author;

    }

    /**
     * Gets a book item's barcode.
     *
     * @return a long value representing a book item's barcode.
     */
    public Long getBarcode() {
        return barcode;
    }

    /**
     * Sets a value to a book item's barcode.
     *
     * @param barcode a string value representing a book item's barcode.
     */
    public void setBarcode(Long barcode) {
        this.barcode = barcode;
    }

    /**
     * Gets a book's isbn.
     *
     * @return a long value representing a book's isbn.
     */
    public Long getISBN() {
        return ISBN;
    }

    /**
     * Sets a value to a book 's ISBN.
     *
     * @param ISBN a string value representing a book's ISBN.
     */
    public void setISBN(Long ISBN) {
        this.ISBN = ISBN;
    }

    /**
     * Gets a book's title.
     *
     * @return a string value representing a book's title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets a value to a book's title.
     *
     * @param title a string value representing a book's title.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets a book reservation's status.
     *
     * @return a spring value representing a book item's status.
     */
    public ReservationStatus getStatus() {
        return status;
    }

    /**
     * Sets a value to a book reservation's status.
     *
     * @param status a ReservationStatus value representing a book reservation's  status.
     */
    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    /**
     * Gets a book's student id.
     *
     * @return a string value representing a student's id.
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * Sets a value to a student's id.
     *
     * @param studentId a string value representing a student's id.
     */
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    /**
     * Gets a book reservation's creation date.
     *
     * @return a LocalDate value representing a book reservation's creation date.
     */
    public LocalDate getCreationDate() {
        return creationDate;
    }

    /**
     * Sets a value to a book reservation's creation date.
     *
     * @param creationDate a string value representing a book reservation's creation date.
     */
    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * Gets a book reservation's return date.
     *
     * @return a LocalDate value representing a book reservation's return date.
     */
    public LocalDate getReturnDate() {
        return returnDate;
    }

    /**
     * Gets a book's author.
     *
     * @return a string value representing a book's  author.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sets a value to a book's author.
     *
     * @param author a string value representing a book's  author.
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Sets a value to a book reservation's return date.
     *
     * @param returnDate a LocalDate value representing a book reservation's return date.
     */
    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

}
