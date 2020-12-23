package com.gqshop.kiosk.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gqshop.kiosk.app.port.incoming.customer_ordering.CustomerOrderingUsecase;
import com.gqshop.kiosk.app.port.incoming.customer_ordering.GetAllFoodMenuUseCase;
import com.gqshop.kiosk.app.port.incoming.customer_ordering.GetFoodMenuWithIdUseCase;
import com.gqshop.kiosk.app.port.incoming.customer_ordering.GetFoodMenuWithNameUseCase;

@Configuration
public class UseCaseConfiguration  implements CommandLineRunner{

	Logger logger = LoggerFactory.getLogger(this.getClass());

//	@Bean
//	CustomerOrderingService customerOrderingService(CustomerOrderingJdbcRepository customerOrderingJdbcRepository) {
//		return new CustomerOrderingService(
//				(GetAllFoodMenuPort) customerOrderingJdbcRepository,
//				(GetFoodMenuWithIdPort) customerOrderingJdbcRepository,
//				(GetFoodMenuWithNamePort) customerOrderingJdbcRepository);
//	}
	

    @Bean
    public CustomerOrderingUsecase customerOrderingGetFoodMenuUsecase(GetAllFoodMenuUseCase getAllFoodMenuUseCase, GetFoodMenuWithIdUseCase getFoodMenuWithIdUseCase, GetFoodMenuWithNameUseCase getFoodMenuWithNameUseCase) {
        return new CustomerOrderingUsecase(getAllFoodMenuUseCase,getFoodMenuWithIdUseCase,getFoodMenuWithNameUseCase);
    }

	@Override
	public void run(String... args) throws Exception {
		logger.info("BeanConfiguration bean created");
	}
}
