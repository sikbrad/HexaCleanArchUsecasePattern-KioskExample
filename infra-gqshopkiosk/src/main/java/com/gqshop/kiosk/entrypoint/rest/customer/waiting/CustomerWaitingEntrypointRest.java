package com.gqshop.kiosk.entrypoint.rest.customer.waiting;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gqshop.kiosk.core.entity.Order;
import com.gqshop.kiosk.core.usecase.customer.waiting.CustomerWaitingUsecase;
import com.gqshop.kiosk.entrypoint.model.FoodMenuDto;

@RestController
@RequestMapping("/api")
public class CustomerWaitingEntrypointRest implements CommandLineRunner {
	
	ObjectMapper mapper = new ObjectMapper(); 
	
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CustomerWaitingUsecase customerWaitingUsecase;
	
	@Override
	public void run(String... args) throws Exception {
		logger.info("CustomerWaitingEntrypointRest bean created");
	}
	
	@GetMapping(value = "/order/one/status/{status}")
	public Collection<Order> getFoodMenuList(@PathVariable(value = "status") String status) {
		return customerWaitingUsecase.getCookedOrder(status);
	}
	
}
