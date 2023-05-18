package io.github.cytaylorw.springdemo.common.config;

import java.util.Base64;

/**
 * Base64 decoder
 * 
 * @author Taylor Wong
 */
public class Base64StringDecoder implements PropertyDecoder {

  @Override
  public String decode(String encoded) {
    return new String(Base64.getDecoder().decode(encoded));
  }
}
