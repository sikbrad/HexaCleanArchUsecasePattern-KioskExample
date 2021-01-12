package com.gqshop.kiosk.configuration;

import java.io.IOException;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

@Configuration
public class DatasourceConfiguration {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private Environment env;
	  
	@Value("${datasource.driver}")
    private String dbDriverClassName;
 
    @Value("${datasource.url}")
    private String dbURL;
 
    @Value("${datasource.user}")
    private String userName;
 
    @Value("${datasource.password}")
    private String password;
	    
	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(byEnvironment("datasource.driver"));
        dataSource.setUrl(byEnvironment("datasource.url"));
        dataSource.setUsername(byEnvironment("datasource.user"));
        dataSource.setPassword(byEnvironment("datasource.password"));
        return dataSource;
	}
	
	private String byEnvironment(String before) {
        //String key = before + "." + env.getProperty("spring.profiles.active");
		 String key = before;
        return env.getProperty(key);
	}
	 
	@Bean
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(dataSource());
	}

	@Bean
	public DataSourceTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}

}
