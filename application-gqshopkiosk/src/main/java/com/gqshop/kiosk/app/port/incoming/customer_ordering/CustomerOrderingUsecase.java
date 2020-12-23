package com.gqshop.kiosk.app.port.incoming.customer_ordering;

import java.util.Collection;

import com.gqshop.kiosk.app.domain.FoodMenu;

public class CustomerOrderingUsecase {
	private final GetAllFoodMenuUseCase getAllFoodMenuUseCase;
	private final GetFoodMenuWithIdUseCase getFoodMenuWithIdUseCase;
	private final GetFoodMenuWithNameUseCase getFoodMenuWithNameUseCase;
	
	public CustomerOrderingUsecase(GetAllFoodMenuUseCase getAllFoodMenuUseCase,
			GetFoodMenuWithIdUseCase getFoodMenuWithIdUseCase, GetFoodMenuWithNameUseCase getFoodMenuWithNameUseCase) {
		super();
		this.getAllFoodMenuUseCase = getAllFoodMenuUseCase;
		this.getFoodMenuWithIdUseCase = getFoodMenuWithIdUseCase;
		this.getFoodMenuWithNameUseCase = getFoodMenuWithNameUseCase;
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
	
	
	
}
