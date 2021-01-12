package com.gqshop.kiosk.core.usecase.staff.processing;

public interface MarkOrderAsCookingStartedPort {
	int updateOrderStatusWithId(int id, String status);
}
