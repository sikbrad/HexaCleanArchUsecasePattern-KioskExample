package com.gqshop.kiosk.app.port.outgoing.customer_ordering;

import com.gqshop.kiosk.app.domain.FoodMenu;

public interface GetFoodMenuWithNamePort {
	FoodMenu getWithName(String name);

}
