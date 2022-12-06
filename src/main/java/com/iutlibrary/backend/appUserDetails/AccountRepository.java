package com.iutlibrary.backend.appUserDetails;

import com.iutlibrary.backend.utility.enums.AppUserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    // todo check whether string param will work
    @Query("SELECT a FROM Account a WHERE lower(a.role) = lower(?1)")
    List<Account> findAllByRole(AppUserRole role);

    @Query("SELECT a FROM Account a WHERE a.memberId = ?1")
    Optional<Account> findByMemberId(String memberId);

    @Query("SELECT a FROM Account a WHERE a.memberId = ?1 OR a.email = ?2")
    Optional<Account> findByMemberIdOrEmail(String memberId, String email);

    @Modifying
    @Query("UPDATE Account a SET a.email = ?1, a.firstName = ?2, a.lastName = ?3")
    long updateMember(String email, String firstName, String lastName);

    long deleteAccountByMemberId(String memberId);
}
