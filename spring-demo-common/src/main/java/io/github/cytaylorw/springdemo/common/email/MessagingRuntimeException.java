package io.github.cytaylorw.springdemo.common.email;

import jakarta.mail.MessagingException;
import lombok.Getter;

/**
 * The RuntimeException for {@link MessagingException}
 * 
 * @author Taylor
 *
 */
@Getter
public class MessagingRuntimeException extends RuntimeException {

    @java.io.Serial
    private static final long serialVersionUID = -6958003708887928896L;

    /**
     * constructor
     */
    public MessagingRuntimeException() {
        super();
    }

    /**
     * constructor
     * 
     * @param cause
     */
    public MessagingRuntimeException(Throwable cause) {
        super(cause);
    }

    /**
     * constructor
     * 
     * @param message
     */
    public MessagingRuntimeException(String message) {
        super(message);
    }

    /**
     * constructor
     * 
     * @param message
     * @param cause
     */
    public MessagingRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

}
