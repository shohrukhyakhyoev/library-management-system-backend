package com.iutlibrary.backend.bookStuff.bookItem;

import com.iutlibrary.backend.bookStuff.book.Book;
import com.iutlibrary.backend.exception.ApiRequestException;
import com.iutlibrary.backend.utility.enums.BookStatus;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/bookItem")
@CrossOrigin(maxAge = 3600)
public class BookItemController {

    private final BookItemService bookItemService;

    // for dashboard: front end gets size of list
    @GetMapping("/status")
    public List<BookItem> getBookItems(@RequestParam("status") String status){
        return bookItemService.findBookItemByStatus(status);
    }

    // fill table once librarian clicks the book
    // if list is empty, then empty table, no need for exception
    @GetMapping("/isbn")
    public List<BookItem> getBookItemsByISBN(@RequestParam("isbn") Long isbn){
        return bookItemService.findBookByISBN(isbn);
    }

    // fill table of bookItems in book with particular status
    @GetMapping("/isbn&status")
    public List<BookItem> getBookItemsByISBN(@RequestParam("isbn") Long isbn,
                                             @RequestParam("status") BookStatus status){
        return bookItemService.findTopByISBNAndStatus(isbn, status);
    }

    // select ONLY ONE bookItem with particular status and delete them
    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteBookItems(@RequestBody BookItem bookItem){
        // todo statuses are same right? available or lost
        return bookItemService.deleteBookItems(bookItem);
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateStatusBookItem(@RequestParam("barcode") Long barcode,
                                                       @RequestParam("newStatus") BookStatus newStatus){
        return bookItemService.updateStatus(barcode, newStatus);
    }


}
