package com.gqshop.kiosk.app.usecase.customer.ordering;

import entity.FoodMenu;

public interface GetFoodMenuWithNamePort {
	FoodMenu getWithName(String name);

}
