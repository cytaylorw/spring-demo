package io.github.cytaylorw.springdemo.core.response;

import org.springframework.http.ResponseEntity;

import io.github.cytaylorw.springdemo.core.exception.ApiException;
import io.github.cytaylorw.springdemo.core.exception.ApiRuntimeException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseMessage {

    /**
     * DEFAULT_SUCCESS(true, 200, "default.success",
     * {@link Description#LOGIN_SUCCESS}),
     */
    DEFAULT_SUCCESS(true, 200, "default.success", Description.DEFAULT_SUCCESS),
    /**
     * LOGIN_SUCCESS(true, 200, "login.success", {@link Description#LOGIN_SUCCESS}),
     */
    LOGIN_SUCCESS(true, 200, "login.success", Description.LOGIN_SUCCESS),
    /**
     * DEFAULT_QUERY_SUCCESS(true, 200, "default.query.success",
     * {@link Description#v}),
     */
    DEFAULT_QUERY_SUCCESS(true, 200, "default.query.success", Description.DEFAULT_QUERY_SUCCESS),
    /**
     * DEFAULT_CREATE_SUCCESS(true, 200, "default.create.success",
     * {@link Description#DEFAULT_CREATE_SUCCESS}),
     */
    DEFAULT_CREATE_SUCCESS(true, 200, "default.create.success", Description.DEFAULT_CREATE_SUCCESS),
    /**
     * DEFAULT_UPDATE_SUCCESS(true, 200, "default.update.success",
     * {@link Description#DEFAULT_UPDATE_SUCCESS}),
     */
    DEFAULT_UPDATE_SUCCESS(true, 200, "default.update.success", Description.DEFAULT_UPDATE_SUCCESS),
    /**
     * DEFAULT_DELETE_SUCCESS(true, 200, "default.delete.success",
     * {@link Description#DEFAULT_DELETE_SUCCESS}),
     */
    DEFAULT_DELETE_SUCCESS(true, 200, "default.delete.success", Description.DEFAULT_DELETE_SUCCESS),
    /**
     * UNAUTHORIZED_ERROR(false, 401, "unauthorized.error", "Unauthorized."),
     */
    UNAUTHORIZED_ERROR(false, 401, "unauthorized.error", "Unauthorized."),
    /**
     * LOGIN_FAILED(false, 401, "login.failed", "Login failed."),
     */
    LOGIN_FAILED(false, 401, "login.failed", "Login failed."),
    /**
     * DATA_NOT_FOUND(false, 404, "data.not.found", "Data not found."),
     */
    DATA_NOT_FOUND(false, 404, "data.not.found", "Data not found."),
    /**
     * DEFAULT_BAD_REQUEST_ERROR(false, 400, "default.bad.request.error", "Bad
     * requests."),
     */
    DEFAULT_BAD_REQUEST_ERROR(false, 400, "default.bad.request.error", "Bad requests."),
    /**
     * PROPERTY_NOT_FOUND(false, 400, "property.not.found", "No property '{0}'
     * found."),
     */
    PROPERTY_NOT_FOUND(false, 400, "property.not.found", "No property ''{0}'' found."),
    /**
     * DEFAULT_SYSTEM_ERROR(false, 500, "default.system.error", "System encountered
     * an error.");
     */
    DEFAULT_SYSTEM_ERROR(false, 500, "default.system.error", "System encountered an error.");

    /**
     * Is success?
     */
    private boolean success;

    /**
     * HTTP Status
     */
    private int status;

    /**
     * Response code
     */
    private String code;

    /**
     * Response message
     */
    private String message;

    /**
     * Convert to {@link ResponseEntity}<{@link SimpleResponseBody}> for Swagger.
     * 
     * @param data the data of {@link ResponseBody}
     * @return the response entity
     */
    public ApiSimpleResponseEntity toApiSimpleResponseEntity() {
        SimpleResponseBody body = ResponseBody.simpleBuiler().success(success).code(code).message(message).build();
        return new ApiSimpleResponseEntity(body, status);
    }

    /**
     * Convert to {@link ResponseEntity}<{@link SimpleResponseBody}> for Swagger.
     * 
     * @param formatter
     * @return the response entity
     */
    public ApiSimpleResponseEntity toApiSimpleResponseEntity(MessageFormatter formatter) {
        SimpleResponseBody body = ResponseBody.simpleBuiler()
                .success(success)
                .code(code)
                .message(message)
                .build()
                .formatMessage(formatter);
        return new ApiSimpleResponseEntity(body, status);
    }

    /**
     * Convert to {@link ResponseEntity}<{@link SimpleResponseBody}> for Swagger.
     * 
     * @param formatter
     * @return the response entity
     */
    public ApiSimpleResponseEntity toApiSimpleResponseEntity(MessageCodeFormatter formatter) {
        SimpleResponseBody body = ResponseBody.simpleBuiler()
                .success(success)
                .code(code)
                .message(message)
                .build()
                .formatMessage(formatter);
        return new ApiSimpleResponseEntity(body, status);
    }

    /**
     * Convert to
     * {@link ResponseEntity}<{@link ResponseBody}{@literal <}?{@literal >}>
     * 
     * @param data the data of {@link ResponseBody}
     * @return the response entity
     */
    public ApiResponseEntity<?> toApiResponseEntity() {
        ResponseBody<?> body = ResponseBody.builder().success(success).code(code).message(message).build();
        return new ApiResponseEntity<>(body, status);
    }

    /**
     * Convert to
     * {@link ResponseEntity}<{@link ResponseBody}{@literal <}?{@literal >}>
     * 
     * @param formatter
     * @return the response entity
     */
    public ApiResponseEntity<?> toApiResponseEntity(MessageFormatter formatter) {
        ResponseBody<?> body = ResponseBody.builder()
                .success(success)
                .code(code)
                .message(message)
                .build()
                .formatMessage(formatter);
        return new ApiResponseEntity<>(body, status);
    }

    /**
     * Convert to
     * {@link ResponseEntity}<{@link ResponseBody}{@literal <}?{@literal >}>
     * 
     * @param formatter
     * @return the response entity
     */
    public ApiResponseEntity<?> toApiResponseEntity(MessageCodeFormatter formatter) {
        ResponseBody<?> body = ResponseBody.builder()
                .success(success)
                .code(code)
                .message(message)
                .build()
                .formatMessage(formatter);
        return new ApiResponseEntity<>(body, status);
    }

    /**
     * Convert to
     * {@link ResponseEntity}<{@link ResponseBody}{@literal <}D{@literal >}>
     * 
     * @param <D>  data type response body data
     * @param data the data of {@link ResponseBody}
     * @return the response entity
     */
    public <D> ApiResponseEntity<D> toApiResponseEntity(D data) {
        ResponseBody<D> body = ResponseBody.<D>builder()
                .success(success)
                .code(code)
                .message(message)
                .data(data)
                .build();
        return new ApiResponseEntity<>(body, status);
    }

    /**
     * Convert to
     * {@link ResponseEntity}<{@link ResponseBody}{@literal <}D{@literal >}>
     * 
     * @param <D>       data type response body data
     * @param data      the data of {@link ResponseBody}
     * @param formatter
     * @return the response entity
     */
    public <D> ApiResponseEntity<D> toApiResponseEntity(D data, ResponseBodyFormatter<D> formatter) {
        ResponseBody<D> body = ResponseBody.<D>builder()
                .success(success)
                .code(code)
                .message(message)
                .data(data)
                .build()
                .formatMessage(formatter);
        return new ApiResponseEntity<>(body, status);
    }

    /**
     * Convert to
     * {@link ResponseEntity}<{@link ResponseBody}{@literal <}D{@literal >}>
     * 
     * @param <D>       data type response body data
     * @param data      the data of {@link ResponseBody}
     * @param formatter
     * @return the response entity
     */
    public <D> ApiResponseEntity<D> toApiResponseEntity(D data, MessageFormatter formatter) {
        ResponseBody<D> body = ResponseBody.<D>builder()
                .success(success)
                .code(code)
                .message(message)
                .data(data)
                .build()
                .formatMessage(formatter);
        return new ApiResponseEntity<>(body, status);
    }

    /**
     * Convert to
     * {@link ResponseEntity}<{@link ResponseBody}{@literal <}D{@literal >}>
     * 
     * @param <D>       data type response body data
     * @param data      the data of {@link ResponseBody}
     * @param formatter
     * @return the response entity
     */
    public <D> ApiResponseEntity<D> toApiResponseEntity(D data, MessageCodeFormatter formatter) {
        ResponseBody<D> body = ResponseBody.<D>builder()
                .success(success)
                .code(code)
                .message(message)
                .data(data)
                .build()
                .formatMessage(formatter);
        return new ApiResponseEntity<>(body, status);
    }

    /**
     * Convert to {@link ApiException}
     * 
     * @return
     */
    public ApiException toApiException() {
        return new ApiException(this);
    }

    /**
     * Convert to {@link ApiException}
     * 
     * @param cause the cause
     * @return
     */
    public ApiException toApiException(Throwable cause) {
        return new ApiException(this, cause);
    }

    /**
     * Convert to {@link ApiRuntimeException}
     * 
     * @return
     */
    public ApiRuntimeException toApiRuntimeException() {
        return new ApiRuntimeException(this);
    }

    /**
     * Convert to {@link ApiRuntimeException}
     * 
     * @param cause the cause
     * @return
     */
    public ApiRuntimeException toApiRuntimeException(Throwable cause) {
        return new ApiRuntimeException(this, cause);
    }

    /**
     * Common message description constant
     * 
     * @author Taylor Wong
     *
     */
    public static class Description {

        /**
         * {@link ResponseMessage#DEFAULT_SUCCESS}
         */
        public static final String DEFAULT_SUCCESS = "Success.";

        /**
         * {@link ResponseMessage#DEFAULT_LOGIN_SUCCESSSUCCESS}
         */
        public static final String LOGIN_SUCCESS = "Login success.";

        /**
         * {@link ResponseMessage#DEFAULT_QUERY_SUCCESS}
         */
        public static final String DEFAULT_QUERY_SUCCESS = "Successfully queried.";

        /**
         * {@link ResponseMessage#DEFAULT_CREATE_SUCCESS}
         */
        public static final String DEFAULT_CREATE_SUCCESS = "Successfully added.";

        /**
         * {@link ResponseMessage#DEFAULT_UPDATE_SUCCESS}
         */
        public static final String DEFAULT_UPDATE_SUCCESS = "Successfully updated.";

        /**
         * {@link ResponseMessage#DEFAULT_DELETE_SUCCESS}
         */
        public static final String DEFAULT_DELETE_SUCCESS = "Successfully deleted.";

    }
}
