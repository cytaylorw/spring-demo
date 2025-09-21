package io.github.cytaylorw.springdemo.config.jpa;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * JPA auditor configuration
 * 
 * @author Taylor Wong
 *
 */
@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class PersistAuditConfig {

    /**
     * {@code auditorProvider} bean
     * 
     * @return
     */
    @Bean
    AuditorAware<String> auditorProvider() {
        return new DemoAuditorAware();
    }
}
