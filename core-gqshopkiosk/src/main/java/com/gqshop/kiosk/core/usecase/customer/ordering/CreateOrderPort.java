package com.gqshop.kiosk.core.usecase.customer.ordering;

import java.util.List;
import java.util.Map;

public interface CreateOrderPort {
	int createOrder(List<Map<String, Object>> orders);
}
