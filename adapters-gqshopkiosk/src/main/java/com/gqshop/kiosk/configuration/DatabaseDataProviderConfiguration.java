package com.gqshop.kiosk.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import com.gqshop.kiosk.persistence.CustomerOrderingJdbcRepository;

@Configuration
public class DatabaseDataProviderConfiguration {

	@Bean
	public CustomerOrderingJdbcRepository customerOrderingJdbcRepository(JdbcTemplate jdbcTemplate) {		
		return new CustomerOrderingJdbcRepository(jdbcTemplate);
	}
}
	
