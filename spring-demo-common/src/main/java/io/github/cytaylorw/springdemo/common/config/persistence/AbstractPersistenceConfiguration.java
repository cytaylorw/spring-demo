package io.github.cytaylorw.springdemo.common.config.persistence;

import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * Abstract class for configuring Spring Data JPA.
 * 
 * @author Taylor Wong
 */
public abstract class AbstractPersistenceConfiguration {

	/**
	 * Default initialization of DataSourceProperties to be used in
	 * {@link AbstractPersistenceConfiguration#getDataSourceProperties()}
	 * 
	 * @return DataSourceProperties data source configuration
	 */
	protected DataSourceProperties initDataSourceProperties() {
		return new DataSourceProperties();
	}

	/**
	 * Default initialization of DataSource to be used in
	 * {@link AbstractPersistenceConfiguration#getDataSource()}
	 * 
	 * @return DataSource the DataSource
	 */
	protected DataSource initDataSource() {
		return this.getDataSourceProperties().initializeDataSourceBuilder().build();
	}

	/**
	 * Default initialization of JdbcTemplate to be used in
	 * {@link AbstractPersistenceConfiguration#getJdbcTemplate()}
	 * 
	 * @return JdbcTemplate the DataSource
	 */
	protected JdbcTemplate initJdbcTemplate() {
		return new JdbcTemplate(this.getDataSource());
	}

	/**
	 * Default initialization of JpaProperties to be used in
	 * {@link AbstractPersistenceConfiguration#getJpaProperties()}
	 * 
	 * @return JpaProperties configuration properties for a JPA EntityManagerFactory
	 */
	protected JpaProperties initJpaProperties() {
		return new JpaProperties();
	}

	/**
	 * Default initialization of JpaProperties to be used in
	 * {@link AbstractPersistenceConfiguration#getHibernateProperties()}
	 * 
	 * @return HibernateProperties configuration properties for Hibernate
	 */
	protected HibernateProperties initHibernateProperties() {
		return new HibernateProperties();
	}

	/**
	 * Create LocalContainerEntityManagerFactoryBean to be used in
	 * {@link AbstractPersistenceConfiguration#getEntityManager()}
	 * 
	 * @return LocalContainerEntityManagerFactoryBean
	 */
	protected LocalContainerEntityManagerFactoryBean createEntityManager() {
		JpaProperties jpaProperties = this.getJpaProperties();
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();

		em.setDataSource(this.getDataSource());
		em.setPackagesToScan(this.getEntityPackages());
		em.setMappingResources(jpaProperties.getMappingResources().toArray(new String[0]));
		em.setJpaVendorAdapter(this.createHibernateJpaVendorAdapter());
		em.setJpaPropertyMap(this.createJpaPropertyMap());

		return em;
	}

	/**
	 * Create TransactionManager to be used in
	 * {@link AbstractPersistenceConfiguration#getTransactionManager()}
	 * 
	 * @return PlatformTransactionManager
	 */
	protected PlatformTransactionManager createTransactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();

		transactionManager.setEntityManagerFactory(this.getEntityManager().getObject());

