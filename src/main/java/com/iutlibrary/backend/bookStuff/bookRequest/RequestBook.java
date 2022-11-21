package com.iutlibrary.backend.bookStuff.bookRequest;

import javax.persistence.*;


@Entity
@Table
public class RequestBook {

    private String studentId;
    private Long ISBN;
    @Id
    @SequenceGenerator(
            name = "bookRequest_sequence",
            sequenceName = "bookRequest_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "bookRequest_sequence"
    )
    private Long id;

    public RequestBook() {
    }

    public RequestBook(String studentId, Long ISBN) {
        this.studentId = studentId;
        this.ISBN = ISBN;
    }

    public String getStudentId() {
        return studentId;
    }

    public Long getISBN() {
        return ISBN;
    }

}
