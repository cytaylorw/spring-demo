package io.github.cytaylorw.springdemo.config;

import java.lang.reflect.UndeclaredThrowableException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.github.cytaylorw.springdemo.core.exception.ApiException;
import io.github.cytaylorw.springdemo.core.exception.ApiRuntimeException;
import io.github.cytaylorw.springdemo.core.response.ApiResponseEntity;
import io.github.cytaylorw.springdemo.core.response.GenericResponseBody;
import io.github.cytaylorw.springdemo.core.response.MessageFormatterUtil;
import io.github.cytaylorw.springdemo.core.response.ResponseBody;
import io.github.cytaylorw.springdemo.core.response.ResponseMessage;
import io.github.cytaylorw.springdemo.core.response.SimpleResponseBody;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;

/**
 * API Exception handler
 * 
 * @author Taylor
 *
 */
@Slf4j
@RestControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
public class ApiExceptionHandler {

    /**
     * Error Handler
     * 
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @param ex       Exception
     * @return
     * @return response object
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Error.class)
    public ResponseEntity<SimpleResponseBody> handleError(final HttpServletRequest request,
            final HttpServletResponse response, final Error ex) {
        logError(ex);
        return ResponseMessage.DEFAULT_SYSTEM_ERROR.toApiSimpleResponseEntity();
    }

	/**
     * Exception Handler
     * 
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @param ex       Exception
     * @return
     * @return response object
     */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
    public ResponseEntity<SimpleResponseBody> handleException(final HttpServletRequest request,
            final HttpServletResponse response,
			final Exception ex) {
		logError(ex);
        return ResponseMessage.DEFAULT_SYSTEM_ERROR.toApiSimpleResponseEntity();
	}

	/**
     * ApiException Handler
     * 
     * @param request  Handler
     * @param response HttpServletResponse
     * @param ex       ApiException
     * @return response object
     */
    @ApiResponse(responseCode = "any", content = {
            @Content(schema = @Schema(implementation = GenericResponseBody.class)) })
	@ExceptionHandler(ApiException.class)
    public ApiResponseEntity<?> handleApiException(final HttpServletRequest request,
            final HttpServletResponse response,
			final ApiException ex) {
		logError(ex);
        return ex.getResponse();
	}

	/**
     * ApiRuntimeException Handler
     * 
     * @param request  Handler
     * @param response HttpServletResponse
     * @param ex       ApiRuntimeException
     * @return response object
     */
    @ApiResponse(responseCode = "any", content = {
            @Content(schema = @Schema(implementation = GenericResponseBody.class)) })
	@ExceptionHandler(ApiRuntimeException.class)
    public ApiResponseEntity<?> handleApiRuntimeException(final HttpServletRequest request,
            final HttpServletResponse response,
			final ApiRuntimeException ex) {
		logError(ex);
        return ex.getResponse();
	}

	/**
     * UndeclaredThrowableException Handler
     *
     * @param request  Handler
     * @param response HttpServletResponse
     * @param ex       UndeclaredThrowableException
     * @return response object
     */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(UndeclaredThrowableException.class)
    public ResponseEntity<SimpleResponseBody> handleUndeclaredThrowableException(final HttpServletRequest request,
			final HttpServletResponse response, final UndeclaredThrowableException ex) {
		logError(ex);
        return ResponseMessage.DEFAULT_SYSTEM_ERROR.toApiSimpleResponseEntity();
	}

    /**
     * AuthenticationException Handler
     *
     * @param request  Handler
     * @param response HttpServletResponse
     * @param ex       AuthenticationException
     * @return response object
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<SimpleResponseBody> handleAuthenticationException(final HttpServletRequest request,
            final HttpServletResponse response, final AuthenticationException ex) {
        logError(ex);
        return ResponseMessage.UNAUTHORIZED_ERROR.toApiSimpleResponseEntity();
    }

	/**
     * MethodArgumentNotValidException Handler
     * 
     * @param ex MethodArgumentNotValidException
     * @return response object
     */
    @ApiResponse(responseCode = "400", description = "Validation error")
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseBody<Map<String, String>>> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex) {
		logError(ex);
		Map<String, String> errors = new HashMap<>();

		ex.getBindingResult().getFieldErrors()
				.forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

        return ResponseMessage.DEFAULT_BAD_REQUEST_ERROR.toApiResponseEntity(errors);
	}

	/**
     * ConstraintViolationException Handler (thrown by hibernate entity manager)
     * 
     * @param ex ConstraintViolationException
     * @return
     */
    @ApiResponse(responseCode = "400", description = "Validation error")
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ResponseBody<Map<String, String>>> handleConstraintViolationException(
            ConstraintViolationException ex) {
		logError(ex);
        Map<String, String> errors = ex.getConstraintViolations()
                .stream()
                .collect(Collectors.toMap(v -> v.getPropertyPath().toString(), ConstraintViolation::getMessage));

        return ResponseMessage.DEFAULT_BAD_REQUEST_ERROR.toApiResponseEntity(errors);
	}

    /**
     * ConversionFailedException Handler
     * 
     * @param ex ConversionFailedException
     * @return
     */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ConversionFailedException.class)
    public ResponseEntity<SimpleResponseBody> handleConversionFailedException(ConversionFailedException ex) {
		logError(ex);
        return ResponseMessage.DEFAULT_BAD_REQUEST_ERROR.toApiSimpleResponseEntity();
	}

    /**
     * HttpMessageNotReadableException Handler
     *
     * @param ex
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<SimpleResponseBody> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        logError(ex);
        return ResponseMessage.DEFAULT_BAD_REQUEST_ERROR.toApiSimpleResponseEntity();
    }

    /**
     * HttpMessageNotReadableException Handler
     *
     * @param ex
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PropertyReferenceException.class)
    public ResponseEntity<SimpleResponseBody> handlePropertyReferenceException(PropertyReferenceException ex) {
        logError(ex);
        return ResponseMessage.PROPERTY_NOT_FOUND
                .toApiSimpleResponseEntity(MessageFormatterUtil.messageFormat(ex.getPropertyName()));
    }

	/**
	 * Log Exception message
	 * 
	 * @param ex Exception
	 */
    private void logError(Throwable ex) {
        log.error(this.getClass().getName() + " resolved: ", ex);
	}

}
