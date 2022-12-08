package com.iutlibrary.backend.image;

import lombok.*;

import javax.persistence.*;

/**
 * Represents an image.
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Image {

    /**
     * @field isbn  represents a book's isbn.
     * @field imageData represents image in byte[] type.
     */
    @Id
    private Long ISBN;
    @Lob
    @Column(length = 1000)
    private byte[] imageData;
}
