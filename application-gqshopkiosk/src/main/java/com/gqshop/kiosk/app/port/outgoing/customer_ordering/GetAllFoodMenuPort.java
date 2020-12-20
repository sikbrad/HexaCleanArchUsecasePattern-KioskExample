package com.gqshop.kiosk.app.port.outgoing.customer_ordering;

import java.util.Collection;

import com.gqshop.kiosk.app.domain.FoodMenu;

public interface GetAllFoodMenuPort {
    Collection<FoodMenu> getAll();

}
