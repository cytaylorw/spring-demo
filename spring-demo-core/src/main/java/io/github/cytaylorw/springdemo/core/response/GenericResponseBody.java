package io.github.cytaylorw.springdemo.core.response;

import lombok.experimental.SuperBuilder;

/**
 * A generic response body class representing
 * {@link ResponseBody}{@literal<}Object{@literal>} for swagger.
 * 
 * @author Taylor Wong
 *
 */
@SuperBuilder
public class GenericResponseBody extends ResponseBody<Object> {
}
