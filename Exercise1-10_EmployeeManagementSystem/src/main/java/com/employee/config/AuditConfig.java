package com.employee.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * Exercise 7: Auditing configuration.
 * Provides the current auditor (user) for @CreatedBy and @LastModifiedBy.
 */
@Configuration
public class AuditConfig {

    @Bean
    public AuditorAware<String> auditorAware() {
        // In a real app, this would return the authenticated user
        return () -> Optional.of("SYSTEM");
    }
}
