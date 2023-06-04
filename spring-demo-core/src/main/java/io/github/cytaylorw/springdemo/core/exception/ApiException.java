package io.github.cytaylorw.springdemo.core.exception;

import io.github.cytaylorw.springdemo.core.response.ApiResponseEntity;
import io.github.cytaylorw.springdemo.core.response.ResponseMessage;
import lombok.Getter;

/**
 * API Exception
 * 
 * @author Taylor
 *
 */
@Getter
public class ApiException extends Exception {

    @java.io.Serial
	private static final long serialVersionUID = 173686238506833976L;

	/**
     * Default message
     */
    private static final ResponseMessage DEFAULT_MESSAGE = ResponseMessage.DEFAULT_SYSTEM_ERROR;

    /**
     * the response instance
     */
    private final ApiResponseEntity<?> response;

	/**
     * constructor
     */
	public ApiException() {
		super(DEFAULT_MESSAGE.getMessage());
        this.response = DEFAULT_MESSAGE.toApiResponseEntity();
	}

    /**
     * constructor
     * 
     * @param cause
     */
    public ApiException(Throwable cause) {
        super(DEFAULT_MESSAGE.getMessage(), cause);
        this.response = DEFAULT_MESSAGE.toApiResponseEntity();
    }

	/**
     * constructor
     * 
     * @param message
     */
	public ApiException(String message) {
		super(message);
        this.response = DEFAULT_MESSAGE.toApiResponseEntity();
        this.response.getBody().setMessage(message);
	}

    /**
     * constructor
     * 
     * @param message
     * @param cause
     */
    public ApiException(String message, Throwable cause) {
        super(message, cause);
        this.response = DEFAULT_MESSAGE.toApiResponseEntity();
        this.response.getBody().setMessage(message);
    }

	/**
     * constructor
     * 
     * @param message
     */
    public ApiException(ResponseMessage message) {
        super(message.getMessage());
        this.response = message.toApiResponseEntity();
	}

    /**
     * constructor
     * 
     * @param message
     * @param cause
     */
    public ApiException(ResponseMessage message, Throwable cause) {
        super(message.getMessage(), cause);
        this.response = message.toApiResponseEntity();
    }

    /**
     * constructor
     * 
     * @param response
     */
    public <D> ApiException(ApiResponseEntity<D> response) {
        super(response.getBody().getMessage());
        this.response = response;
    }

    /**
     * constructor
     * 
     * @param response
     * @param cause
     */
    public <D> ApiException(ApiResponseEntity<D> response, Throwable cause) {
        super(response.getBody().getMessage(), cause);
        this.response = response;
    }

}
