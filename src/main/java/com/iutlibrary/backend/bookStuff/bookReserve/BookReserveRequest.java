package com.iutlibrary.backend.bookStuff.bookReserve;

import javax.persistence.*;

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


    public BookReserveRequest() {
    }

    public BookReserveRequest(String studentId,
                              String bookTitle,
                              Long ISBN,
                              Long barcode) {
        this.studentId = studentId;
        this.bookTitle = bookTitle;
        this.barcode = barcode;
        this.ISBN = ISBN;
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

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

}
