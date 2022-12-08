package com.iutlibrary.backend.bookStuff.bookItem;

import com.iutlibrary.backend.utility.enums.BookStatus;
import javax.persistence.*;


/**
 * Represents a book item.
 *
 * @author shohrukhyakhyoev
 */
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

    /**
     * No-args constructor.
     */
    public BookItem() {
    }

    /**
     * Creates a book item object with given data fields.
     *
     * @param status represents book item's status.
     * @param ISBN represents book item's ISBN.
     */
    public BookItem(BookStatus status, Long ISBN) {
        this.status = status;
        this.ISBN = ISBN;
    }

    /**
     * Gets book item's barcode.
     *
     * @return a long value representing a book item's barcode.
     */
    public Long getBarcode() {
        return barcode;
    }

    /**
     * Sets a value to a book item's barcode data field.
     *
     * @param barcode represents a book item's barcode.
     */
    public void setBarcode(Long barcode) {
        this.barcode = barcode;
    }

    /**
     * Gets book item's status.
     *
     * @return a BookStatus value representing a book item's status.
     */
    public BookStatus getStatus() {
        return status;
    }

    /**
     * Sets a value to a book item's status data field.
     *
     * @param status represents a book item's status.
     */
    public void setStatus(BookStatus status) {
        this.status = status;
    }

    /**
     * Gets book item's ISBN.
     *
     * @return a long value representing a book item's ISBN.
     */
    public Long getISBN() {
        return ISBN;
    }

    /**
     * Sets a value to a book item's ISBN data field.
     *
     * @param ISBN represents a book item's ISBN.
     */
    public void setISBN(Long ISBN) {
        this.ISBN = ISBN;
    }
}
