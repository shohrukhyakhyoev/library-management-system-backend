package com.iutlibrary.backend.image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Optional;

/**
 * Serves as a service layer for requests associated with manipulation over image's data.
 */
@Service
public class ImageService {

    /**
     * @field   repository  used to interact with data layer.
     */
    @Autowired
    private ImageRepository repository;

    /**
     * Uploads an image to the database.
     *
     * @param file represents an image file.
     * @param isbn represents an isbn value of a book.
     * @return ResponseEntity object.
     * @throws IOException
     */
    public ResponseEntity<String> uploadImage(MultipartFile file, Long isbn) throws IOException {

        repository.save(Image.builder()
                .ISBN(isbn)
                .imageData(ImageUtil.compressImage(file.getBytes())).build());

        return new ResponseEntity<>("Image uploaded successfully: " +
                file.getOriginalFilename(), HttpStatus.OK);

    }

    /**
     * Gets an image from the database.
     *
     * @param isbn representing a book's isbn.
     * @return byte[]
     */
    @Transactional
    public byte[] getImage(Long isbn) {
        Optional<Image> dbImage = repository.findById(isbn);
        return ImageUtil.decompressImage(dbImage.get().getImageData());
    }

    /**
     * Deletes an image with the given isbn from the database.
     *
     * @param isbn representing a book's isbn.
     */
    @Transactional
    public void deleteImage(Long isbn){
        repository.deleteById(isbn);
    }

}