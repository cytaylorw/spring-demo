package io.github.cytaylorw.springdemo.config.jpa;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;

import io.github.cytaylorw.springdemo.config.security.JwtUserContext;
import io.github.cytaylorw.springdemo.config.security.JwtUserDetails;

/**
 * {@link AuditorAware} implementation.
 * 
 * @author Taylor Wong
 *
 */
public class DemoAuditorAware implements AuditorAware<String> {

    /**
     * Default username
     */
    public static final String DEFAULT_USERNAME = "sys";

    @Autowired
    private JwtUserContext context;


    @Override
    public Optional<String> getCurrentAuditor() {
        String username = context.getCurrentUser().map(JwtUserDetails::getUsername).orElse(DEFAULT_USERNAME);
        return Optional.of(username);
    }

}
