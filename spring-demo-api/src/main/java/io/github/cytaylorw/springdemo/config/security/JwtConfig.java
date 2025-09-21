package io.github.cytaylorw.springdemo.config.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.cytaylorw.springdemo.core.security.jwt.JwtProperties;
import io.github.cytaylorw.springdemo.core.security.jwt.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * JWT Configuration
 * 
 * @author Taylor Wong
 *
 */
@Slf4j
@Configuration
public class JwtConfig {

    /**
     * Properties for access token.
     * 
     * @return
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.jwt.token.access")
    protected JwtProperties accessTokenProperties() {
        return new JwtProperties();
    }

    /**
     * Properties for access token.
     * 
     * @return
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.jwt.token.refresh")
    protected JwtProperties refreshTokenProperties() {
        return new JwtProperties();
    }

    /**
     * Access token utility.
     * 
     * @param accessTokenProperties
     * @return
     */
    @Bean
    public JwtTokenUtil accessTokenUtil(JwtProperties accessTokenProperties) {
        log.debug("Creatig accessTokenUtil...");
        return new JwtTokenUtil(accessTokenProperties);
    }

    /**
     * Refresh token utility.
     * 
     * @param refreshTokenProperties
     * @return
     */
    @Bean
    public JwtTokenUtil refreshTokenUtil(JwtProperties refreshTokenProperties) {
        log.debug("Creatig refreshTokenUtil...");
        return new JwtTokenUtil(refreshTokenProperties);
    }
}
