package com.iutlibrary.backend.bookStuff.bookItem;


import com.iutlibrary.backend.exception.ApiRequestException;
import com.iutlibrary.backend.utility.enums.BookStatus;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Serves as a service layer for requests associated with manipulation over a book item's data.
 *
 * @author shohrukhyakhyoev
 */
@Service
@Transactional
@AllArgsConstructor
public class BookItemService {

    /**
     * @field   repository    used to interact with data layer.
     */
    @Autowired
    private final BookItemRepository repository;

    /**
     * Adds new book item into the database.
     *
     * @param bookItem BookItem object.
     */
    public void addNewBookItem(BookItem bookItem) {
        repository.save(bookItem);
    }

    /**
     * Gets details of all book items that match to a given status.
     *
     * @param status represents a book item's status.
     * @return list of BookItem objects.
     */
    public List<BookItem> findBookItemByStatus(String status){
        return repository.findBookItemByStatus(status);
    }

    /**
     * Gets details of all book items of a particular book that match to a given status.

     * @param isbn represents book's isbn.
     * @param status represents book item's status.
     * @return
     */
    public List<BookItem> findTopByISBNAndStatus(Long isbn, BookStatus status) {
        return repository.findTopByISBNAndStatus(isbn, status);
    }

    /**
     * Gets details of a book item with a given barcode.
     *
     * @param barcode represents a book item's barcode.
     * @return BookItem object.
     */
    public Optional<BookItem> findByBarcode(Long barcode) {
        return repository.findByBarcode(barcode);
    }

    /**
     * Gets details of all book items of a particular isbn.
     *
     * @param isbn represents a book's isbn.
     * @return list of BookItem objects.
     */
    public List<BookItem> findBookByISBN(Long isbn) {
        return repository.findByISBN(isbn);
    }

    /**
     * Deletes a book item with a given barcode from the database.
     *
     * @param barcode represents a book item's barcode.
     * @return list of BookItem objects.
     */
    @Transactional
    public ResponseEntity<Object> deleteBookItem(Long barcode ) {
        repository.deleteById(barcode);
        return new ResponseEntity<>("BookItem is deleted", HttpStatus.OK);
    }

    /**
     * Updates status of a book item.
     *
     * @param barcode represents a book item's barcode.
     * @param newStatus represents a new status value of a book item.
     * @return ReponseEntity object.
     * @throws ApiRequestException
     */
    public ResponseEntity<Object> updateStatus(Long barcode, BookStatus newStatus) {
        if (repository.updateStatus(barcode, newStatus) == 0){
            throw new ApiRequestException("Failed to update status!");
        }

        return new ResponseEntity<>("Status has been successfully updated", HttpStatus.OK);
    }

}
