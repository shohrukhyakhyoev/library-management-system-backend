package com.iutlibrary.backend.bookStuff.bookRequest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface RequestBookRepository extends JpaRepository<RequestBook, Long> {
    @Query("SELECT  rb FROM RequestBook rb WHERE rb.studentId = ?1 AND rb.ISBN = ?2")
    Optional<RequestBook> findByStudentIdAndISBN(String studentId, Long ISBN);

    @Query("SELECT rb FROM RequestBook rb WHERE rb.ISBN = ?1")
    List<Optional<RequestBook>> findRequestBookByISBN(Long ISBN);

}
