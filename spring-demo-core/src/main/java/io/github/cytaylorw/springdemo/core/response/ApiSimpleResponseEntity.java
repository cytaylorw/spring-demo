package io.github.cytaylorw.springdemo.core.response;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.util.MultiValueMap;

import io.github.cytaylorw.springdemo.core.exception.ApiException;
import io.github.cytaylorw.springdemo.core.exception.ApiRuntimeException;

/**
 * Extension of {@link ResponseEntity} that use {@link SimpleResponseBody} as
 * the body.
 * 
 * @author Taylor
 *
 */
public class ApiSimpleResponseEntity extends ResponseEntity<SimpleResponseBody> {

    /**
     * Create a {@code ApiSimpleResponseEntity} with a status code only.
     * 
     * @param status the status code
     */
    public ApiSimpleResponseEntity(HttpStatusCode status) {
        super(null, null, status);
    }

    /**
     * Create a {@code ApiSimpleResponseEntity} with a body and status code.
     * 
     * @param body   the entity body
     * @param status the status code
     */
    public ApiSimpleResponseEntity(@Nullable SimpleResponseBody body, HttpStatusCode status) {
        super(body, null, status);
    }

    /**
     * Create a {@code ApiSimpleResponseEntity} with a body and status code.
     * 
     * @param body   the entity body
     * @param status the status code
     */
    public ApiSimpleResponseEntity(@Nullable SimpleResponseBody body, int status) {
        super(body, null, status);
    }

    /**
     * Create a {@code ApiSimpleResponseEntity} with headers and a status code.
     * 
     * @param headers the entity headers
     * @param status  the status code
     */
    public ApiSimpleResponseEntity(MultiValueMap<String, String> headers, HttpStatusCode status) {
        super(null, headers, status);
    }

    /**
     * Create a {@code ApiSimpleResponseEntity} with a body, headers, and a status
     * code.
     * 
     * @param body    the entity body
     * @param headers the entity headers
     * @param status  the status code
     */
    public ApiSimpleResponseEntity(@Nullable SimpleResponseBody body, @Nullable MultiValueMap<String, String> headers,
            HttpStatusCode status) {
        super(body, headers, status);
    }

    /**
     * Create a {@code ApiSimpleResponseEntity} with a body, headers, and a raw
     * status code.
     * 
     * @param body      the entity body
     * @param headers   the entity headers
     * @param rawStatus the status code value
     */
    public ApiSimpleResponseEntity(@Nullable SimpleResponseBody body, @Nullable MultiValueMap<String, String> headers,
            int rawStatus) {
        super(body, headers, rawStatus);
    }

    /**
     * Create a {@code ApiSimpleResponseEntity} with a {@link ResponseEntity}
     * 
     * @param entity the entity
     */
    public ApiSimpleResponseEntity(@Nullable ResponseEntity<SimpleResponseBody> entity) {
        super(entity.getBody(), entity.getHeaders(), entity.getStatusCode());
    }

    /**
     * Convert to {@code ApiResponseEntity}
     * 
     * @return
     */
    public ApiResponseEntity<?> toApiResponseEntity() {
        ResponseBody<?> body = ResponseBody.builder()
                .success(this.getBody().success)
                .code(this.getBody().code)
                .message(this.getBody().message)
                .build();
        return new ApiResponseEntity<>(body, this.getHeaders(), this.getStatusCode());
    }

    /**
     * Convert to {@link ApiException}
     * 
     * @return ApiException
     */
    public ApiException toApiException() {
        return new ApiException(this.toApiResponseEntity());
    }

    /**
     * Convert to {@link ApiException}
     * 
     * @param cause the cause
     * @return ApiException
     */
    public ApiException toApiException(Throwable cause) {
        return new ApiException(this.toApiResponseEntity(), cause);
    }

    /**
     * Convert to {@link ApiRuntimeException}
     * 
     * @return ApiRuntimeException
     */
    public ApiRuntimeException toApiRuntimeException() {
        return new ApiRuntimeException(this.toApiResponseEntity());
    }

    /**
     * Convert to {@link ApiRuntimeException}
     * 
     * @param cause the cause
     * @return ApiRuntimeException
     */
    public ApiRuntimeException toApiRuntimeException(Throwable cause) {
        return new ApiRuntimeException(this.toApiResponseEntity(), cause);
    }
}
