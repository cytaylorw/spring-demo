package io.github.cytaylorw.springdemo.core.security.jwt;

import java.nio.charset.StandardCharsets;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * JWT configuration properties
 * 
 * @author Taylor
 *
 */
@ToString
@Getter
@Setter
public class JwtProperties {
    /**
     * Expiration time in seconds
     */
    private long expiration = 60;

    /**
     * Secret key
     */
    private String secret = "spring.demo.jwt.secret";

    /**
     * Algorithm name
     */
    private String algorithm = "HS256";

    /**
     * {@link SecretKey}
     */
    private SecretKey secretKey;

    /**
     * {@link SignatureAlgorithm}
     */
    private SignatureAlgorithm signatureAlgorithm;

    /**
     * Set and validate {@link SecretKey} and {@link SignatureAlgorithm}
     */
    public void config() {

        SecretKey key = Keys.hmacShaKeyFor(this.secret.getBytes(StandardCharsets.UTF_16));
        SignatureAlgorithm algorithm = SignatureAlgorithm.forName(this.algorithm);

        // validate
        Jwts.builder().signWith(key, algorithm).compact();

        this.secretKey = key;
        this.signatureAlgorithm = algorithm;
    }
}
