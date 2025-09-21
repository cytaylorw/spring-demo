package io.github.cytaylorw.springdemo.core.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * A simple response body class without data.
 * 
 * @author Taylor
 *
 */
@Getter
@Setter
@SuperBuilder(builderMethodName = "simpleBuiler")
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class SimpleResponseBody {

    /**
     * Is success?
     */
    protected Boolean success;

    /**
     * Response code
     */
    protected String code;

    /**
     * Response message
     */
    protected String message;

    /**
     * Format the message with the formatter.
     * 
     * @param formatter
     * @return
     */
    public SimpleResponseBody formatMessage(MessageFormatter formatter) {
        this.message = formatter.apply(this.message);
        return this;
    }

    /**
     * Format the message with the formatter.
     * 
     * @param formatter
     * @return
     */
    public SimpleResponseBody formatMessage(MessageCodeFormatter formatter) {
        this.message = formatter.apply(this.code);
        return this;
    }

}
