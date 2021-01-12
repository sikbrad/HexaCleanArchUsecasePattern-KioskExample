package com.gqshop.kiosk.core.usecase.customer.waiting;

import java.util.Collection;

import com.gqshop.kiosk.core.entity.Order;

public interface GetCookedOrderPort {
	Collection<Order> getOrderWithStatus(String status);
}
