package com.iutlibrary.backend.bookStuff.bookRequest;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class RequestBookService {
    @Autowired
    private final RequestBookRepository repository;

    public List<RequestBook> getAllBookRequests(){
        return repository.findAll();
    }

    public Optional<RequestBook> findByStudentIdAndISBN(String studentId, Long isbn) {
        return repository.findByStudentIdAndISBN(studentId, isbn);
    }

    public void addNewRequest(RequestBook requestBook) {
        repository.save(requestBook);
    }

    public List<Optional<RequestBook>> findRequestBookByISBN(Long isbn) {
        return repository.findRequestBookByISBN(isbn);
    }

    public void delete(RequestBook requestBook) {
        repository.delete(requestBook);
    }
}
