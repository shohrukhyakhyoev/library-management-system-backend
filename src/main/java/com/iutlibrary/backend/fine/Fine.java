package com.iutlibrary.backend.fine;


import lombok.NoArgsConstructor;
import javax.persistence.*;

/**
 * Represents a fine.
 */
@Entity
@Table
@NoArgsConstructor
public class Fine {

    @Id
    @SequenceGenerator(
            name = "fine_sequence",
            sequenceName = "fine_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "fine_sequence"
    )
    private Long id;
    private String studentId;
    private String reason;
    private Long ISBN;
    private Long barcode;
    private Long amount;
    private String title;

    /**
     * Creates a new fine object.
     *
     * @param studentId represents student's id.
     * @param reason represents reason why fine is being registered.
     * @param ISBN represents a book's isbn.
     * @param barcode represents a book item's barcode.
     * @param amount represents an amount of fine.
     * @param title  represents a book's title.
     */
    public Fine(String studentId, String reason, Long ISBN,
                Long barcode, Long amount, String title) {
        this.studentId = studentId;
        this.reason = reason;
        this.ISBN = ISBN;
        this.barcode = barcode;
        this.amount = amount;
        this.title = title;
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
     * Sets a value to a student's id.
     *
     * @param studentId a long value representing a student's id.
     */
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    /**
     * Gets a fine's reason.
     *
     * @return a string value representing a fine's reason.
     */
    public String getReason() {
        return reason;
    }

    /**
     * Sets a value to a fine's reason.
     *
     * @param reason a long value representing a fine's reason
     */
    public void setReason(String reason) {
        this.reason = reason;
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
     * Sets a value to a book's ISBN.
     *
     * @param ISBN a long value representing a book's ISBN
     */
    public void setISBN(Long ISBN) {
        this.ISBN = ISBN;
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
     * Sets a value to a book's title.
     *
     * @param barcode a long value representing a book item's barcode
     */
    public void setBarcode(Long barcode) {
        this.barcode = barcode;
    }

    /**
     * Gets a fine's amount.
     *
     * @return a long value representing a fine's amount.
     */
    public Long getAmount() {
        return amount;
    }

    /**
     * Sets a value to a fine's amount.
     *
     * @param amount a long value representing a fine's amount
     */
    public void setAmount(Long amount) {
        this.amount = amount;
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
     * @param title a string value representing a book's title
     */
    public void setTitle(String title) {
        this.title = title;
    }
}
