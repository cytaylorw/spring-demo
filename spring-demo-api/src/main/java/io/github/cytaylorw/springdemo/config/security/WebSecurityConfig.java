package io.github.cytaylorw.springdemo.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

/**
 * WebSecurity configuration
 * 
 * @author Taylor
 *
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

	@Bean
	protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
		return http.csrf(this::setCsrf)
				.authorizeHttpRequests(this::setAuthorization)
				.sessionManagement(this::setSession)
				.httpBasic(Customizer.withDefaults())
				.build();
	}

	/**
     * CSRF Configuration
     * 
     * @param csrfConfigurer
     */
	private void setCsrf(CsrfConfigurer<HttpSecurity> csrfConfigurer) {
		csrfConfigurer.disable();
	}

	/**
     * Authorization Configuration
     * 
     * @param authorizationManagerRequestMatcherRegistry
     */
	private void setAuthorization(
			AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry authorizationManagerRequestMatcherRegistry) {
		authorizationManagerRequestMatcherRegistry
                .anyRequest()
                .permitAll();
	}

	/**
     * Session configuration
     * 
     * @param sessionManagementConfigurer
     */
	private void setSession(SessionManagementConfigurer<HttpSecurity> sessionManagementConfigurer) {
		sessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
}
