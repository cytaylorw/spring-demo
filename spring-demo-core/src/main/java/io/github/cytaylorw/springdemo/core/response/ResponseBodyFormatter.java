package io.github.cytaylorw.springdemo.core.response;

import java.util.function.Function;

/**
 * Functional interface that accepts a {@link ResponseBody} and produces a
 * formatted message.
 * 
 * @author Taylor
 *
 * @param <D> the data type of the {@link ResponseBody}
 */
@FunctionalInterface
public interface ResponseBodyFormatter<D> extends Function<ResponseBody<D>, String> {
}
