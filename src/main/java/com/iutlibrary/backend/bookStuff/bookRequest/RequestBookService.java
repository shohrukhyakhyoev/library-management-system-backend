package com.iutlibrary.backend.bookStuff.bookRequest;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Serves as a service layer for requests associated with manipulation over request book's data.
 *
 * @author shohrukhyakhyoev
 */
@Service
@Transactional
@AllArgsConstructor
public class RequestBookService {
    @Autowired
    private final RequestBookRepository repository;

    /**
     * Gets all requests in the database.
     *
     * @return list of RequestBook objects.
     */
    public List<RequestBook> getAllBookRequests(){
        return repository.findAll();
    }

    /**
     * Gets details a request book whose data fields match to the given params.
     *
     * @param studentId represents a student's id.
     * @param isbn represents a book's isbn.
     * @return RequestBook object.
     */
    public Optional<RequestBook> findByStudentIdAndISBN(String studentId, Long isbn) {
        return repository.findByStudentIdAndISBN(studentId, isbn);
    }

    /**
     * Adds new request book object into the database.
     *
     * @param requestBook represents RequestBook object.
     */
    public void addNewRequest(RequestBook requestBook) {
        repository.save(requestBook);
    }

    /**
     * Gets details of all request books in the database with the given isbn value.
     *
     * @param isbn represents a book's isbn.
     * @return list of RequestBook objects.
     */
    public List<Optional<RequestBook>> findRequestBookByISBN(Long isbn) {
        return repository.findRequestBookByISBN(isbn);
    }

    /**
     * Deletes a request book object from the database.
     *
     * @param requestBook represents RequestBook object.
     */
    public void delete(RequestBook requestBook) {
        repository.delete(requestBook);
    }
}
