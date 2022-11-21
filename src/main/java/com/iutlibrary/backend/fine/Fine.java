package com.iutlibrary.backend.fine;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    public Fine(String studentId, String reason, Long ISBN, Long barcode, Long amount) {
        this.studentId = studentId;
        this.reason = reason;
        this.ISBN = ISBN;
        this.barcode = barcode;
        this.amount = amount;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Long getISBN() {
        return ISBN;
    }

    public void setISBN(Long ISBN) {
        this.ISBN = ISBN;
    }
    public Long getBarcode() {
        return barcode;
    }

    public void setBarcode(Long barcode) {
        this.barcode = barcode;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
