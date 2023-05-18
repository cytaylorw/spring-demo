package io.github.cytaylorw.springdemo.common.config.persistence;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;

import io.github.cytaylorw.springdemo.common.config.PropertyDecoder;
import lombok.extern.slf4j.Slf4j;

/**
 * Encoded DataSourceProperties
 * 
 * @author Taylor Wong
 */
@Slf4j
public class EncodedDataSourceProperties extends DataSourceProperties {

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
	public EncodedDataSourceProperties(PropertyDecoder decoder) {
		super();
		this.decoder = decoder;
	}

	@Override
	public void setPassword(String password) {
		super.setPassword(decoder.decode(password));
	}

	@Override
	public void setUsername(String username) {
		super.setUsername(decoder.decode(username));
	}

	/**
	 * Set the encoded password.
	 * 
	 * @param mi the encoded password
	 */
	public void setMi(String mi) {
		this.setPassword(mi);
	}

	/**
	 * Get the encoded password.
	 * 
	 * @return the encoded password
	 */
	public String getMi() {
		return this.getPassword();
	}

	/**
	 * Set the encoded username.
	 * 
	 * @param zhang the encoded username
	 */
	public void setZhang(String zhang) {
		this.setUsername(zhang);
	}

	/**
	 * Get the encoded password.
	 * 
	 * @return the encoded username
	 */
	public String getZhang() {
		return this.getUsername();
	}
}
