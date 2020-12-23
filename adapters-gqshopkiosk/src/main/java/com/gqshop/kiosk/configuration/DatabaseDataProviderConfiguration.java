package com.gqshop.kiosk.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;

import com.gqshop.kiosk.dataprovider.CustomerOrderingJdbcRepository;

@Configuration
public class DatabaseDataProviderConfiguration {

	@Bean
	@Profile("default")
	public CustomerOrderingJdbcRepository customerOrderingJdbcRepository(JdbcTemplate jdbcTemplate) {		
		return new CustomerOrderingJdbcRepository(jdbcTemplate);
	}
	
}
	
