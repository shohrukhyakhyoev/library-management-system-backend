package com.iutlibrary.backend.bookStuff.book;

import com.iutlibrary.backend.image.Image;
import com.iutlibrary.backend.image.ImageUtil;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.IOException;
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
    private Integer numberOfPages;
    private Long publicationDate;
    @Lob
    @Column(length = 1000)
    private byte[] imageData;

    private Boolean isAvailable;

    public Book(Long ISBN, String title,
                String subject, String author,
                String language, int numberOfPages,
                Long publicationDate) throws IOException {
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

    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public Long getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Long publicationDate) {
        this.publicationDate = publicationDate;
    }

    public byte[] getImageData() {
        return ImageUtil.decompressImage(imageData);
    }

    public void setImageData(byte[] imageData) {
        this.imageData = ImageUtil.compressImage(imageData);
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }
}
