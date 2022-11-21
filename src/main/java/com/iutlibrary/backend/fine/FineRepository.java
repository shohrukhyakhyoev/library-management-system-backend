package com.iutlibrary.backend.fine;

import com.iutlibrary.backend.fine.Fine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FineRepository extends JpaRepository<Fine, Long> {
    @Query("SELECT f FROM Fine f WHERE f.studentId = ?1")
    List<Fine> findFineByStudentId(String studentId);

    @Query("SELECT f FROM Fine f WHERE f.studentId = ?1 AND f.barcode = ?2 AND f.reason = ?3")
    Fine findByStudentIdAndBarcodeAndReason(String studentId, Long barcode, String reason);

    @Query("SELECT f FROM Fine f WHERE f.reason = ?1")
    List<Fine> getAllByReason(String reason);
}
