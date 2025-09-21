package io.github.cytaylorw.springdemo.config.security;

import java.io.IOException;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.github.cytaylorw.springdemo.core.security.jwt.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * JWT Authentication
 * 
 * @author Taylor Wong
 *
 */
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired
    private JwtTokenUtil accessTokenUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		final String requestTokenHeader = request.getHeader("Authorization");

        Consumer<UserDetails> setAuthentication = user -> {
			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
					user, null, user.getAuthorities());
			usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

			SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			logger.debug("setAuthentication: " + user.getUsername());
		};

        Optional.ofNullable(requestTokenHeader)
                .filter(this::isBearer)
                .map(this::getBearerToken)
                .map(accessTokenUtil::parseToken)
                .map(Jws<Claims>::getBody)
                .filter(claims -> Objects.nonNull(claims.getSubject()))
				.filter(claims -> Objects.isNull(SecurityContextHolder.getContext().getAuthentication()))
                .map(this::toUserDetails)
				.ifPresent(setAuthentication);

		chain.doFilter(request, response);
	}

    private boolean isBearer(String value) {
        return value.startsWith("Bearer ");
    }

    private String getBearerToken(String value) {
        return value.substring(7);
    }

    private UserDetails toUserDetails(Claims claims) {
//        UserBuilder builder = User.withUsername(claims.getSubject());
//        Optional.ofNullable(claims.get("roles"))
//                .filter(Collection.class::isInstance)
//                .map(Collection.class::cast)
//                .map(this::toGrantedAuthority)
//                .ifPresent(builder::authorities);
//        return builder.build();\
        return new JwtUserDetails(claims);
    }

    private Collection<GrantedAuthority> toGrantedAuthority(Collection<?> roles) {
        return roles.stream()
                .map(String.class::cast)
                .map(SimpleGrantedAuthority::new)
                .map(GrantedAuthority.class::cast)
                .toList();
    }
}
