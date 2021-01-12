package com.gqshop.kiosk.core.usecase.staff.processing;

import java.util.List;

import com.gqshop.kiosk.core.entity.FoodMenu;

public interface GetFoodMenuWithOrderIdPort {
	List<FoodMenu> getWithOrderId(int id);
}
