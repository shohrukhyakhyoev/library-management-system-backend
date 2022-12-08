package com.iutlibrary.backend.appUserDetails;


import com.iutlibrary.backend.bookStuff.bookReservation.BookReservationService;
import com.iutlibrary.backend.exception.ApiRequestException;
import com.iutlibrary.backend.registration.EmailValidator;
import com.iutlibrary.backend.utility.UserUpdateRequest;
import com.iutlibrary.backend.utility.enums.AppUserRole;
import com.iutlibrary.backend.utility.enums.ReservationStatus;
import com.iutlibrary.backend.utility.StudentBasicInfo;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

/**
 * Serves as a service layer for requests associated with manipulation over app user's data.
 *
 * @author shohrukhyakhyoev
 */
@Service
@AllArgsConstructor
public class AccountService implements UserDetailsService {

    /**
     * @field   repository    used to interact with data layer.
     * @field   passwordEncoder encodes app user's password.
     * @field   emailValidator  validates whether email is valid or not.
     * @field bookReservationService  used to call methods of BookReservationService class.
     */
    private final AccountRepository repository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final EmailValidator emailValidator;
    private final BookReservationService bookReservationService;

    /**
     * Loads an app user once the given memberId and password match to the values in the database.
     *
     * @param memberId represents an id of an app user at university.
     * @return UserDetails object which contains all details of an app user.
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
        Optional<Account> account = repository.findByMemberId(memberId);

        if (account.isPresent()) {
            return account.get();
        }
        throw new ApiRequestException(String.format("User with Id '%s' is not found.", memberId));
    }

    /**
     * Gets details of all app users with a specified role.
     *
     * @param role represents a particular role of an app user.
     * @return list containing details of app users.
     * @throws ApiRequestException
     */
    public List<Account> findAllByRole(AppUserRole role) {
        return repository.findAllByRole(role);
    }

    public Account findByMemberId(String memberId) {
        return repository.findByMemberId(memberId)
                .orElseThrow(() -> new ApiRequestException
                        (String.format("User with Id '%s' is not found.", memberId)));

    }

    /**
     * Adds details of a new app users to the database.
     *
     * @param account object of a new app user containing all details.
     * @return ResponseEntity object.
     * @throws ApiRequestException
     */
    public ResponseEntity<Object> addAccount(Account account) {
        if (repository.findByMemberIdOrEmail(account.getMemberId(), account.getEmail())
                .isPresent()) {
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

    /**
     * Deletes details of existing app users from the database.
     *
     * @param memberId id of existing app user whose details will be deleted.
     * @return ResponseEntity object.
     * @throws ApiRequestException
     */
    @Transactional
    public ResponseEntity<Object> deleteAccount(String memberId) {
        long isDeleted = repository.deleteAccountByMemberId(memberId);

        if (isDeleted == 0L) {
            throw new ApiRequestException("Deletion failed!");
        }

        return new ResponseEntity<>("Successfully deleted.", HttpStatus.OK);
    }

    /**
     * Gets memberId and number of reserved books of each student in the database.
     *
     * @return list containing details of each student.
     */
    public List<StudentBasicInfo> getStudentsIdAndNoOfReservedBooks(){
        List<StudentBasicInfo> result = new ArrayList<>();

        List<Account> accounts = repository.findAllByRole(AppUserRole.STUDENT);

        accounts.forEach(account -> {
            String studentId = account.getMemberId();

            Integer noOfReservedBooks = bookReservationService.findInUseById(
                    studentId, ReservationStatus.COMPLETED).size();

            result.add(StudentBasicInfo.builder()
                    .noOfInUseBooks(noOfReservedBooks)
                    .studentId(studentId)
                    .build());
        });

        return result;
    }

    /**
     * Adds details of a new app users to the database.
     *
     * @param account object of a new app user containing all details.
     * @return ResponseEntity object.
     */
    public ResponseEntity<Object> singUpUser(Account account) {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        repository.save(account);
        return new ResponseEntity<>("New account has been added", HttpStatus.ACCEPTED);
    }

    /**
     * Updates details of a certain app user.
     *
     * @param userUpdateRequest represents request containing all data fields that are updatable.
     * So application only accepts this class's object to update existing account's detail.
     * @return ResponseEntity object.
     * @throws ApiRequestException
     */
    public ResponseEntity<String> updateMember(UserUpdateRequest userUpdateRequest) {
        if (repository.updateMember(userUpdateRequest.getEmail(),
                userUpdateRequest.getFirstName(), userUpdateRequest.getLastName(),
                userUpdateRequest.getMemberId()) == 0){
            throw new ApiRequestException("User's details are unsuccessfully updated!");
        }

        return new ResponseEntity<>("User's details are updated successfully", HttpStatus.OK);
    }
}
