package com.iutlibrary.backend.image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/image")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestBody MultipartFile file,
                                              @RequestParam("ISBN") Long isbn)
            throws IOException {
        return imageService.uploadImage(file, isbn);
    }

    @GetMapping("/get")
    public ResponseEntity<?>  getImageByName(@RequestParam("ISBN") Long isbn){
        byte[] image = imageService.getImage(isbn);

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(image);
    }

}
