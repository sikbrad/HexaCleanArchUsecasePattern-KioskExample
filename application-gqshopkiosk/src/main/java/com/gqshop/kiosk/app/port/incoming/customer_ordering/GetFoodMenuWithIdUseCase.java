package com.gqshop.kiosk.app.port.incoming.customer_ordering;

import java.util.Optional;
import java.util.UUID;

import com.gqshop.kiosk.app.domain.FoodMenu;

public interface GetFoodMenuWithIdUseCase {
	Optional<FoodMenu> getWithId(String id);

}
