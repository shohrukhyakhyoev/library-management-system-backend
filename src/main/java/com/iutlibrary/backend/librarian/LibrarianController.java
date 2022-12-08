package com.iutlibrary.backend.librarian;


import com.iutlibrary.backend.bookStuff.bookReservation.BookReservation;
import com.iutlibrary.backend.bookStuff.bookReserve.BookReserveRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Serves as a controller for requests coming from the librarian associated with manipulation
 * over a book's and student's data. It listens to requests and then calls a certain function
 * from the service layer. Each method is mapped to a certain request type.
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/librarian")
public class LibrarianController {

    /**
     * @field librarianService an injected data field with the type of LibrarianService.
     * Each method mapped to a particular client-request call a certain function from
     * service layer (LibrarianService) using this data field.
     */
    private final LibrarianService librarianService;

    /**
     * Listens to a request asking to issue a book to a particular user.
     *
     * @param decision  a string value representing a decision of a librarian about issuing a book.
     * @param bookReserveRequest represents a BookReserveRequest object containing details of reserve request.
     * @return ResponseEntity object.
     */
    @PostMapping ("/issueBook")
    public ResponseEntity<Object> issueBook(@RequestParam("decision") String decision,
                                            @RequestBody BookReserveRequest bookReserveRequest) {
        return librarianService.issueBook(decision, bookReserveRequest);
    }

    /**
     * Listens to a request asking to return a book from the student back to the database.
     * That means it terminates a student's reservation of a particular book.
     *
     * @param bookReservation a BookRepresentation containing a book reservation details of a student of a particular book.
     * @return ResponseEntity object.
     */
    @PutMapping("/returnBook")
    public ResponseEntity<Object> returnBook(@RequestBody BookReservation bookReservation) {
        return librarianService.returnBook(bookReservation);
    }

}
