package com.gqshop.kiosk.core.usecase.staff.processing;

import com.gqshop.kiosk.core.entity.Order;

public interface GetOrderWithIdPort {
	Order getWithId(int id);
}
