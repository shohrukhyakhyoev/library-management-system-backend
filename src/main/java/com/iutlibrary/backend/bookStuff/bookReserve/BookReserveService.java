package com.iutlibrary.backend.bookStuff.bookReserve;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class BookReserveService {
    @Autowired
    private final BookReserveRequestRepository repository;

    public List<BookReserveRequest> getAllBookReserveRequest() {
        return repository.findAll();
    }
    public void addNewBookReserveRequest(BookReserveRequest bookReserveRequest) {
        repository.save(bookReserveRequest);
    }

    public Optional<BookReserveRequest> findBookRequestByISBN(Long isbn, String studentId) {
        return repository.findBookRequestByISBN(isbn, studentId);
    }

    @Transactional
    public void delete(BookReserveRequest bookReserveRequest) {
        repository.deleteBookReserveRequestByISBN(bookReserveRequest.getISBN());
    }
}
