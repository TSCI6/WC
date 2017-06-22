package com.whichcontact.core.jpa;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@PropertySource({ "classpath:which-contact.properties" })
@EnableJpaRepositories(basePackages = "com.whichcontact", entityManagerFactoryRef = "masterEntityManager", transactionManagerRef = "masterTransactionManager")
public class MasterJpaConfig {

	private static final String HIBERNATE_HBM2DDL = "hibernate.hbm2ddl.auto";
	private static final String HIBERNATE_DIALECT = "hibernate.dialect";
	private static final String HIBERNATE_SHOW_SQL = "hibernate.show_sql";
	private static final String DRIVER_CLASS = "jdbc.driverClassName";

	private static final String MASTER_PACKAGE_TO_SCAN = "com.whichcontact.core";
	private static final String MASTER_PERSISTENCE_UNIT = "persistence-master";
	private static final String MASTER_DB_URL = "master.db.url";
	private static final String MASTER_DB_USERNAME = "master.db.user";
	private static final String MASTER_DB_PASSWORD = "master.db.pass";

	@Autowired
	private Environment env;

	/**
	 * @return
	 */
	@Bean
	@Primary
	public LocalContainerEntityManagerFactoryBean masterEntityManager() {
		LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
		entityManager.setDataSource(masterDataSource());
		entityManager.setPackagesToScan(new String[] { MASTER_PACKAGE_TO_SCAN });
		entityManager.setPersistenceUnitName(MASTER_PERSISTENCE_UNIT);

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		entityManager.setJpaVendorAdapter(vendorAdapter);
		HashMap<String, Object> properties = new HashMap<String, Object>();
		properties.put(HIBERNATE_HBM2DDL, env.getProperty(HIBERNATE_HBM2DDL));
		properties.put(HIBERNATE_DIALECT, env.getProperty(HIBERNATE_DIALECT));
		properties.put(HIBERNATE_SHOW_SQL, env.getProperty(HIBERNATE_SHOW_SQL));
		entityManager.setJpaPropertyMap(properties);

		return entityManager;
	}

	/**
	 * @return
	 */
	@Bean
	@Primary
	public DataSource masterDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty(DRIVER_CLASS));
		dataSource.setUrl(env.getProperty(MASTER_DB_URL));
		dataSource.setUsername(env.getProperty(MASTER_DB_USERNAME));
		dataSource.setPassword(env.getProperty(MASTER_DB_PASSWORD));
		return dataSource;
	}

	@Bean
	@Primary
	public PlatformTransactionManager masterTransactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(masterEntityManager().getObject());
		return transactionManager;
	}
}