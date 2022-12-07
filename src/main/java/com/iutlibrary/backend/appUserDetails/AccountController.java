package com.iutlibrary.backend.appUserDetails;


import com.iutlibrary.backend.utility.UserUpdateRequest;
import com.iutlibrary.backend.utility.enums.AppUserRole;
import com.iutlibrary.backend.utility.StudentBasicInfo;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Serves as a controller for requests associated with manipulation over app user's data.
 * It listens to requests and then calls a certain function from the service layer.
 * Each method is mapped to a certain request type.
 *
 * @author shohrukhyakhyoev
 */

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class AccountController {

    /**
     * @field accountService an injected data field with the type of AccountService.
     * Each method mapped to a particular client-request call a certain function from
     * service layer (AccountService) using this data field.
     */
    private final AccountService accountService;

    /**
     * Listens to a request asking all users' details.
     *
     * @param role specific role of an app user whose data will be fetched from the database.
     * @return list of Accpount objects.
     */
    @GetMapping("/users/all")
    public List<Account> getAllUsers(@RequestParam("role") AppUserRole role){
        return accountService.findAllByRole(role);
    }

    /**
     * Listens to a request asking a basic info of account wit a STUDENT role.
     *
     * @return list of StudentBasicInfo objects
     */
    @GetMapping("/students/info")
    public List<StudentBasicInfo> getStudentsBasicInfo(){
        return accountService.getStudentsIdAndNoOfReservedBooks();
    }

    /**
     * Listens to a request asking quantity of app users of a particular role.
     *
     * @param role a specific role of app user whose quantity in database will be returned.
     * @return Integer value representing a number of app users of a particular role.
     */
    @GetMapping("/quantity")
    public Integer getQuantityOfUsers(@RequestParam("role")AppUserRole role){
        return accountService.findAllByRole(role).size();
    }

    /**
     * Listens to a request asking a details of a particular student.
     *
     * @param studentId a string representing id of student. It is used to fetch the asked details of account from the database.
     * @return Account object of searched user.
     */
    @GetMapping("/search")
    public Account getOneStudent(@RequestParam(name = "memberId") String studentId){
        return accountService.findByMemberId(studentId);
    }

    /**
     * Listens to a request that asks to delete an account from the application
     *
     * @param memberId memberId of app user whose details will be deleted.
     * @return ResponseEntity object.
     */
    @DeleteMapping("/admin/delete")
    public ResponseEntity<Object> deleteAccount(@RequestParam(name = "memberId") String memberId){
        return accountService.deleteAccount(memberId);
    }

    /**
     * Listens to a request that asks to add a new account to the database.
     *
     * @param account Account object of new app user whose details will be added.
     * @return ResponseEntity object.
     */
    @PostMapping("/admin/add")
    public ResponseEntity<Object> addAccount(@RequestBody Account account){
        return accountService.addAccount(account);
    }

    /**
     * Listens to a request that asks to update details of existing account in the database.
     *
     * @param userUpdateRequest UserUpdateRequest object that contains new details of a particular
     * app user.
     * @return ResponseEntity object.
     */
    @PutMapping("/admin/update")
    public ResponseEntity<String> updateAppUser(@RequestBody UserUpdateRequest userUpdateRequest){
        return accountService.updateMember(userUpdateRequest);
    }

}
