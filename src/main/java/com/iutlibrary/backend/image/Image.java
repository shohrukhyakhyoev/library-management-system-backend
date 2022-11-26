package com.iutlibrary.backend.image;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Image {
    @Id
    private Long ISBN;
    private String name;
    private String type;

    @Lob
    @Column(length = 1000)
    private byte[] imageData;
}
