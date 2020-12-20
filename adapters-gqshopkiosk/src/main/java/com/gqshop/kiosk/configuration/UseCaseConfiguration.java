package com.gqshop.kiosk.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gqshop.kiosk.app.port.outgoing.customer_ordering.GetAllFoodMenuPort;
import com.gqshop.kiosk.app.port.outgoing.customer_ordering.GetFoodMenuWithIdPort;
import com.gqshop.kiosk.app.port.outgoing.customer_ordering.GetFoodMenuWithNamePort;
import com.gqshop.kiosk.app.services.CustomerOrderingService;
import com.gqshop.kiosk.persistence.CustomerOrderingJdbcRepository;

@Configuration
public class UseCaseConfiguration  implements CommandLineRunner{

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Bean
	CustomerOrderingService customerOrderingService(CustomerOrderingJdbcRepository customerOrderingJdbcRepository) {
		return new CustomerOrderingService(
				(GetAllFoodMenuPort) customerOrderingJdbcRepository,
				(GetFoodMenuWithIdPort) customerOrderingJdbcRepository,
				(GetFoodMenuWithNamePort) customerOrderingJdbcRepository);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("BeanConfiguration bean created");
	}
}
