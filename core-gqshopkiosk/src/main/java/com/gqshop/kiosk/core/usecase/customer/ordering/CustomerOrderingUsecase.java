package com.gqshop.kiosk.core.usecase.customer.ordering;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.gqshop.kiosk.core.entity.FoodMenu;

public class CustomerOrderingUsecase {
	private final GetAllFoodMenuPort getAllFoodMenuUseCase;
	private final GetFoodMenuWithIdPort getFoodMenuWithIdUseCase;
	private final GetFoodMenuWithNamePort getFoodMenuWithNameUseCase;
	private final CreateOrderPort createOrderPort;

	public CustomerOrderingUsecase(
			GetAllFoodMenuPort getAllFoodMenuPort,
			GetFoodMenuWithIdPort getFoodMenuWithIdPort,
			GetFoodMenuWithNamePort getFoodMenuWithNamePort,
			CreateOrderPort createOrderPort) {
		super();
		this.getAllFoodMenuUseCase = getAllFoodMenuPort;
		this.getFoodMenuWithIdUseCase = getFoodMenuWithIdPort;
		this.getFoodMenuWithNameUseCase = getFoodMenuWithNamePort;
		this.createOrderPort = createOrderPort;
	}
	
	public Collection<FoodMenu> getAll() {
		return getAllFoodMenuUseCase.getAll();
	}
	public FoodMenu getWithId(String id) {
		return getFoodMenuWithIdUseCase.getWithId(id);
	}
	public FoodMenu getWithName(String name) {
		return getFoodMenuWithNameUseCase.getWithName(name);
	}
	public int createOrder(List<Map<String, Object>> orders) {
		return createOrderPort.createOrder(orders);
	}

}
