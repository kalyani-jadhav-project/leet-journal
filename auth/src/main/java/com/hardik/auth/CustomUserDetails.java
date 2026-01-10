package com.hardik.auth;

import com.hardik.auth.repository.AuthRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetails {

    private final PasswordEncoder passwordEncoder;
    private final AuthRepository authRepository;
    private final JdbcUserDetailsManager jdbcUserDetailsManager;

    CustomUserDetails(PasswordEncoder passwordEncoder, AuthRepository authRepository, JdbcUserDetailsManager jdbcUserDetailsManager) {
        this.passwordEncoder = passwordEncoder;
        this.authRepository = authRepository;
        this.jdbcUserDetailsManager = jdbcUserDetailsManager;
    }

    public String newUser(String username, String password) throws UsernameNotFoundException {

        boolean existingUser = jdbcUserDetailsManager.userExists(username);
        IO.println("User: " + existingUser);
        if(existingUser) {
            return loginUser();
        }

        UserDetails user = User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .roles("USER")
                .build();

        jdbcUserDetailsManager.createUser(user);

        return user.getUsername();
    }

    public String loginUser() {
        return "ok";
    }
}
