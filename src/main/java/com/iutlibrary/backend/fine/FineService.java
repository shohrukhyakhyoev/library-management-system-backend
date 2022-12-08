package com.iutlibrary.backend.fine;


import com.iutlibrary.backend.exception.ApiRequestException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Serves as a service layer for requests associated with manipulation over fine's data.
 */
@Service
@Transactional
@AllArgsConstructor
public class FineService {
    /**
     * @field   repository  used to interact with data layer.
     */
    private final FineRepository repository;

    /**
     * Adds a new fine record into the database.
     *
     * @param studentId represents a student's id.
     * @param reason represents a fine's reason.
     * @param ISBN represents a book's isbn.
     * @param barcode represents a book item's barcode.
     * @param amount represents a fine's amount;
     * @param title represents a book's title;
     */
    public void addNewFine(String studentId, String reason,
                           Long ISBN, Long barcode, Long amount, String title){
        repository.save(new Fine(studentId, reason, ISBN, barcode, amount, title));
    }

    /**
     * Gets all fines in the database.
     *
     * @return list of Fine objects.
     */
    public List<Fine> getAllFines() {
        return repository.findAll();
    }

    /**
     * Gets all fines details of one student.
     *
     * @param studentId represents a student's id.
     * @return list of Fine objects.
     */
    public List<Fine> getAllFinesOfStudent(String studentId){
        return repository.findFineByStudentId(studentId);
    }

    /**
     * Gets all fines details of a particular reason.
     *
     * @param reason represents a fine's reason.
     * @return list of Fine objects.
     */
    public List<Fine> getAllByReason(String reason) {
        return repository.getAllByReason(reason);
    }

    /**
     * Gets all fines details of one isbn.
     *
     * @param isbn represents a book's isbn.
     * @return list of Fine objects.
     */
    public List<Fine> getAllByISBN(Long isbn) {
        List<Fine> fines = repository.findByISBN(isbn);
         if (fines.isEmpty()){
             throw new ApiRequestException("Fines with given ISBN don't exist");
         }

         return fines;
    }

    /**
     * Deletes a fine record from the database.
     *
     * @param fine Fine object representing a fine record in the database.
     */
    public void deleteFine(Fine fine){
        repository.delete(fine);
    }

}
