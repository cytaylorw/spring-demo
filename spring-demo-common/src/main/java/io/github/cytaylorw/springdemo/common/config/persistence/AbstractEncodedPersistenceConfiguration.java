package io.github.cytaylorw.springdemo.common.config.persistence;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;

import io.github.cytaylorw.springdemo.common.config.Base64StringDecoder;

/**
 * Abstract configuration for encoded properties.
 * 
 * @author Taylor Wong
 */
public abstract class AbstractEncodedPersistenceConfiguration extends AbstractPersistenceConfiguration {

	@Override
	protected DataSourceProperties initDataSourceProperties() {
		return new EncodedDataSourceProperties(new Base64StringDecoder());
	}

}
