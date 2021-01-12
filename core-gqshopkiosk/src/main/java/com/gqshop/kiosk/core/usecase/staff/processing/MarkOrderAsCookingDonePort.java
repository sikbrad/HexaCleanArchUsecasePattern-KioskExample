package com.gqshop.kiosk.core.usecase.staff.processing;

public interface MarkOrderAsCookingDonePort {
	int updateOrderStatusWithId(int id, String status);
}
