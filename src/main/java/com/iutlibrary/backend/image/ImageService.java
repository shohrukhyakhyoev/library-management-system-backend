package com.iutlibrary.backend.image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Optional;

@Service
public class ImageService {

    @Autowired
    private ImageRepository repository;

    public ResponseEntity<String> uploadImage(MultipartFile file, Long isbn) throws IOException {

        repository.save(Image.builder()
                .ISBN(isbn)
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtil.compressImage(file.getBytes())).build());

        return new ResponseEntity<>("Image uploaded successfully: " +
                file.getOriginalFilename(), HttpStatus.OK);

    }
    

    @Transactional
    public byte[] getImage(Long isbn) {
        Optional<Image> dbImage = repository.findById(isbn);
        return ImageUtil.decompressImage(dbImage.get().getImageData());
    }


}