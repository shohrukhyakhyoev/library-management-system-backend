package com.iutlibrary.backend.fine;



import com.iutlibrary.backend.exception.ApiRequestException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class FineService {
    private final FineRepository repository;

    public void addNewFine(String studentId, String reason,
                           Long ISBN, Long barcode, Long amount, String title){
        repository.save(new Fine(studentId, reason, ISBN, barcode, amount, title));
    }
    public List<Fine> getAllFines() {
        return repository.findAll();
    }

    public List<Fine> getAllFinesOfStudent(String studentId){
        return repository.findFineByStudentId(studentId);
    }

    public List<Fine> getAllByReason(String reason) {
        return repository.getAllByReason(reason);
    }

    public Fine getAllByISBN(Long isbn) {
        return repository.findOneByISBN(isbn)
                .orElseThrow(() -> new ApiRequestException("Fine with given ISBN doesn't exist"));
    }

    public void deleteFine(Fine fine){
        repository.delete(fine);
    }

}
