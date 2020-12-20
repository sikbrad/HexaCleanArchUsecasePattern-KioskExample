package com.gqshop.kiosk.app.port.outgoing.customer_ordering;

import java.util.Optional;
import java.util.UUID;

import com.gqshop.kiosk.app.domain.FoodMenu;

public interface GetFoodMenuWithIdPort {
	Optional<FoodMenu> getWithId(String id);

}
