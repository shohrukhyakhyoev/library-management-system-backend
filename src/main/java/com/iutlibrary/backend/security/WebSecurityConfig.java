package com.iutlibrary.backend.security;

import com.iutlibrary.backend.appUserDetails.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final AccountService accountService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/v1/login")
                .permitAll()
                .antMatchers("/api/v1/registration")
                .permitAll()


                .antMatchers("/api/v1/book/all", "/api/v1/book/add", "/api/v1/bookItem/delete",
                        "/api/v1/bookItem/status", "/api/v1/bookItem/isbn", "/api/v1/bookItem/isbn&status",
                        "/api/v1/bookItem/delete", "/api/v1/bookItem/update", "/api/v1/reservation/all",
                        "/api/v1/reservation/status/", "/api/v1/reservation/barcode", "/api/v1/reserve/all",
                        "/api/v1/fine/all", "/api/v1/fine/all/reason", "/api/v1/fine/delete",
                        "/api/v1/librarian/issueBook", "/api/v1/librarian/returnBook" )
                .hasAuthority("LIBRARIAN")


                .antMatchers("/api/v1/book/one", "/api/v1/book/search/title", "/api/v1/book/search/author",
                        "/api/v1/reservation/one", "/api/v1/fine/one")
                .hasAnyAuthority("STUDENT", "LIBRARIAN")

                .antMatchers("/api/v1/student/reserveBook", "/api/v1/student/reportLost",
                        "/api/v1/reservation/student/active", "/api/v1//reservation/student/history" )
                .hasAuthority("STUDENT")

                .antMatchers("/api/v1/admin/all", "/api/v1/librarian/all", "/api/v1/admin/delete", "/api/v1/admin/add")
                .hasAuthority("ADMIN")

                .antMatchers("/api/v1/student")
                .hasAnyAuthority("ADMIN", "LIBRARIAN", "STUDENT")

                .antMatchers("/api/v1/librarian", "/api/v1/student/all")
                .hasAnyAuthority("LIBRARIAN", "ADMIN")

                .anyRequest()
                .authenticated().and()
                .httpBasic();

        http.authenticationProvider(daoAuthenticationProvider());

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(accountService);
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }


}