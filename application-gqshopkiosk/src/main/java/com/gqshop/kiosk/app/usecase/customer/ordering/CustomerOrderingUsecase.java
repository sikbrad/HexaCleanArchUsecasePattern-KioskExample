package com.gqshop.kiosk.app.usecase.customer.ordering;

import java.util.Collection;

import entity.FoodMenu;

public class CustomerOrderingUsecase {
	private final GetAllFoodMenuPort getAllFoodMenuUseCase;
	private final GetFoodMenuWithIdPort getFoodMenuWithIdUseCase;
	private final GetFoodMenuWithNamePort getFoodMenuWithNameUseCase;
	
	public CustomerOrderingUsecase(
			GetAllFoodMenuPort getAllFoodMenuUseCase,
			GetFoodMenuWithIdPort getFoodMenuWithIdUseCase,
			GetFoodMenuWithNamePort getFoodMenuWithNameUseCase) {
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
