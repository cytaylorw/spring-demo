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
     * DEFAULT_SUCCESS(true, 200, "default.success", "Success."),
     */
    DEFAULT_SUCCESS(true, 200, "default.success", "Success."),
    /**
     * DEFAULT_QUERY_SUCCESS(true, 200, "default.query.success", "Successfully
     * queried."),
     */
    DEFAULT_QUERY_SUCCESS(true, 200, "default.query.success", "Successfully queried."),
    /**
     * DEFAULT_CREATE_SUCCESS(true, 200, "default.create.success", "Successfully
     * added."),
     */
    DEFAULT_CREATE_SUCCESS(true, 200, "default.create.success", "Successfully added."),
    /**
     * DEFAULT_UPDATE_SUCCESS(true, 200, "default.update.success", "Successfully
     * updated."),
     */
    DEFAULT_UPDATE_SUCCESS(true, 200, "default.update.success", "Successfully updated."),
    /**
     * DEFAULT_DELETE_SUCCESS(true, 200, "default.delete.success", "Successfully
     * deleted."),
     */
    DEFAULT_DELETE_SUCCESS(true, 200, "default.delete.success", "Successfully deleted."),
    /**
     * UNAUTHORIZED_ERROR(false, 400, "unauthorized.error", "Unauthorized."),
     */
    UNAUTHORIZED_ERROR(false, 400, "unauthorized.error", "Unauthorized."),
    /**
     * DEFAULT_BAD_REQUEST_ERROR(false, 400, "default.bad.request.error", "Bad
     * requests."),
     */
    DEFAULT_BAD_REQUEST_ERROR(false, 400, "default.bad.request.error", "Bad requests."),
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
     * Convert {@link ResponseEntity}<{@link ResponseBody}<?>>
     * 
     * @param data the data of {@link ResponseBody}
     * @return the response entity
     */
    public ApiResponseEntity<?> toApiResponseEntity() {
        return new ApiResponseEntity<>(ResponseBody.of(success, code, message, null), status);
    }

    /**
     * Convert {@link ResponseEntity}<{@link ResponseBody}<?>>
     * 
     * @param formatter
     * @return the response entity
     */
    public ApiResponseEntity<?> toApiResponseEntity(MessageFormatter formatter) {
        return new ApiResponseEntity<>(ResponseBody.of(success, code, message, null).formatMessage(formatter), status);
    }

    /**
     * Convert {@link ResponseEntity}<{@link ResponseBody}<?>>
     * 
     * @param formatter
     * @return the response entity
     */
    public ApiResponseEntity<?> toApiResponseEntity(MessageCodeFormatter formatter) {
        return new ApiResponseEntity<>(ResponseBody.of(success, code, message, null).formatMessage(formatter), status);
    }

    /**
     * Convert {@link ResponseEntity}<{@link ResponseBody}<D>>
     * 
     * @param <D>  data type response body data
     * @param data the data of {@link ResponseBody}
     * @return the response entity
     */
    public <D> ApiResponseEntity<D> toApiResponseEntity(D data) {
        return new ApiResponseEntity<>(ResponseBody.of(success, code, message, data), status);
    }

    /**
     * Convert {@link ResponseEntity}<{@link ResponseBody}<D>>
     * 
     * @param <D>       data type response body data
     * @param data      the data of {@link ResponseBody}
     * @param formatter
     * @return the response entity
     */
    public <D> ApiResponseEntity<D> toApiResponseEntity(D data, ResponseBodyFormatter<D> formatter) {
        return new ApiResponseEntity<>(ResponseBody.of(success, code, message, data).formatMessage(formatter), status);
    }

    /**
     * Convert {@link ResponseEntity}<{@link ResponseBody}<D>>
     * 
     * @param <D>       data type response body data
     * @param data      the data of {@link ResponseBody}
     * @param formatter
     * @return the response entity
     */
    public <D> ApiResponseEntity<D> toApiResponseEntity(D data, MessageFormatter formatter) {
        return new ApiResponseEntity<>(ResponseBody.of(success, code, message, data).formatMessage(formatter), status);
    }

    /**
     * Convert to {@link ResponseEntity}<{@link ResponseBody}<D>>
     * 
     * @param <D>       data type response body data
     * @param data      the data of {@link ResponseBody}
     * @param formatter
     * @return the response entity
     */
    public <D> ApiResponseEntity<D> toApiResponseEntity(D data, MessageCodeFormatter formatter) {
        return new ApiResponseEntity<>(ResponseBody.of(success, code, message, data).formatMessage(formatter), status);
    }

    /**
     * Convert to {@link ApiException}
     * 
     * @return
     */
    public ApiException toApiException() {
        return this.toApiException();
    }

    /**
     * Convert to {@link ApiException}
     * 
     * @param cause the cause
     * @return
     */
    public ApiException toApiException(Throwable cause) {
        return this.toApiException();
    }

    /**
     * Convert to {@link ApiRuntimeException}
     * 
     * @return
     */
    public ApiRuntimeException toApiRuntimeException() {
        return this.toApiRuntimeException();
    }

    /**
     * Convert to {@link ApiRuntimeException}
     * 
     * @param cause the cause
     * @return
     */
    public ApiRuntimeException toApiRuntimeException(Throwable cause) {
        return this.toApiRuntimeException();
    }
}
