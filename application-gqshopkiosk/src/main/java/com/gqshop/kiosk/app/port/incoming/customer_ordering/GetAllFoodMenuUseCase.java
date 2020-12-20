package com.gqshop.kiosk.app.port.incoming.customer_ordering;

import java.util.Collection;

import com.gqshop.kiosk.app.domain.FoodMenu;

public interface GetAllFoodMenuUseCase {
    Collection<FoodMenu> getAll();

}
