package com.iutlibrary.backend.bookStuff.book;

import com.iutlibrary.backend.image.ImageUtil;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.IOException;


/**
 * Represents a book.
 *
 * @author shohrukhyakhyoev
 */
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
    private Integer numberOfPages;
    private Long publicationDate;
    @Lob
    @Column(length = 1000)
    private byte[] imageData;

    private Boolean isAvailable;

    /**
     * Creates a book with specified params.
     *
     * @param ISBN special id of a book.
     * @param title represents book's title.
     * @param subject represents book's subject/area.
     * @param author represents book's author.
     * @param language represents a language in which book has been written.
     * @param numberOfPages represents book's total number of pages
     * @param publicationDate represents book's date of publication
     */
    public Book(Long ISBN, String title,
                String subject, String author,
                String language, int numberOfPages,
                Long publicationDate) {
        this.ISBN = ISBN;
        this.title = title;
        this.subject = subject;
        this.author = author;
        this.language = language;
        this.numberOfPages = numberOfPages;
        this.publicationDate = publicationDate;
    }

    /**
     * Gets book's ISBN.
     * @return a long value representing book's ISBN.
     */
    public Long getISBN() {
        return ISBN;
    }

    /**
     * Sets a value to a book's ISBN data field.
     * @param ISBN a long value representing book's ISBN.
     */
    public void setISBN(Long ISBN) {
        this.ISBN = ISBN;
    }

    /**
     * Gets book's title.
     * @return a string value representing a book's title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets a value to a book's title data field.
     * @param title a string value representing a book's title.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets book's subject.
     * @return a string value representing a book's subject.
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Sets a value to a book's subject data field.
     * @param subject a string value representing a book's title.
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * Gets a book's author.
     * @return a string value representing a book's author.
     */
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Gets a book's language.
     * @return a string value representing a book's language.
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Sets a value to a book's language data field.
     * @param language a string value representing a book's language.
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * Gets a book's number of pages.
     * @return a integer value representing a book's number of pages.
     */
    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    /**
     * Sets a value to a book's numberOfPages data field.
     * @param numberOfPages a integer value representing a book's numberOfPages.
     */
    public void setNumberOfPages(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    /**
     * Gets a book's publication year.
     * @return a long value representing a book's publication year.
     */
    public Long getPublicationDate() {
        return publicationDate;
    }

    /**
     * Sets a value to a book's publicationDate data field.
     * @param publicationDate long value representing a book's publicationDate.
     */
    public void setPublicationDate(Long publicationDate) {
        this.publicationDate = publicationDate;
    }

    /**
     * Gets a book's image data.
     * @return byte values representing a book's image data.
     */
    public byte[] getImageData() {
        return ImageUtil.decompressImage(imageData);
    }

    /**
     * Sets a value to a book's imageData data field.
     * @param imageData byte values representing a book's imageData.
     */
    public void setImageData(byte[] imageData) {
        this.imageData = ImageUtil.compressImage(imageData);
    }

    /**
     * Gets a book's availability value.
     * @return a boolean value representing whether book is available or not.
     */
    public Boolean getAvailable() {
        return isAvailable;
    }

    /**
     * Sets a value to a book's available data field.
     * @param available a boolean value representing a book's availability.
     */
    public void setAvailable(Boolean available) {
        isAvailable = available;
    }
}
