package com.iutlibrary.backend.fine;

import com.iutlibrary.backend.fine.Fine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FineRepository extends JpaRepository<Fine, Long> {
    @Query("SELECT f FROM Fine f WHERE f.studentId = ?1")
    List<Fine> findFineByStudentId(String studentId);

    @Query("SELECT f FROM Fine f WHERE f.reason = ?1")
    List<Fine> getAllByReason(String reason);

    @Query("SELECT f FROM Fine f WHERE f.ISBN = ?1")
    Optional<Fine> findOneByISBN(Long isbn);
}
