package com.gqshop.kiosk.entrypoints.rest.customer_ordering;

import java.util.Collection;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gqshop.kiosk.app.domain.FoodMenu;
import com.gqshop.kiosk.app.port.incoming.customer_ordering.CustomerOrderingUsecase;

@RestController
@RequestMapping("/api")
public class CustomerOrderingEntrypointRest implements CommandLineRunner {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	

//	@Autowired
//    CustomerOrderingService customerOrderingService;
	

	@Autowired
	CustomerOrderingUsecase customerOrderingUsecase;
	
	@Override
	public void run(String... args) throws Exception {
		logger.info("CustomerOrderingEntrypointRest bean created");
	}
	

	@GetMapping(value = "/foodmenu")
	public Collection<FoodMenuDto> getFoodMenuList() {
		logger.info("getFoodMenuList called");
		Collection<FoodMenu> allFoodMenu = customerOrderingUsecase.getAll();
		return toFoodMenuCollectionDto(allFoodMenu);
	}

	@GetMapping(value = "/foodmenu/{foodname}")
	public FoodMenuDto getFoodMenuWithName(@PathVariable(value = "foodname") String foodname) {
		logger.info("getFoodMenuWithName called with param [{}]", foodname);
		var dto = toFoodMenuDto(customerOrderingUsecase.getWithName(foodname));	
		return dto;
	}

	@GetMapping(value = "/foodmenu/uuid/{id}")
	public FoodMenuDto getFoodMenu(@PathVariable(value = "id") String id) {
		return toFoodMenuDto(customerOrderingUsecase.getWithId(id));
	}

	private Collection<FoodMenuDto> toFoodMenuCollectionDto(Collection<FoodMenu> allFoodMenu) {
		return allFoodMenu.stream().map(x -> toFoodMenuDto(x)).collect(Collectors.toList());
	}

	private FoodMenuDto toFoodMenuDto(FoodMenu foodMenu) {
		if(foodMenu == null) {
			return null;
		}
		return new FoodMenuDto(foodMenu.getId(), foodMenu.getName(), foodMenu.getDescription(), foodMenu.getImageUrl());
	}
	
}