		return transactionManager;
	}

	/**
	 * Create VendorAdapter
	 * 
	 * @return VendorAdapter
	 */
	private HibernateJpaVendorAdapter createHibernateJpaVendorAdapter() {
		JpaProperties jpaProperties = this.getJpaProperties();
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		Optional.ofNullable(jpaProperties.getDatabase()).ifPresent(db -> vendorAdapter.setDatabase(db));
		Optional.ofNullable(jpaProperties.getDatabasePlatform())
				.ifPresent(dbp -> vendorAdapter.setDatabasePlatform(dbp));
		vendorAdapter.setGenerateDdl(jpaProperties.isGenerateDdl());
		vendorAdapter.setShowSql(jpaProperties.isShowSql());
		return vendorAdapter;
	}

	/**
	 * Create JpaPropertyMap
	 * 
	 * @return JpaPropertyMap
	 */
	private Map<String, ?> createJpaPropertyMap() {
		JpaProperties jpaProperties = this.getJpaProperties();
		HibernateSettings hibernateSettings = new HibernateSettings();
		Map<String, ?> jpaPropertyMap = this.getHibernateProperties()
				.determineHibernateProperties(jpaProperties.getProperties(), hibernateSettings);
		return jpaPropertyMap;
	}

	/**
	 * Entity packages to scan.
	 * 
	 * @return Entity packages
	 */
	protected abstract String[] getEntityPackages();

	/**
	 * Get the JpaProperties. Need to register as a Spring bean. For example From
	 * spring properties for example:
	 * 
	 * <pre>
	 * &#64;ConfigurationProperties(prefix = PROPERTY_PREFIX + ".jpa")
	 * &#64;Override
	 * protected JpaProperties getJpaProperties() {
	 * 	return this.initJpaProperties();
	 * }
	 * </pre>
	 * 
	 * @return JpaProperties
	 */
	protected abstract JpaProperties getJpaProperties();

	/**
	 * Get the HibernateProperties. Need to register as a Spring bean. For example
	 * From spring properties for example:
	 * 
	 * <pre>
	 * &#64;ConfigurationProperties(prefix = PROPERTY_PREFIX + ".jpa.hibernate")
	 * &#64;Override
	 * protected HibernateProperties getHibernateProperties() {
	 * 	return this.initHibernateProperties();
	 * }
	 * </pre>
	 * 
	 * @return HibernateProperties
	 */
	protected abstract HibernateProperties getHibernateProperties();

	/**
	 * Get the DataSourceProperties. Need to register as a Spring bean. For example
	 * From spring properties for example:
	 * 
	 * <pre>
	 * &#64;Bean(name = NAME + "DataSourceProperties")
	 * &#64;Primary
	 * &#64;ConfigurationProperties(PROPERTY_PREFIX + ".datasource")
	 * &#64;Override
	 * public DataSourceProperties getDataSourceProperties() {
	 * 	return this.initDataSourceProperties();
	 * }
	 * </pre>
	 * 
	 * @return DataSourceProperties
	 */
	public abstract DataSourceProperties getDataSourceProperties();

	/**
	 * Get the DataSource. Need to register as a Spring bean. For example From
	 * spring properties for example:
	 * 
	 * <pre>
	 * &#64;Bean(name = NAME + "DataSource")
	 * &#64;Primary
	 * &#64;Override
	 * public DataSource getDataSource() {
	 * 	return this.initDataSource();
	 * }
	 * </pre>
	 * 
	 * @return DataSource
	 */
	public abstract DataSource getDataSource();

	/**
	 * Get the JdbcTemplate. Need to register as a Spring bean. For example From
	 * spring properties for example:
	 * 
	 * <pre>
	 * &#64;Bean(name = NAME + "JdbcTemplate")
	 * &#64;Primary
	 * &#64;Override
	 * public JdbcTemplate getJdbcTemplate() {
	 * 	return this.initJdbcTemplate();
	 * }
	 * </pre>
	 * 
	 * @return JdbcTemplate
	 */
	public abstract JdbcTemplate getJdbcTemplate();

	/**
	 * Get the LocalContainerEntityManagerFactoryBean. Need to register as a Spring
	 * bean. For example From spring properties for example:
	 * 
	 * <pre>
	 * &#64;Bean(name = NAME + "EntityManager")
	 * &#64;Primary
	 * &#64;Override
	 * public LocalContainerEntityManagerFactoryBean getEntityManager() {
	 * 	return this.createEntityManager();
	 * }
	 * </pre>
	 * 
	 * @return LocalContainerEntityManagerFactoryBean
	 */
	public abstract LocalContainerEntityManagerFactoryBean getEntityManager();

	/**
	 * Get the PlatformTransactionManager. Need to register as a Spring bean. For
	 * example From spring properties for example:
	 * 
	 * <pre>
	 * &#64;Primary
	 * &#64;Bean(name = NAME + "TransactionManager")
	 * &#64;Override
	 * public PlatformTransactionManager getTransactionManager() {
	 * 	LocalContainerEntityManagerFactoryBean em = this.createEntityManager();
	 * 	em.setPersistenceUnitName(NAME);
	 * 	return em;
	 * }
	 * </pre>
	 * 
	 * @return PlatformTransactionManager
	 */
	public abstract PlatformTransactionManager getTransactionManager();
}
