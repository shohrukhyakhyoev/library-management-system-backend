package com.iutlibrary.backend.appUserDetails;

import com.iutlibrary.backend.utility.enums.AppUserRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;


/**
 * Represents an App User. Basically, app users are distinguished by app roles.
 * There are 3 roles: STUDENT, LIBRARIAN and ADMIN. Overall, all app users have
 * same data fields.
 *
 * @author shohrukhyakhyoev
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Account implements UserDetails {
    @Id
    @SequenceGenerator(
            name = "account_sequence",
            sequenceName = "account_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "account_sequence"
    )
    private Long id;
    @Column(unique = true, nullable = false)
    private String memberId;
    private String password;
    private String firstName;
    private String lastName;
    @Column(unique = true, nullable = false)
    private String email;
    @Enumerated(EnumType.STRING)
    private AppUserRole role;


    /**
     * Creates an account (app user) with specified data fields.
     *
     * @param memberId app user's id given from the university
     * @param password password of app user to use services at the university
     * @param firstName app user's first name
     * @param lastName app user's first name
     * @param email app user's university email
     * @param role app user's specified role
     */
    public Account(String memberId,
                   String password,
                   String firstName,
                   String lastName,
                   String email,
                   AppUserRole role) {
        this.memberId = memberId;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
    }

    /**
     * Returns list of authorities attached to a particular app user so that users access only
     * the features they are allowed to by the application. For example, this method is responsible
     * for students not having access to records of others students, which is allowed only for stuff
     * members.
     *
     * @return  list of authorities it allows app users to access certain features in the application
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority =
                new SimpleGrantedAuthority(role.name());
        return Collections.singletonList(authority);
    }

    /**
     * Gets a user's memberId
     * @return a string representing user's id
     */
    @Override
    public String getUsername() {
        return memberId;
    }

    /**
     * Checks if account is expired or not
     * @return a boolean value
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Checks if account is non-locked or not
     * @return a boolean value
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Checks if account's credentials are not expired
     * @return a boolean value
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Checks if account is enabled or not
     * @return a boolean value
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

}
