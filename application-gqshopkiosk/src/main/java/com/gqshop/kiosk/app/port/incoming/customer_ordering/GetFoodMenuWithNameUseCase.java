package com.gqshop.kiosk.app.port.incoming.customer_ordering;

import com.gqshop.kiosk.app.domain.FoodMenu;

public interface GetFoodMenuWithNameUseCase {
	FoodMenu getWithName(String name);

}
