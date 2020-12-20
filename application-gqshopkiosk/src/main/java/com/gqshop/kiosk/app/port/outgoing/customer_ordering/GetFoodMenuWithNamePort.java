package com.gqshop.kiosk.app.port.outgoing.customer_ordering;

import java.util.Optional;

import com.gqshop.kiosk.app.domain.FoodMenu;

public interface GetFoodMenuWithNamePort {
	Optional<FoodMenu> getWithName(String name);

}
