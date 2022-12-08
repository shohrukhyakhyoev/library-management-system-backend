package com.iutlibrary.backend.fine;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Serves as a controller for requests associated with manipulation over a fine's data.
 * It listens to requests and then calls a certain function from the service layer.
 * Each method is mapped to a certain request type.
 */
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/fine")
public class FineController {

    /**
     * @field bookService an injected data field with the type of FineService.
     * Each method mapped to a particular client-request call a certain function from
     * service layer (FineService) using this data field.
     */
    private final FineService fineService;

    /**
     * Listens to a request asking to get all fine details in the database.
     *
     * @return list of Fine objects.
     */
    @GetMapping("/all")
    public List<Fine> getAllFines(){
        return fineService.getAllFines();
    }


    /**
     * Listens to a request asking to get all fine details of a particular student.
     *
     * @param studentId represents a student's id.
     * @return list of Fine objects.
     */
    @GetMapping("/search/student")
    public List<Fine> getAllFinesOfStudent(
            @RequestParam("studentId") String studentId){
        return fineService.getAllFinesOfStudent(studentId);
    }

    /**
     * Listens to a request asking to get all fine details of a particular reason.
     * Reason can be either Lost or Overdue.
     *
     * @param reason represents a fine's reason.
     * @return list of Fine objects.
     */    @GetMapping("/search/reason")
    public List<Fine> getAllFinesByReason(@RequestParam("reason") String reason){
        return fineService.getAllByReason(reason);
    }

    /**
     * Listens to a request asking to get all fine details of a particular ISBN.
     *
     * @param isbn represents a book's isbn.
     * @return list of Fine objects.
     */    @GetMapping("/search/isbn")
    public List<Fine> getAllFinesByISBN(@RequestParam("ISBN") Long isbn){
        return fineService.getAllByISBN(isbn);
    }

    /**
     * Deletes a fine object from the database.
     *
     * @param fine Fine object representing a fine details that will be deleted.
     */
    @DeleteMapping("/delete")
    public void deleteFine(@RequestBody Fine fine){
        fineService.deleteFine(fine);
    }

}
