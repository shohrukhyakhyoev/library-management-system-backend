package com.iutlibrary.backend.bookStuff.bookReserve;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Serves as a service layer for requests associated with manipulation over a book reserve request's data.
 */
@Service
@Transactional
@AllArgsConstructor
public class BookReserveService {
    /**
     * @field   repository  used to interact with data layer.
     */
    @Autowired
    private final BookReserveRequestRepository repository;

    /**
     * Gets all reserve requests in the database.
     *
     * @return list of BookReserveRequest objects.
     */
    public List<BookReserveRequest> getAllBookReserveRequest() {
        return repository.findAll();
    }

    /**
     * Adds new reserve request into the database.
     *
     * @param bookReserveRequest represents BookReserveRequest object.
     */
    public void addNewBookReserveRequest(BookReserveRequest bookReserveRequest) {
        repository.save(bookReserveRequest);
    }

    /**
     * Gets reserve request of a particular student with specific params.
     *
     * @param isbn represents a book's isbn.
     * @param studentId represents a student's id.
     * @return BookReserveRequest object.
     */
    public Optional<BookReserveRequest> findBookRequestByISBN(Long isbn, String studentId) {
        return repository.findBookRequestByISBN(isbn, studentId);
    }

    /**
     * Gets all reserve requests whose title data field matches to the given param.
     *
     * @param title represents a book's title.
     * @return list of BookReserveRequest objects.
     */
    public List<BookReserveRequest> findBookRequestByBookTitle(String title) {
        return repository.findBookRequestByBookTitle(title);
    }

    /**
     * Gets all reserve requests whose isbn data field matches to the given param.
     *
     * @param isbn represents a book's isbn.
     * @return list of BookReserveRequest objects.
     */
    public List<BookReserveRequest> findBookRequestByOnlyISBN(Long isbn) {
        return repository.findAllByISBN(isbn);
    }

    /**
     * Gets all reserve requests of one student.
     *
     * @param studentId represents a student's id.
     * @return list of BookReserveRequest objects.
     */
    public List<BookReserveRequest> findBookRequestByStudentId(String studentId){
        return repository.findByAllStudentId(studentId);
    }

    /**
     * Deletes a reserve request object from the database.
     *
     * @param bookReserveRequest represents a BookReserveRequest object which will be deleted.
     */
    @Transactional
    public void delete(BookReserveRequest bookReserveRequest) {
        repository.deleteBookReserveRequestByISBN(bookReserveRequest.getISBN());
    }

}
