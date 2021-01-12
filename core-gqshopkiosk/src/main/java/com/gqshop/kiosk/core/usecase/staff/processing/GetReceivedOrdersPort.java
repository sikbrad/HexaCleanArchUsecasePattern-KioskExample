package com.gqshop.kiosk.core.usecase.staff.processing;

import java.util.Collection;

import com.gqshop.kiosk.core.entity.Orders;

public interface GetReceivedOrdersPort {
	Collection<Orders> getAll();
}
