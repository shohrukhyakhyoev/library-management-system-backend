package com.iutlibrary.backend.appUserDetails;


import com.iutlibrary.backend.utility.UserUpdateRequest;
import com.iutlibrary.backend.utility.enums.AppUserRole;
import com.iutlibrary.backend.utility.StudentBasicInfo;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/users/all")
    public List<Account> getAllAdmins(@RequestParam("role") AppUserRole role){
        return accountService.findAllByRole(role);
    }

    @GetMapping("/students/info")
    public List<StudentBasicInfo> getStudentsBasicInfo(){
        return accountService.getStudentsIdAndNoOfReservedBooks();
    }

    @GetMapping("/quantity")
    public Integer getQuantityOfAppUsers(@RequestParam("role")AppUserRole role){
        return accountService.findAllByRole(role).size();
    }

    @GetMapping("/search")
    public Account getOneStudent(@RequestParam(name = "memberId") String studentId){
        return accountService.findByMemberId(studentId);
    }

    @DeleteMapping("/admin/delete")
    public ResponseEntity<Object> deleteAccount(@RequestParam(name = "memberId") String memberId){
        return accountService.deleteAccount(memberId);
    }

    @PostMapping("/admin/add")
    public ResponseEntity<Object> addAccount(@RequestBody Account account){
        return accountService.addAccount(account);
    }

    @PutMapping("/admin/update")
    public ResponseEntity<String> updateAppUser(@RequestBody UserUpdateRequest userUpdateRequest){
        return accountService.updateMember(userUpdateRequest);
    }

}
