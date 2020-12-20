package com.gqshop.kiosk.entrypoints.rest.customer_ordering;

import java.util.Collection;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gqshop.kiosk.app.domain.FoodMenu;
import com.gqshop.kiosk.app.port.incoming.customer_ordering.GetAllFoodMenuUseCase;
import com.gqshop.kiosk.app.services.CustomerOrderingService;

@RestController
@RequestMapping("/api")
public class CustomerOrderingEntrypointRest implements CommandLineRunner {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	

	@Autowired
    CustomerOrderingService customerOrderingService;
	
	@Override
	public void run(String... args) throws Exception {
		logger.info("CustomerOrderingEntrypointRest bean created");
	}
	

	@GetMapping(value = "/foodmenu")
	public Collection<FoodMenuDto> getFoodMenuList() {
		Collection<FoodMenu> allFoodMenu = customerOrderingService.getAll();
		return toFoodMenuCollectionDto(allFoodMenu);
	}

	private Collection<FoodMenuDto> toFoodMenuCollectionDto(Collection<FoodMenu> allFoodMenu) {
		return allFoodMenu.stream().map(x -> toFoodMenuDto(x)).collect(Collectors.toList());
	}

	private FoodMenuDto toFoodMenuDto(FoodMenu foodMenu) {
		return new FoodMenuDto(foodMenu.getId(), foodMenu.getName(), foodMenu.getDescription(), foodMenu.getImageUrl());
	}
	
}
