package com.iutlibrary.backend.appUserDetails;

import com.iutlibrary.backend.utility.enums.AppUserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    // todo check whether string param will work
    @Query("SELECT a FROM Account a WHERE a.role = ?1")
    List<Account> findAllByRole(AppUserRole role);

    @Query("SELECT a FROM Account a WHERE a.memberId = ?1")
    Optional<Account> findByMemberId(String memberId);

    @Query("SELECT a FROM Account a WHERE a.memberId = ?1 OR a.email = ?2")
    Optional<Account> findByMemberIdOrEmail(String memberId, String email);

    // todo test if this works: delete
    @Query("DELETE FROM Account a WHERE a.memberId = ?1")
    Account deleteByMemberId(String memberId);
}
