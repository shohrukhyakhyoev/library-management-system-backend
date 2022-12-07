package com.iutlibrary.backend.bookStuff.bookItem;


import com.iutlibrary.backend.utility.enums.BookStatus;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/bookItem")
public class BookItemController {

    private final BookItemService bookItemService;

    // fill table once librarian clicks the book
    // if list is empty, then empty table, no need for exception
    @GetMapping("/search/isbn")
    public List<BookItem> getBookItemsByISBN(@RequestParam("isbn") Long isbn){
        return bookItemService.findBookByISBN(isbn);
    }

    // fill table of bookItems in book with particular status
    @GetMapping("/search/status")
    public List<BookItem> getBookItemsByISBN(@RequestParam("isbn") Long isbn,
                                             @RequestParam("status") BookStatus status){
        return bookItemService.findTopByISBNAndStatus(isbn, status);
    }

    // for dashboard: front end gets size of list
    @GetMapping("/quantity")
    public Integer getQuantityOfBookItems(@RequestParam("status") String status){
        return bookItemService.findBookItemByStatus(status).size();
    }

    // select ONLY ONE bookItem: BORROWED is NOT ALLOWED TO CLICK.
    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteBookItem(@RequestParam("barcode") Long barcode){
        // todo statuses are same right? available or lost
        return bookItemService.deleteBookItem(barcode);
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateStatusBookItem(@RequestParam("barcode") Long barcode,
                                                       @RequestParam("newStatus") BookStatus newStatus){
        return bookItemService.updateStatus(barcode, newStatus);
    }

}
