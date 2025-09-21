package io.github.cytaylorw.springdemo.config;

import java.io.IOException;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * 產生Request ID，並放入MDC和response header。
 *
 * @author Taylor
 */
@Slf4j
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RequestIdFilter extends OncePerRequestFilter {

    public static final String MDC_KEY = "REQUEST_ID";

    public static final String RESPONSE_HEADER_KEY = "REQUEST-ID";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        try {
            String requestId = UUID.randomUUID().toString().toUpperCase().replace("-", StringUtils.EMPTY);
            MDC.put(MDC_KEY, requestId);
            response.setHeader(RESPONSE_HEADER_KEY, requestId);
            log.debug("Request ID started for {} {}", request.getMethod(), request.getRequestURI());
            chain.doFilter(request, response);
        } finally {
            log.debug("Request ID finsihed for {} {}", request.getMethod(), request.getRequestURI());
            MDC.remove(MDC_KEY);
        }
    }
}
