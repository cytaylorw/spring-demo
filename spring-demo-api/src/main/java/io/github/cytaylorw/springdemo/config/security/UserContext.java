package io.github.cytaylorw.springdemo.config.security;

import java.util.Optional;

/**
 * User context helper interface
 * 
 * @author Taylor Wong
 *
 * @param <U> type of the user object
 */
public interface UserContext<U> {

	/**
     * Get current user details
     * 
     * @return current user details
     */
    public Optional<U> getCurrentUser();
}
