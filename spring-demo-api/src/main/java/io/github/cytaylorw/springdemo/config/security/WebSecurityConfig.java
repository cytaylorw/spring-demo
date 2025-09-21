package io.github.cytaylorw.springdemo.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.ExceptionHandlingConfigurer;
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * WebSecurity configuration
 * 
 * @author Taylor Wong
 *
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

    /**
     * List of permit request patterns
     */
    private static final String[] PERMIT_PATTERNS = { "/login/**", "/swagger-ui/**", "/v3/api-docs*/**" };

    @Autowired
    @Qualifier("exceptionHandlerAuthenticationEntryPoint")
    private AuthenticationEntryPoint authEntryPoint;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    /**
     * SecurityFilterChain configuration
     * 
     * @param http
     * @return
     * @throws Exception
     */
	@Bean
	protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
		return http.csrf(this::setCsrf)
				.authorizeHttpRequests(this::setAuthorization)
                .exceptionHandling(this::setExceptionHandling)
				.sessionManagement(this::setSession)
				.httpBasic(Customizer.withDefaults())
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
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
                .requestMatchers(PERMIT_PATTERNS)
                .permitAll()
                .anyRequest()
                .authenticated();
	}

	/**
     * Session configuration
     * 
     * @param sessionManagementConfigurer
     */
	private void setSession(SessionManagementConfigurer<HttpSecurity> sessionManagementConfigurer) {
		sessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

    /**
     * Exception handling configuration
     *
     * @param exceptionHandlingConfigurer
     */
    private void setExceptionHandling(ExceptionHandlingConfigurer<HttpSecurity> exceptionHandlingConfigurer) {
        exceptionHandlingConfigurer.authenticationEntryPoint(this.authEntryPoint);
    }
}
