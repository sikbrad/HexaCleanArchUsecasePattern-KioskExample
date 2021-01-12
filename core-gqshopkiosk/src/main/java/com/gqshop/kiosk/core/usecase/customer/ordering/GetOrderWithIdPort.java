package com.gqshop.kiosk.core.usecase.customer.ordering;

import com.gqshop.kiosk.core.entity.Orders;

public interface GetOrderWithIdPort {
	Orders getWithId(int id);
}
