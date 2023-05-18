package io.github.cytaylorw.springdemo.common.config.persistence;

import io.github.cytaylorw.springdemo.common.config.PropertyDecoder;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;

/**
 * Encoded DataSourceProperties
 * 
 * @author Taylor Wong
 */
public class EncodedHikariDataSourceProperties extends DataSourceProperties {

  /**
   * Decoder
   */
  private final PropertyDecoder decoder;

  /**
   * Encoded username
   */
  private String zhang;

  /**
   * Encoded password
   */
  private String mi;

  /**
   * constructor
   * 
   * @param decoder the property decoder
   */
  public EncodedHikariDataSourceProperties(PropertyDecoder decoder) {
    super();
    this.decoder = decoder;
  }

  @Override
  public String getPassword() {
    return decoder.decode(this.mi);
  }

  @Override
  public String getUsername() {
    return decoder.decode(this.zhang);
  }

  /**
   * Set the encoded password.
   * 
   * @param mi the encoded password
   */
  public void setMi(String mi) {
    this.mi = mi;
  }

  /**
   * Get the encoded password.
   * 
   * @return the encoded password
   */
  public String getMi() {
    return this.mi;
  }

  /**
   * Set the encoded username.
   * 
   * @param zhang the encoded username
   */
  public void setZhang(String zhang) {
    this.zhang = zhang;
  }

  /**
   * Get the encoded password.
   * 
   * @return the encoded username
   */
  public String getZhang() {
    return this.zhang;
  }
}
