package com.iutlibrary.backend.image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Serves as a controller for requests associated with manipulation over an image's data.
 * It listens to requests and then calls a certain function from the service layer.
 * Each method is mapped to a certain request type.
 */
@RestController
@RequestMapping("/api/v1/image")
public class ImageController {

    /**
     * @field bookService an injected data field with the type of ImageService.
     * Each method mapped to a particular client-request call a certain function from
     * service layer (ImageService) using this data field.
     */
    @Autowired
    private ImageService imageService;

    /**
     * Uploads a new image of a book to the database.
     * @param file represents a image file.
     * @param isbn represents a book's isbn.
     * @return ResponseEntity object.
     * @throws IOException
     */
    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("image") MultipartFile file,
                                              @RequestParam("ISBN") Long isbn)
            throws IOException {
        return imageService.uploadImage(file, isbn);
    }

}
