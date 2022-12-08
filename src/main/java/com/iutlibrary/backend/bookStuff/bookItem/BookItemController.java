package com.iutlibrary.backend.bookStuff.bookItem;


import com.iutlibrary.backend.utility.enums.BookStatus;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Serves as a controller for requests associated with manipulation over a book item's data.
 * It listens to requests and then calls a certain function from the service layer.
 * Each method is mapped to a certain request type.
 *
 * @author shohrukhyakhyoev
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/bookItem")
public class BookItemController {

    /**
     * @field bookItemService an injected data field with the type of BookItemService.
     * Each method mapped to a particular client-request call a certain function from
     * service layer (BookItemService) using this data field.
     */
    private final BookItemService bookItemService;

    /**
     * Listens to a request asking to details of book items with a specified ISBN.
     *
     * @param isbn represents a book's ISBN.
     * @return list of BookItem objects.
     */
    @GetMapping("/search/isbn")
    public List<BookItem> getBookItemsByISBN(@RequestParam("isbn") Long isbn){
        return bookItemService.findBookByISBN(isbn);
    }

    /**
     * Listens to a request asking to get details of book items of a specified book with
     * a certain status.
     *
     * @param isbn represents book's ISBN.
     * @param status represents book item's status.
     * @return list of BookItem objects
     */
    @GetMapping("/search/status")
    public List<BookItem> getBookItemsByISBN(@RequestParam("isbn") Long isbn,
                                             @RequestParam("status") BookStatus status){
        return bookItemService.findTopByISBNAndStatus(isbn, status);
    }

    /**
     * Gets a number of book items with a particular status.
     *
     * @param status represents a book item's status
     * @return an integer value representing a total number of book items (of a particular status) in the database.
     */
    @GetMapping("/quantity")
    public Integer getQuantityOfBookItems(@RequestParam("status") String status){
        return bookItemService.findBookItemByStatus(status).size();
    }

    /**
     * Deletes a book item with a given barcode.
     *
     * @param barcode represents a book item's barcode.
     * @return ResponseEntity object.
     */
    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteBookItem(@RequestParam("barcode") Long barcode){
        // todo statuses are same right? available or lost
        return bookItemService.deleteBookItem(barcode);
    }

    /**
     * Updates a book item's status.
     *
     * @param barcode represents a book item's barcode.
     * @param newStatus represents a book item's new status value.
     * @return ResponseEntity object.
     */
    @PutMapping("/update")
    public ResponseEntity<Object> updateStatusBookItem(@RequestParam("barcode") Long barcode,
                                                       @RequestParam("newStatus") BookStatus newStatus){
        return bookItemService.updateStatus(barcode, newStatus);
    }

}
