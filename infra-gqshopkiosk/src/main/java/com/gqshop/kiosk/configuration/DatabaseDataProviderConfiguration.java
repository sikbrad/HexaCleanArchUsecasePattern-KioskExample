package com.gqshop.kiosk.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;

import com.gqshop.kiosk.dataprovider.CustomerOrderingJdbcDataProvider;

@Configuration
public class DatabaseDataProviderConfiguration {

	@Bean
	public CustomerOrderingJdbcDataProvider customerOrderingJdbcDataProvider(JdbcTemplate jdbcTemplate) {		
		return new CustomerOrderingJdbcDataProvider(jdbcTemplate);
	}
	
}
	
