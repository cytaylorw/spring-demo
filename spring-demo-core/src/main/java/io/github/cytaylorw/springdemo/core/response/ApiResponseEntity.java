package io.github.cytaylorw.springdemo.core.response;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.util.MultiValueMap;

import io.github.cytaylorw.springdemo.core.exception.ApiException;
import io.github.cytaylorw.springdemo.core.exception.ApiRuntimeException;

/**
 * Extension of {@link ResponseEntity} that use {@link ResponseBody} as the
 * body.
 * 
 * @author Taylor
 *
 * @param <D> the data type of {@link ResponseBody}
 */
public class ApiResponseEntity<D> extends ResponseEntity<ResponseBody<D>> {

    /**
     * Create a {@code ApiResponseEntity} with a status code only.
     * 
     * @param status the status code
     */
    public ApiResponseEntity(HttpStatusCode status) {
        super(null, null, status);
    }

    /**
     * Create a {@code ApiResponseEntity} with a body and status code.
     * 
     * @param body   the entity body
     * @param status the status code
     */
    public ApiResponseEntity(@Nullable ResponseBody<D> body, HttpStatusCode status) {
        super(body, null, status);
    }

    /**
     * Create a {@code ApiResponseEntity} with a body and status code.
     * 
     * @param body   the entity body
     * @param status the status code
     */
    public ApiResponseEntity(@Nullable ResponseBody<D> body, int status) {
        super(body, null, status);
    }

    /**
     * Create a {@code ApiResponseEntity} with headers and a status code.
     * 
     * @param headers the entity headers
     * @param status  the status code
     */
    public ApiResponseEntity(MultiValueMap<String, String> headers, HttpStatusCode status) {
        super(null, headers, status);
    }

    /**
     * Create a {@code ApiResponseEntity} with a body, headers, and a status code.
     * 
     * @param body    the entity body
     * @param headers the entity headers
     * @param status  the status code
     */
    public ApiResponseEntity(@Nullable ResponseBody<D> body, @Nullable MultiValueMap<String, String> headers,
            HttpStatusCode status) {
        super(body, headers, status);
    }

    /**
     * Create a {@code ApiResponseEntity} with a body, headers, and a raw status
     * code.
     * 
     * @param body      the entity body
     * @param headers   the entity headers
     * @param rawStatus the status code value
     */
    public ApiResponseEntity(@Nullable ResponseBody<D> body, @Nullable MultiValueMap<String, String> headers,
            int rawStatus) {
        super(body, headers, rawStatus);
    }

    /**
     * Create a {@code ApiResponseEntity} with a {@link ResponseEntity}
     * 
     * @param entity the entity
     */
    public ApiResponseEntity(@Nullable ResponseEntity<ResponseBody<D>> entity) {
        super(entity.getBody(), entity.getHeaders(), entity.getStatusCode());
    }

    /**
     * Convert to {@link ApiException}
     * 
     * @return ApiException
     */
    public ApiException toApiException() {
        return new ApiException(this);
    }

    /**
     * Convert to {@link ApiException}
     * 
     * @param cause the cause
     * @return ApiException
     */
    public ApiException toApiException(Throwable cause) {
        return new ApiException(this, cause);
    }

    /**
     * Convert to {@link ApiRuntimeException}
     * 
     * @return ApiRuntimeException
     */
    public ApiRuntimeException toApiRuntimeException() {
        return new ApiRuntimeException(this);
    }

    /**
     * Convert to {@link ApiRuntimeException}
     * 
     * @param cause the cause
     * @return ApiRuntimeException
     */
    public ApiRuntimeException toApiRuntimeException(Throwable cause) {
        return new ApiRuntimeException(this, cause);
    }
}
