package com.gqshop.kiosk.core.usecase.staff.processing;

import java.util.Collection;

import com.gqshop.kiosk.core.entity.Order;

public interface GetReceivedOrdersPort {
	Collection<Order> getAll();
}
