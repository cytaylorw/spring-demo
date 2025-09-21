package io.github.cytaylorw.springdemo.core.security.jwt;

import java.time.Instant;
import java.util.Date;
import java.util.Map;
import java.util.function.Consumer;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;

/**
 * JWT Utility
 * 
 * @author Taylor
 *
 */
@Slf4j
public class JwtTokenUtil {

    private final JwtProperties jwtProperties;

    /**
     * Constructor
     * 
     * @param jwtProperties
     */
    public JwtTokenUtil(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
        this.jwtProperties.config();
        log.debug("JwtProperties: {}", this.jwtProperties);
    }

    /**
     * Return a JWT token string with given claims.
     * 
     * @param claimsSetter setter to set claims details
     * @return a JWT token string
     */
    public String generateToken(Consumer<Claims> claimsSetter) {

        long expiration = this.jwtProperties.getExpiration();
        Claims claims = Jwts.claims();
        claimsSetter.accept(claims);
        String jws = Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(Instant.now().toEpochMilli() + expiration * 1000))
                .signWith(this.jwtProperties.getSecretKey(), this.jwtProperties.getSignatureAlgorithm())
                .compact();
        log.debug("token: {}", jws);
        return jws;
    }

    /**
     * Return a JWT token string with given user details.
     * 
     * @param userDetails user details to be set as claims
     * @return a JWT token string
     */
    public String generateToken(Map<? extends String, ? extends Object> userDetails) {

        long expiration = this.jwtProperties.getExpiration();
        Claims claims = Jwts.claims();
        claims.putAll(userDetails);
        String jws = Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(Instant.now().toEpochMilli() + expiration * 1000))
                .signWith(this.jwtProperties.getSecretKey(), this.jwtProperties.getSignatureAlgorithm())
                .compact();
        log.debug("token: {}", jws);
        return jws;
    }

    /**
     * Return a JWS parsed from the given token.
     * 
     * @param token token to be parsed.
     * @return a JWS parsed from the given token.
     */
    public Jws<Claims> parseToken(String token) {
        Jws<Claims> jws = null;
        try {
            jws = Jwts.parserBuilder().setSigningKey(this.jwtProperties.getSecretKey()).build().parseClaimsJws(token);
        } catch (Exception e) {
            log.error("Error parsing access token.", e);
        }
        return jws;
    }
}
