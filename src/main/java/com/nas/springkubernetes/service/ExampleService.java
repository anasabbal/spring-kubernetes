package com.nas.springkubernetes.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.Arrays;

@Slf4j
@Configuration
public class ExampleService {

    @Value("${user.username:user}")
    private String userUsername;

    @Value("${user.password:secret}")
    private String userPassword;

    @Value("${admin.username:admin}")
    private String adminUsername;

    @Value("${admin.password:secret}")
    private String adminPassword;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public UserDetailsService helloUserDetailsService() {
        log.info("Password for user '{}': '{}'", userUsername, userPassword);
        log.info("Password for user '{}': '{}'", adminUsername, adminPassword);
        return new InMemoryUserDetailsManager(
                Arrays.asList(
                        User.withUsername(userUsername).password(passwordEncoder().encode(userPassword)).roles("USER").build(),
                        User.withUsername(adminUsername).password(passwordEncoder().encode(adminPassword)).roles("USER", "ADMIN").build()
                )
        );
    }
}
