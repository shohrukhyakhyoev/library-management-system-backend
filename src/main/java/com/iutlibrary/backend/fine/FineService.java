package com.iutlibrary.backend.fine;



import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class FineService {
    private final FineRepository repository;

    public void addNewFine(String studentId, String reason,
                           Long ISBN, Long barcode, Long amount){
        repository.save(new Fine(studentId, reason, ISBN, barcode, amount));
    }
    public List<Fine> getAllFines() {
        return repository.findAll();
    }

    public List<Fine> getAllFinesOfStudent(String studentId){
        return repository.findFineByStudentId(studentId);
    }

    public void deleteFine(Fine fine){
        repository.delete(fine);
    }

    public List<Fine> getAllByReason(String reason) {
        return repository.getAllByReason(reason);
    }
}
