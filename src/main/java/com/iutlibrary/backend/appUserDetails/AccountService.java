package com.iutlibrary.backend.appUserDetails;


import com.iutlibrary.backend.exception.ApiRequestException;
import com.iutlibrary.backend.registration.EmailValidator;
import com.iutlibrary.backend.utility.enums.AppUserRole;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class AccountService implements UserDetailsService {

    private final AccountRepository repository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final EmailValidator emailValidator;

    @Override
    public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
        Optional<Account> account = repository.findByMemberId(memberId);
        
        if (account.isPresent()){
            return account.get();
        }
        
        throw new ApiRequestException(String.format("User with Id '%s' is not found.", memberId));
    }

    public List<Account> findAllByRole(AppUserRole role) {
        return repository.findAllByRole(role);
    }

    public Account findByMemberId(String memberId){
        Optional<Account> account = repository.findByMemberId(memberId);
        
        if (account.isPresent()){
            return account.get();
        }

        throw new ApiRequestException(String.format("User with Id '%s' is not found.", memberId));
    }

    public ResponseEntity<Object> addAccount(Account account) {
        if (repository.findByMemberIdOrEmail(account.getMemberId(), account.getEmail())
                .isPresent()){
            throw new ApiRequestException("This MemberId or Email already exists.");
        }

        if (!emailValidator.isValid(account.getEmail())) {
            throw new ApiRequestException("Email isn't valid.");
        }

        account.setPassword(passwordEncoder.encode(account.getPassword()));
        repository.save(account);
        
        repository.findByMemberId(account.getMemberId())
                .orElseThrow(() -> new ApiRequestException("New app user addition failed!"));
        
        return new ResponseEntity<>("New user has been successfully added.", HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<Object> deleteAccount(String memberId){
        long isDeleted = repository.deleteAccountByMemberId(memberId);
        
         if (isDeleted == 0L){
             throw new ApiRequestException("Deletion failed!");
         }
        
        return new ResponseEntity<>("Successfully deleted.", HttpStatus.OK);
    }
    

    public ResponseEntity<Object> singUpUser(Account account) {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        repository.save(account);
        return new ResponseEntity<>("New account has been added", HttpStatus.ACCEPTED);
    }
}
