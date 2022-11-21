package com.iutlibrary.backend.bookStuff.bookItem;


import com.iutlibrary.backend.exception.ApiRequestException;
import com.iutlibrary.backend.utility.enums.BookStatus;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class BookItemService {

    @Autowired
    private final BookItemRepository repository;

    public void addNewBookItem(BookItem bookItem) {
        repository.save(bookItem);
    }

    public List<BookItem> findBookItemByStatus(String status){
        return repository.findBookItemByStatus(status);
    }

    public List<BookItem> findTopByISBNAndStatus(Long isbn, BookStatus status) {
        return repository.findTopByISBNAndStatus(isbn, status);
    }

    public Optional<BookItem> findByBarcode(Long barcode) {
        return repository.findByBarcode(barcode);
    }

    public List<BookItem> findBookByISBN(Long isbn) {
        return repository.findByISBN(isbn);
    }

    public ResponseEntity<Object> deleteBookItems(BookItem bookItem) {

        // TODO check whether delete is working, write check function
        repository.delete(bookItem);
        return new ResponseEntity<>("BookItems are deleted", HttpStatus.OK);
    }

    public ResponseEntity<Object> updateStatus(Long barcode, BookStatus newStatus) {
        if (repository.updateStatus(barcode, newStatus) == 0){
            throw new ApiRequestException("Failed to update status!");
        }

        return new ResponseEntity<>("Status has been successfully updated", HttpStatus.OK);
    }
}
