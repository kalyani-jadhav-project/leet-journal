package com.hardik.auth.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;

@SpringBootTest
@AutoConfigureMockMvc
class AuthSecurityConfigTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RegisteredClientRepository registeredClientRepository;

    @Autowired
    UserDetailsManager userDetailsManager;

    @Test
    void passwordEncoder_bean_created() {
        assertThat(passwordEncoder).isNotNull();
    }

    @Test
    void registeredClient_is_registered() {
        var client = registeredClientRepository.findByClientId("client");
        assertThat(client).isNotNull();
        assertThat(client.getClientId()).isEqualTo("client");
    }

    @Test
    void default_users_are_created() {
        assertThat(userDetailsManager.userExists("hardik")).isTrue();
        assertThat(userDetailsManager.userExists("xane")).isTrue();
        assertThat(userDetailsManager.userExists("sim")).isTrue();
    }

    @Test
    void login_with_valid_credentials_succeeds() throws Exception {
        mockMvc.perform(formLogin("/login")
                        .user("hardik")
                        .password("pass"))
                .andExpect(authenticated());
    }

    @Test
    void login_with_invalid_credentials_fails() throws Exception {
        mockMvc.perform(formLogin("/login")
                        .user("hardik")
                        .password("wrongpassword"))
                .andExpect(unauthenticated());
    }
}