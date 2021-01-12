package com.gqshop.kiosk.core.usecase.customer.ordering;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.gqshop.kiosk.core.entity.FoodMenu;
import com.gqshop.kiosk.core.entity.Orders;

public class CustomerOrderingUsecase {
	private final GetAllFoodMenuPort getAllFoodMenuUseCase;
	private final GetFoodMenuWithIdPort getFoodMenuWithIdUseCase;
	private final GetFoodMenuWithNamePort getFoodMenuWithNameUseCase;
	private final CreateOrderPort createOrderPort;
	private final GetOrderWithIdPort getOrderWithIdPort;

	public CustomerOrderingUsecase(
			GetAllFoodMenuPort getAllFoodMenuPort,
			GetFoodMenuWithIdPort getFoodMenuWithIdPort,
			GetFoodMenuWithNamePort getFoodMenuWithNamePort,
			CreateOrderPort createOrderPort,
			GetOrderWithIdPort getOrderWithIdPort) {
		super();
		this.getAllFoodMenuUseCase = getAllFoodMenuPort;
		this.getFoodMenuWithIdUseCase = getFoodMenuWithIdPort;
		this.getFoodMenuWithNameUseCase = getFoodMenuWithNamePort;
		this.createOrderPort = createOrderPort;
		this.getOrderWithIdPort = getOrderWithIdPort;
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
	public Orders getWithId(int id) {
		return getOrderWithIdPort.getWithId(id);
	}
}
