package io.github.cytaylorw.springdemo.config.security;

import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * User context helper
 * 
 * @author Taylor Wong
 *
 */
@Component
public class JwtUserContext implements UserContext<JwtUserDetails> {

    @Override
    public Optional<JwtUserDetails> getCurrentUser() {
		SecurityContext context = SecurityContextHolder.getContext();
        return Optional.ofNullable(context.getAuthentication())
                .map(Authentication::getPrincipal)
                .filter(JwtUserDetails.class::isInstance)
                .map(JwtUserDetails.class::cast);
	}
}
