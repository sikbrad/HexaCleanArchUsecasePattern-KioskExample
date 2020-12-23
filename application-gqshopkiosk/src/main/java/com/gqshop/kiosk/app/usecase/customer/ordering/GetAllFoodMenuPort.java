package com.gqshop.kiosk.app.usecase.customer.ordering;

import java.util.Collection;

import entity.FoodMenu;

public interface GetAllFoodMenuPort {
    Collection<FoodMenu> getAll();

}
