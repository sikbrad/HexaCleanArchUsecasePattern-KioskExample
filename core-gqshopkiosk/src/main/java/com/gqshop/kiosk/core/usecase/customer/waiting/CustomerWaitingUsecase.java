package com.gqshop.kiosk.core.usecase.customer.waiting;

import java.util.Collection;

import com.gqshop.kiosk.core.entity.Order;

public class CustomerWaitingUsecase {

	private final GetCookedOrderPort getCookedOrderPort;
	private final GetNotCookedOrderPort getNotCookedOrderPort;
	
	public CustomerWaitingUsecase(
			GetCookedOrderPort getCookedOrderPort,
			GetNotCookedOrderPort getNotCookedOrderPort) {
		
		super();
		this.getCookedOrderPort = getCookedOrderPort;
		this.getNotCookedOrderPort = getNotCookedOrderPort;
	}
	
	public Collection<Order> getCookedOrder(String status){
		return getCookedOrderPort.getOrderWithStatus(status);
	}
	
	public Collection<Order> getNotCookedOrder(String status){
		return getNotCookedOrderPort.getOrderWithStatus(status);
	}
}
