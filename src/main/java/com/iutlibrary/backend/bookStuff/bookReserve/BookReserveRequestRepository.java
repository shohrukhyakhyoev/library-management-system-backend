package com.iutlibrary.backend.bookStuff.bookReserve;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BookReserveRequestRepository extends JpaRepository<BookReserveRequest, Long> {
    @Query("SELECT b FROM BookReserveRequest b WHERE b.ISBN = ?1 AND b.studentId = ?2")
    Optional<BookReserveRequest> findBookRequestByISBN(Long ISBN, String studentId);

    long deleteBookReserveRequestByISBN(Long ISBN);

}

