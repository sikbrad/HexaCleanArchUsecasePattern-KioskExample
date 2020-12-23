package com.gqshop.kiosk.core.usecase.customer.ordering;

import java.util.Collection;

import com.gqshop.kiosk.core.entity.FoodMenu;

public interface GetAllFoodMenuPort {
    Collection<FoodMenu> getAll();

}
