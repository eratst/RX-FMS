package com.pcitc.fms.common;

import java.beans.PropertyVetoException;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.StringUtils;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.pcitc.imp.common.context.SpringConfiguration;
import com.zaxxer.hikari.HikariDataSource;

/**
 * 要使用ioc 和 jpa 必须有这个配置类
 */
@Configuration
@PropertySource(value = { "classpath:application.properties" })
@EnableJpaRepositories(basePackages = {"com.pcitc.fms.dal.dao"})
@ComponentScan("com.pcitc.fms")
@EnableTransactionManagement
public class MySpringConfiguration {
	@Autowired
	private Environment env;

	@Bean
	@Autowired
	public DataSource dataSource(DatabasePopulator populator) throws PropertyVetoException {
		DataSource dataSource = null;
		String dataSourceName = env.getProperty("jdbc.datasource");
		if ((dataSourceName != null) && (dataSourceName.equals("hikari"))) {
			dataSource = setHikariDataSource();
		} else {
			dataSource = setC3P0DataSource();
		}
		DatabasePopulatorUtils.execute(populator, dataSource);
		return dataSource;
	}
	
	private String DecryptUserName() {
		String username = env.getProperty("jdbc.username");
		String secure = env.getProperty("jdbc.secure");
		if ((StringUtils.isEmpty(secure)) || (!secure.trim().equals("1"))) {
			return username;
		}
		return DesEncryptUtil.desDecrypt2String(username);
	}

	private String DecryptPassword() {
		String password = env.getProperty("jdbc.password");
		String secure = env.getProperty("jdbc.secure");
		if ((StringUtils.isEmpty(secure)) || (!secure.trim().equals("1"))) {
			return password;
		}
		return DesEncryptUtil.desDecrypt2String(password);
	}

	private DataSource setHikariDataSource() {
		HikariDataSource hiDataSource = new HikariDataSource();

		hiDataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
		hiDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
		hiDataSource.setUsername(DecryptUserName());
		hiDataSource.setPassword(DecryptPassword());

		int minSize = env.getProperty("pool.min_size") == null
				? 10
				: Integer.parseInt(env.getProperty("pool.min_size"));
		int maxSize = env.getProperty("pool.max_size") == null
				? 2000
				: Integer.parseInt(env.getProperty("pool.max_size"));
		int connTime = env.getProperty("pool.conn_time") == null
				? 3000
				: Integer.parseInt(env.getProperty("pool.conn_time"));
		int lifeTime = env.getProperty("pool.life_time") == null
				? 1800000
				: Integer.parseInt(env.getProperty("pool.life_time"));

		hiDataSource.setMinimumIdle(minSize);
		hiDataSource.setMaximumPoolSize(maxSize);
		hiDataSource.setConnectionTimeout(connTime);
		hiDataSource.setMaxLifetime(lifeTime);

		boolean cachePrepStmts = env.getProperty("pool.cachePrepStmts") == null
				? true
				: Boolean.parseBoolean(env.getProperty("pool.cachePrepStmts"));
		int prepStmtCacheSize = env.getProperty("pool.prepStmtCacheSize") == null
				? 10
				: Integer.parseInt(env.getProperty("pool.prepStmtCacheSize"));
		int prepStmtCacheSqlLimit = env.getProperty("pool.prepStmtCacheSqlLimit") == null
				? 20
				: Integer.parseInt(env.getProperty("pool.prepStmtCacheSqlLimit"));

		hiDataSource.addDataSourceProperty("cachePrepStmts", Boolean.valueOf(cachePrepStmts));
		hiDataSource.addDataSourceProperty("prepStmtCacheSize", Integer.valueOf(prepStmtCacheSize));
		hiDataSource.addDataSourceProperty("prepStmtCacheSqlLimit", Integer.valueOf(prepStmtCacheSqlLimit));
		return hiDataSource;
	}

	private DataSource setC3P0DataSource() throws PropertyVetoException {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		dataSource.setDriverClass(env.getProperty("jdbc.driverClassName"));
		dataSource.setJdbcUrl(env.getProperty("jdbc.url"));
		dataSource.setUser(DecryptUserName());
		dataSource.setPassword(DecryptPassword());

		dataSource.setMaxPoolSize(
				env.getProperty("pool.max_size") == null ? 2000 : Integer.parseInt(env.getProperty("pool.max_size")));
		dataSource.setInitialPoolSize(
				env.getProperty("pool.init_size") == null ? 200 : Integer.parseInt(env.getProperty("pool.init_size")));
		dataSource.setMinPoolSize(
				env.getProperty("pool.min_size") == null ? 200 : Integer.parseInt(env.getProperty("pool.min_size")));

		String strTestConnectionOnCheckout = env.getProperty("pool.testConnectionOnCheckout");
		boolean testConnectionOnCheckout = strTestConnectionOnCheckout == null
				? false
				: Boolean.parseBoolean(strTestConnectionOnCheckout);
		dataSource.setTestConnectionOnCheckout(testConnectionOnCheckout);
		dataSource.setAcquireIncrement(env.getProperty("pool.acquire_increment") == null
				? 1
				: Integer.parseInt(env.getProperty("pool.acquire_increment")));
		dataSource.setIdleConnectionTestPeriod(env.getProperty("pool.idle_test_period") == null
				? 30
				: Integer.parseInt(env.getProperty("pool.idle_test_period")));
		dataSource.setCheckoutTimeout(
				env.getProperty("pool.timeout") == null ? 3000 : Integer.parseInt(env.getProperty("pool.timeout")));
		return dataSource;
	}

	@Bean
	@Autowired
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setDataSource(dataSource);
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(Boolean.FALSE.booleanValue());
		vendorAdapter.setShowSql(Boolean.TRUE.booleanValue());
		factory.setDataSource(dataSource);
		factory.setJpaVendorAdapter(vendorAdapter);
		String pojo_path = env.getProperty("scan.pojo_path");
		factory.setPackagesToScan(pojo_path.split(","));
		Properties jpaProperties = new Properties();
		jpaProperties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		jpaProperties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
//		jpaProperties.put("hibernate.query.plan_cache_max_size", env.getProperty("hibernate.query.plan_cache_max_size"));
//		jpaProperties.put("hibernate.query.plan_parameter_metadata_max_size", env.getProperty("hibernate.query.plan_parameter_metadata_max_size"));
		factory.setJpaProperties(jpaProperties);

		return factory;
	}

	@Bean
	@Autowired
	public PlatformTransactionManager transactionManager(LocalContainerEntityManagerFactoryBean entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory.getObject());
	}

	@Bean
	@Autowired
	public DatabasePopulator databasePopulator() {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.setContinueOnError(false);
		if ((env.getProperty("db.script_path") != null)
				&& (env.getProperty("db.script_path").toString().trim().length() > 0)) {
			populator.addScript(new ClassPathResource(env.getProperty("db.script_path")));
		}
		return populator;
	}
}
