package com.iutlibrary.backend.librarian;


import com.iutlibrary.backend.bookStuff.bookReservation.BookReservation;
import com.iutlibrary.backend.bookStuff.bookReserve.BookReserveRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/librarian")
public class LibrarianController {

    private final LibrarianService librarianService;

    @PostMapping ("/issueBook")
    public ResponseEntity<Object> issueBook(@RequestParam("decision") String decision,
                                            @RequestBody BookReserveRequest bookReserveRequest) {
        return librarianService.issueBook(decision, bookReserveRequest);
    }

    @PutMapping("/returnBook")
    public ResponseEntity<Object> returnBook(@RequestBody BookReservation bookReservation) {
        return librarianService.returnBook(bookReservation);
    }

}
