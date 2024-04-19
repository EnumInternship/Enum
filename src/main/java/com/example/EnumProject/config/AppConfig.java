package com.example.EnumProject.config;

import com.example.EnumProject.data.repository.CohortRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RequiredArgsConstructor
@Configuration
public class AppConfig {
    private final CohortRepository cohortRepository;

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> (UserDetails) cohortRepository.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("Cohort not found"));
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
