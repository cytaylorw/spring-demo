package io.github.cytaylorw.springdemo.core.response;

import java.util.function.Function;

/**
 * Functional interface that accepts a message pattern and produces a formatted
 * message.
 * 
 * @author Taylor
 *
 */
@FunctionalInterface
public interface MessageFormatter extends Function<String, String> {
}
