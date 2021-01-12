package com.gqshop.kiosk.core.usecase.staff.processing;

public interface MarkOrderAsTakenPort {
	int updateOrderStatusWithId(int id, String status);
}
