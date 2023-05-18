package io.github.cytaylorw.springdemo.common.config;

/**
 * Decoder for application properties.
 * 
 * @author Taylor Wong
 */
public interface PropertyDecoder {

  /**
   * Return a decoded string.
   * 
   * @param encoded an encoded string.
   * @return a decoded string.
   */
  String decode(String encoded);

}
