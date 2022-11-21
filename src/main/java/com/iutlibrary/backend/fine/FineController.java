package com.iutlibrary.backend.fine;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/fine")
public class FineController {

    private final FineService fineService;

    // all fines of all students in librarian fines
    @GetMapping("/all")
    public List<Fine> getAllFines(){
        return fineService.getAllFines();
    }

    // all fines of one student in student fines
    // also for search by studentId in librarian
    @GetMapping("/one")
    public List<Fine> getAllFinesOfStudent(
            @RequestParam("studentId") String studentId){
        return fineService.getAllFinesOfStudent(studentId);
    }

    // in librarian search by reason
    @GetMapping("/all/reason")
    public List<Fine> getAllFinesByReason(
            @RequestParam("reason") String reason){
        return fineService.getAllByReason(reason);
    }

    // deleting fine: for paid in librarian
    @DeleteMapping("/delete")
    public void deleteFine(
            @RequestBody Fine fine){
        fineService.deleteFine(fine);
    }

}
