package com.iutlibrary.backend.appUserDetails;

import com.iutlibrary.backend.utility.enums.AppUserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * Serves as a data layer for manipulation over data of app users.
 * It allows application to contact with database with the help of
 * functions together with the specified SQL Query. To accomplish it
 * interface extends JpaRepository class. By this we use Spring Data
 * JPA Framework to map objects in database table. It is called ORM.
 *
 * @author shohrukhyakhyoev
 */
public interface AccountRepository extends JpaRepository<Account, Long> {
    /**
     * Gets all details of all account with specified app user's role.
     * @param role role of app user
     * @return list containing each account's details
     */
    @Query("SELECT a FROM Account a WHERE a.role = ?1")
    List<Account> findAllByRole(AppUserRole role);

    /**
     * Gets details of an account with specified id.
     * @param memberId id of an app user.
     * @return account containing the app user's details.
     */
    @Query("SELECT a FROM Account a WHERE a.memberId = ?1")
    Optional<Account> findByMemberId(String memberId);

    /**
     * Gets details of an account with specified id and email.
     * @param memberId id of an app user.
     * @param email email of an app user.
     * @return account containing the app user's details.
     */
    @Query("SELECT a FROM Account a WHERE a.memberId = ?1 OR a.email = ?2")
    Optional<Account> findByMemberIdOrEmail(String memberId, String email);

    /**
     * Updates details of an account with specified id.
     * @param email email of an app user.
     * @param firstName firstName of an app user.
     * @param lastName  lastName of an app user.
     * @param memberId id of an app user.
     * @return account containing the app user's details.
     */
    @Modifying
    @Query("UPDATE Account a SET a.email = ?1, a.firstName = ?2, a.lastName = ?3 WHERE a.memberId = ?4")
    long updateMember(String email, String firstName, String lastName, String memberId);

    /**
     * Deletes the account of an app user with the given id.
     * @param memberId id of an app user.
     * @return number of records affected after this operation.
     */
    long deleteAccountByMemberId(String memberId);
}
