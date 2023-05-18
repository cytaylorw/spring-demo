package io.github.cytaylorw.springdemo.db.demo.config;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import io.github.cytaylorw.springdemo.common.config.persistence.AbstractEncodedPersistenceConfiguration;
import lombok.extern.slf4j.Slf4j;

/**
 * Demo DB Configuration.<br>
 * <br>
 * entityManagerFactoryRef=authEntityManager<br>
 * transactionManagerRef=authTransactionManager<br>
 */
@Slf4j
@Configuration
@ConditionalOnProperty(prefix = "spring.datasource.demo", name = "enable", havingValue = "true", matchIfMissing = true)
@EnableJpaRepositories(basePackages = DemoPersistenceConfiguration.REPOSITORY_PACKAGE, entityManagerFactoryRef = DemoPersistenceConfiguration.NAME
		+ "EntityManager", transactionManagerRef = DemoPersistenceConfiguration.NAME + "TransactionManager")
public class DemoPersistenceConfiguration extends AbstractEncodedPersistenceConfiguration {

	/**
	 * DB name.
	 */
	protected static final String NAME = "demo";

	/**
	 * application property prefix
	 */
	private static final String PROPERTY_PREFIX = "spring.datasource." + NAME;

	/**
	 * Repository package.
	 */
	protected static final String REPOSITORY_PACKAGE = "io.github.cytaylorw.springdemo.db.demo.repository";

	/**
	 * Entity packages to scan.
	 */
	private static final String[] ENTITY_PACKAGES = { "io.github.cytaylorw.springdemo.db.demo.entity" };

	@Override
	protected String[] getEntityPackages() {
		log.debug("[{}] ENTITY_PACKAGES: {}", NAME, ENTITY_PACKAGES);
		return DemoPersistenceConfiguration.ENTITY_PACKAGES;
	}

	@ConfigurationProperties(prefix = PROPERTY_PREFIX + ".jpa")
	@Override
	protected JpaProperties getJpaProperties() {
		return this.initJpaProperties();
	}

	@ConfigurationProperties(prefix = PROPERTY_PREFIX + ".jpa.hibernate")
	@Override
	protected HibernateProperties getHibernateProperties() {
		return this.initHibernateProperties();
	}

	@Bean(name = NAME + "DataSourceProperties")
	@Primary
	@ConfigurationProperties(PROPERTY_PREFIX + ".datasource")
	@Override
	public DataSourceProperties getDataSourceProperties() {
		return this.initDataSourceProperties();
	}

	@Bean(name = NAME + "DataSource")
	@Primary
	@ConfigurationProperties(PROPERTY_PREFIX + ".datasource.hikari")
	@Override
	public DataSource getDataSource() {
		log.debug("[{}] Creating DataSource...", NAME);
		return this.initDataSource();
	}

	@Bean(name = NAME + "JdbcTemplate")
	@Primary
	@Override
	public JdbcTemplate getJdbcTemplate() {
		log.debug("[{}] Creating JdbcTemplate...", NAME);
		return this.initJdbcTemplate();
	}

	@Bean(name = NAME + "EntityManager")
	@Primary
	@Override
	public LocalContainerEntityManagerFactoryBean getEntityManager() {
		log.debug("[{}] Creating EntityManager...", NAME);
		LocalContainerEntityManagerFactoryBean em = this.createEntityManager();
		em.setPersistenceUnitName(NAME);
		return em;
	}

	@Primary
	@Bean(name = NAME + "TransactionManager")
	@Override
	public PlatformTransactionManager getTransactionManager() {
		log.debug("[{}] Creating TransactionManager...", NAME);
		return this.createTransactionManager();
	}
}
