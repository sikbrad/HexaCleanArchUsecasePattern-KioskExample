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
import com.gqshop.kiosk.core.usecase.customer.ordering.GetOrderWithIdPort;
import com.gqshop.kiosk.core.usecase.customer.ordering.CreateOrderPort;
import com.gqshop.kiosk.core.usecase.staff.processing.GetReceivedOrdersPort;
import com.gqshop.kiosk.core.usecase.staff.processing.MarkOrderAsCookingDonePort;
import com.gqshop.kiosk.core.usecase.staff.processing.MarkOrderAsCookingStartedPort;
import com.gqshop.kiosk.core.usecase.staff.processing.MarkOrderAsTakenPort;
import com.gqshop.kiosk.core.usecase.staff.processing.StaffProcessingUsecase;
import com.gqshop.kiosk.core.usecase.staff.processing.VerifyExistOrderIdPort;

@Configuration
public class UseCaseConfiguration  implements CommandLineRunner{

	Logger logger = LoggerFactory.getLogger(this.getClass());

    @Bean
    public CustomerOrderingUsecase customerOrderingGetFoodMenuUsecase(
    		GetAllFoodMenuPort getAllFoodMenuPort, 
    		GetFoodMenuWithIdPort getFoodMenuWithIdPort, 
    		GetFoodMenuWithNamePort getFoodMenuWithNamePort, 
    		CreateOrderPort postOrderPort, 
    		GetOrderWithIdPort getOrderWithIdPort) {
        return new CustomerOrderingUsecase(
        		getAllFoodMenuPort,
        		getFoodMenuWithIdPort,
        		getFoodMenuWithNamePort, 
        		postOrderPort, 
        		getOrderWithIdPort);
    }
    
    @Bean
    public StaffProcessingUsecase staffProcessingUsecase(
    		GetReceivedOrdersPort getReceivedOrdersPort,
			MarkOrderAsCookingStartedPort markOrderAsCookingStartedPort,
			MarkOrderAsCookingDonePort markOrderAsCookingDonePort,
			MarkOrderAsTakenPort markOrderAsTakenPort,
			VerifyExistOrderIdPort verifyExistOrderIdPort) {
    	return new StaffProcessingUsecase(
    			getReceivedOrdersPort, 
    			markOrderAsCookingStartedPort,
    			markOrderAsCookingDonePort,
    			markOrderAsTakenPort,
    			verifyExistOrderIdPort);
    }

	@Override
	public void run(String... args) throws Exception {
		logger.info("BeanConfiguration bean created");
	}
}
