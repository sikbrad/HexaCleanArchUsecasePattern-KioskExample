package com.gqshop.kiosk.app.services;

import java.util.Collection;
import java.util.Optional;

import com.gqshop.kiosk.app.domain.FoodMenu;
import com.gqshop.kiosk.app.port.incoming.customer_ordering.GetAllFoodMenuUseCase;
import com.gqshop.kiosk.app.port.incoming.customer_ordering.GetFoodMenuWithIdUseCase;
import com.gqshop.kiosk.app.port.incoming.customer_ordering.GetFoodMenuWithNameUseCase;
import com.gqshop.kiosk.app.port.outgoing.customer_ordering.GetAllFoodMenuPort;
import com.gqshop.kiosk.app.port.outgoing.customer_ordering.GetFoodMenuWithIdPort;
import com.gqshop.kiosk.app.port.outgoing.customer_ordering.GetFoodMenuWithNamePort;

public class CustomerOrderingService
		implements GetAllFoodMenuUseCase, GetFoodMenuWithIdUseCase, GetFoodMenuWithNameUseCase {

    private GetAllFoodMenuPort getAllFoodMenuPort;
    private GetFoodMenuWithIdPort getFoodMenuWithIdPort;
    private GetFoodMenuWithNamePort getFoodMenuWithNamePort;
	
	public CustomerOrderingService(
			GetAllFoodMenuPort getAllFoodMenuPort,
			GetFoodMenuWithIdPort getFoodMenuWithIdPort,
			GetFoodMenuWithNamePort getFoodMenuWithNamePort
			) {
		this.getAllFoodMenuPort = getAllFoodMenuPort;
		this.getFoodMenuWithIdPort = getFoodMenuWithIdPort;
		this.getFoodMenuWithNamePort = getFoodMenuWithNamePort;
	}

	@Override
	public Optional<FoodMenu> getWithName(String name) {
		return getFoodMenuWithNamePort.getWithName(name);
	}

	@Override
	public Optional<FoodMenu> getWithId(String id) {
		return getFoodMenuWithIdPort.getWithId(id);
	}

	@Override
	public Collection<FoodMenu> getAll() {		
		return getAllFoodMenuPort.getAll();
	}

}
