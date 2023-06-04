package io.github.cytaylorw.springdemo.config;

import java.lang.reflect.UndeclaredThrowableException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.github.cytaylorw.springdemo.core.exception.ApiException;
import io.github.cytaylorw.springdemo.core.exception.ApiRuntimeException;
import io.github.cytaylorw.springdemo.core.response.ApiResponseEntity;
import io.github.cytaylorw.springdemo.core.response.ResponseMessage;
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
     * Exception Handler
     * 
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @param ex       Exception
     * @return
     * @return Object response
     */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
    public ApiResponseEntity<?> handleException(final HttpServletRequest request,
            final HttpServletResponse response,
			final Exception ex) {
		logError(ex);
        return ResponseMessage.DEFAULT_SYSTEM_ERROR.toApiResponseEntity();
	}

	/**
	 * RuntimeException Handler
	 * 
	 * @param request  Handler
	 * @param response HttpServletResponse
	 * @param ex       RuntimeException
	 * @return Object response
	 */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(RuntimeException.class)
    public ApiResponseEntity<?> handleRunTimeException(final HttpServletRequest request,
            final HttpServletResponse response,
			final RuntimeException ex) {
		logError(ex);
        return ResponseMessage.DEFAULT_SYSTEM_ERROR.toApiResponseEntity();
	}

	/**
     * ApiException Handler
     * 
     * @param request  Handler
     * @param response HttpServletResponse
     * @param ex       ApiException
     * @return Object response
     */
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
     * @return Object response
     */

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
	 * @return Object response
	 */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(UndeclaredThrowableException.class)
    public ApiResponseEntity<?> handleUndeclaredThrowableException(final HttpServletRequest request,
			final HttpServletResponse response, final UndeclaredThrowableException ex) {
		logError(ex);
        return ResponseMessage.DEFAULT_SYSTEM_ERROR.toApiResponseEntity();
	}

	/**
     * MethodArgumentNotValidException Handler
     * 
     * @param ex MethodArgumentNotValidException
     * @return
     */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponseEntity<Map<String, String>> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex) {
		logError(ex);
		Map<String, String> errors = new HashMap<>();

		ex.getBindingResult().getFieldErrors()
				.forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

        return ResponseMessage.DEFAULT_BAD_REQUEST_ERROR.toApiResponseEntity(errors);
	}

	/**
     * ConstraintViolationException Handler
     * 
     * @param ex ConstraintViolationException
     * @return
     */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ConstraintViolationException.class)
    public ApiResponseEntity<Map<Object, String>> handleConstraintViolationException(
            ConstraintViolationException ex) {
		logError(ex);
		Map<Object, String> errors = ex.getConstraintViolations().stream()
				.collect(Collectors.toMap(v -> v.getPropertyPath(), ConstraintViolation::getMessage));

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
    public ApiResponseEntity<?> handleConversionFailedException(ConversionFailedException ex) {
		logError(ex);
        return ResponseMessage.DEFAULT_BAD_REQUEST_ERROR.toApiResponseEntity();
	}

	/**
	 * Log Exception message
	 * 
	 * @param ex Exception
	 */
	private void logError(Exception ex) {
        log.error(this.getClass().getName() + " resolved: ", ex);
	}

}
