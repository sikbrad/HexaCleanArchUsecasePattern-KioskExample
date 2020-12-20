package com.gqshop.kiosk.entrypoints.rest.customer_ordering;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CustomerOrderingEntrypointRest implements CommandLineRunner {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void run(String... args) throws Exception {
		logger.info("CustomerOrderingEntrypointRest bean created");
	}
	

//	@GetMapping(value = "/foodmenu")
//	public Collection<FoodMenuDto> getFoodMenuList() {
//		Collection<FoodMenu> allFoodMenu = customerOrderingGetFoodMenuUsecase.getAllFoodMenu();
//		return toDto(allFoodMenu);
//	}

}
