package com.gqshop.kiosk.core.usecase.customer.ordering;

import com.gqshop.kiosk.core.entity.FoodMenu;

public interface GetFoodMenuWithNamePort {
	FoodMenu getWithName(String name);

}
