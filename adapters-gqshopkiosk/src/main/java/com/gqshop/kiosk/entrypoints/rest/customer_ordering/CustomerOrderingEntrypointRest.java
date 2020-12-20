package com.gqshop.kiosk.entrypoints.rest.customer_ordering;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
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
		logger.info("getFoodMenuList called");
		Collection<FoodMenu> allFoodMenu = customerOrderingService.getAll();
		return toFoodMenuCollectionDto(allFoodMenu);
	}

	@GetMapping(value = "/foodmenu/{foodname}")
	public FoodMenuDto getFoodMenuWithName(@PathVariable(value = "foodname") String foodname) {
		logger.info("getFoodMenuWithName called with param [{}]", foodname);
		var dto = toFoodMenuDto(customerOrderingService.getWithName(foodname)).orElse(null);	
		return dto;
	}

	private Collection<FoodMenuDto> toFoodMenuCollectionDto(Collection<FoodMenu> allFoodMenu) {
		//optional stream to concrete list
		//@ref https://www.baeldung.com/java-filter-stream-of-optional
		List<FoodMenuDto> filteredList = 
				allFoodMenu.stream().map(x -> toFoodMenuDto(Optional.of(x)))
				  .filter(Optional::isPresent)
				  .map(Optional::get)
				  .collect(Collectors.toList());
		return filteredList;
	}

	private Optional<FoodMenuDto> toFoodMenuDto(Optional<FoodMenu> optional) {
		if(optional.isEmpty()) {
			return Optional.empty();
		}
		return Optional.ofNullable(new FoodMenuDto(optional.get().getId(), optional.get().getName(), optional.get().getDescription(), optional.get().getImageUrl()));
	}
	
}
