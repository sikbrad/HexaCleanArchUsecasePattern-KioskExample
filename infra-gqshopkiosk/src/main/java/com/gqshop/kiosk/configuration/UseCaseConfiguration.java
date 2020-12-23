package com.gqshop.kiosk.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gqshop.kiosk.core.usecase.customer.ordering.CustomerOrderingUsecase;
import com.gqshop.kiosk.core.usecase.customer.ordering.GetAllFoodMenuPort;
import com.gqshop.kiosk.core.usecase.customer.ordering.GetFoodMenuWithIdPort;
import com.gqshop.kiosk.core.usecase.customer.ordering.GetFoodMenuWithNamePort;

@Configuration
public class UseCaseConfiguration  implements CommandLineRunner{

	Logger logger = LoggerFactory.getLogger(this.getClass());

    @Bean
    public CustomerOrderingUsecase customerOrderingGetFoodMenuUsecase(GetAllFoodMenuPort getAllFoodMenuPort, GetFoodMenuWithIdPort getFoodMenuWithIdPort, GetFoodMenuWithNamePort getFoodMenuWithNamePort) {
        return new CustomerOrderingUsecase(getAllFoodMenuPort,getFoodMenuWithIdPort,getFoodMenuWithNamePort);
    }

	@Override
	public void run(String... args) throws Exception {
		logger.info("BeanConfiguration bean created");
	}
}
