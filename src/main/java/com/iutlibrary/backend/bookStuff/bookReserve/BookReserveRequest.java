package com.iutlibrary.backend.bookStuff.bookReserve;

import javax.persistence.*;

/**
 * Represents a book reserve request.
 */
@Entity
@Table
public class BookReserveRequest {
    private Long barcode;
    private Long ISBN;
    private String bookTitle;
    private String studentId;
    @Id
    @SequenceGenerator(
            name = "bookReserveRequest_sequence",
            sequenceName = "bookReserveRequest_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "bookReserveRequest_sequence"
    )
    private Long id;

    /**
     * Non-args constructor.
     */
    public BookReserveRequest() {
    }

    /**
     * Creates a new book reserve request object.
     *
     * @param studentId represents student's id.
     * @param bookTitle represents book's title.
     * @param ISBN represents book's isbn.
     * @param barcode represents book item's barcode.
     */
    public BookReserveRequest(String studentId,
                              String bookTitle,
                              Long ISBN,
                              Long barcode) {
        this.studentId = studentId;
        this.bookTitle = bookTitle;
        this.barcode = barcode;
        this.ISBN = ISBN;
    }

    /**
     * Gets a book item's barcode.
     *
     * @return a string value representing a book item's barcode.
     */
    public Long getBarcode() {
        return barcode;
    }

    /**
     * Sets a value to book item's barcode.
     *
     * @param barcode represents a book item's barcode.
     */
    public void setBarcode(Long barcode) {
        this.barcode = barcode;
    }

    /**
     * Gets a book's isbn.
     *
     * @return a long value representing a book's long.
     */
    public Long getISBN() {
        return ISBN;
    }

    /**
     * Sets a value to book's isbn.
     *
     * @param ISBN represents a book's isbn.
     */
    public void setISBN(Long ISBN) {
        this.ISBN = ISBN;
    }

    /**
     * Gets a book's title.
     *
     * @return a string value representing a book's title.
     */
    public String getBookTitle() {
        return bookTitle;
    }

    /**
     * Sets a value to book's title.
     *
     * @param bookTitle represents a book's title.
     */
    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    /**
     * Gets a student's id.
     *
     * @return a string value representing a student's id.
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * Sets a value to book item's barcode.
     *
     * @param studentId represents a student's id.
     */
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

}
