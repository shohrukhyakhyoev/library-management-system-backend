package com.iutlibrary.backend.bookStuff.bookRequest;

import javax.persistence.*;


/**
 * Represents a request to a book.
 */
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

    /**
     * No-args constructor.
     */
    public RequestBook() {
    }

    /**
     * Creates new RequestBook object with specified data fields.
     *
     * @param studentId represents a student's id.
     * @param ISBN represents a book's isbn.
     */
    public RequestBook(String studentId, Long ISBN) {
        this.studentId = studentId;
        this.ISBN = ISBN;
    }

    /**
     * Gets a studentId.
     *
     * @return a string value representing a student id.
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * Gets a book's isbn.
     *
     * @return a long value representing an isbn.
     */
    public Long getISBN() {
        return ISBN;
    }

}
