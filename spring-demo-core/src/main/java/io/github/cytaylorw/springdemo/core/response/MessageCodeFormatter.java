package io.github.cytaylorw.springdemo.core.response;

import java.util.function.Function;

/**
 * Functional interface that accepts a message code and produces a message.
 * 
 * @author Taylor
 *
 */
@FunctionalInterface
public interface MessageCodeFormatter extends Function<String, String> {
}
