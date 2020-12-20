package com.gqshop.kiosk.app.port.incoming.customer_ordering;

import java.util.Optional;

import com.gqshop.kiosk.app.domain.FoodMenu;

public interface GetFoodMenuWithNameUseCase {
	Optional<FoodMenu> getWithName(String name);

}
