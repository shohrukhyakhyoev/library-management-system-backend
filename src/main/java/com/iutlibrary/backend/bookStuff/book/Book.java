package com.iutlibrary.backend.bookStuff.book;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Locale;

@Entity
@Table
@NoArgsConstructor
public class Book {
    @Id
    @SequenceGenerator(
            name = "book_sequence",
            sequenceName = "book_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "book_sequence"
    )
    Long id;
    @Column(unique = true)
    private Long ISBN;
    private String title;
    private String subject;
    private String author;
    private String language;
    private int numberOfPages;
    private LocalDate publicationDate;

    public Book(Long ISBN, String title,
                String subject, String author,
                String language, int numberOfPages,
                LocalDate publicationDate) {
        this.ISBN = ISBN;
        this.title = title;
        this.subject = subject;
        this.author = author;
        this.language = language;
        this.numberOfPages = numberOfPages;
        this.publicationDate = publicationDate;
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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }
}
