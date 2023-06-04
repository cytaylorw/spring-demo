package io.github.cytaylorw.springdemo.core.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * The response body class.
 * 
 * @author Taylor
 *
 * @param <D> response data type
 */
@Getter
@Setter
@AllArgsConstructor(staticName = "of")
@JsonInclude(Include.NON_NULL)
public class ResponseBody<D> {

    /**
     * Is success?
     */
    private Boolean success;

    /**
     * Response code
     */
    private String code;

    /**
     * Response message
     */
    private String message;

    /**
     * Response data
     */
    private D data;

    /**
     * Format the message with the formatter.
     * 
     * @param formatter
     * @return current instance
     */
    public ResponseBody<D> formatMessage(ResponseBodyFormatter<D> formatter) {
        this.message = formatter.apply(this);
        return this;
    }

    /**
     * Format the message with the formatter.
     * 
     * @param formatter
     * @return
     */
    public ResponseBody<D> formatMessage(MessageFormatter formatter) {
        this.message = formatter.apply(this.message);
        return this;
    }

    /**
     * Format the message with the formatter.
     * 
     * @param formatter
     * @return
     */
    public ResponseBody<D> formatMessage(MessageCodeFormatter formatter) {
        this.message = formatter.apply(this.code);
        return this;
    }

}
