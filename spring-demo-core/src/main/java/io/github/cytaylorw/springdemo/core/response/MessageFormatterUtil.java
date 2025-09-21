package io.github.cytaylorw.springdemo.core.response;

import java.text.MessageFormat;

/**
 * MessageFormatter Utilities
 * 
 * @author Taylor
 *
 */
public class MessageFormatterUtil {

    /**
     * User {@link MessageFormat#format(String, Object...)} to format the message
     * 
     * @param args
     * @return
     */
    public static MessageFormatter messageFormat(Object... args) {
        return message -> MessageFormat.format(message, args);
    }

    /**
     * User {@link String#format(String, Object...)} to format the message
     * 
     * @param args
     * @return
     */
    public static MessageFormatter stringFormat(Object... args) {
        return message -> String.format(message, args);
    }

}
