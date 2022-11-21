package com.iutlibrary.backend.bookStuff.bookItem;

import com.iutlibrary.backend.utility.enums.BookStatus;

import javax.persistence.*;

@Entity
@Table
public class BookItem {
    @Id
    @SequenceGenerator(
            name = "bookItem_sequence",
            sequenceName = "bookItem_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "bookItem_sequence"
    )
    private Long barcode;
    @Enumerated(EnumType.STRING)
    private BookStatus status;

    private Long ISBN;


    public BookItem() {
    }

    public BookItem(BookStatus status, Long ISBN) {
        this.status = status;
        this.ISBN = ISBN;
    }

    public Long getBarcode() {
        return barcode;
    }

    public void setBarcode(Long barcode) {
        this.barcode = barcode;
    }

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }

    public Long getISBN() {
        return ISBN;
    }

    public void setISBN(Long ISBN) {
        this.ISBN = ISBN;
    }
}
