package com.gqshop.kiosk.app.port.incoming.customer_ordering;

import com.gqshop.kiosk.app.domain.FoodMenu;

public interface GetFoodMenuWithIdUseCase {
	FoodMenu getWithId(String id);

}
