package com.gqshop.kiosk.app.usecase.customer.ordering;

import entity.FoodMenu;

public interface GetFoodMenuWithIdPort {
	FoodMenu getWithId(String id);

}
