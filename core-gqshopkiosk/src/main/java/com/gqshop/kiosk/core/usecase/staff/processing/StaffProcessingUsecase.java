package com.gqshop.kiosk.core.usecase.staff.processing;

import java.util.Collection;

import com.gqshop.kiosk.core.entity.Order;

public class StaffProcessingUsecase {

	private final GetReceivedOrdersPort getReceivedOrdersPort;
	private final MarkOrderAsCookingStartedPort markOrderAsCookingStartedPort;
	private final MarkOrderAsTakenPort markOrderAsTakenPort;
	private final MarkOrderAsCookingDonePort markOrderAsCookingDonePort;
	private final VerifyExistOrderIdPort verifyExistOrderIdPort;
	private final GetOrderWithIdPort getOrderWithIdPort;
	
	public StaffProcessingUsecase(GetReceivedOrdersPort getReceivedOrdersPort,
			GetOrderWithIdPort getOrderWithIdPort,
			MarkOrderAsCookingStartedPort markOrderAsCookingStartedPort,
			MarkOrderAsCookingDonePort markOrderAsCookingDonePort,
			MarkOrderAsTakenPort markOrderAsTakenPort,
			VerifyExistOrderIdPort verifyExistOrderIdPort){
		
		super();
		this.getReceivedOrdersPort = getReceivedOrdersPort;
		this.getOrderWithIdPort = getOrderWithIdPort;
		this.markOrderAsCookingStartedPort = markOrderAsCookingStartedPort;
		this.markOrderAsCookingDonePort = markOrderAsCookingDonePort;
		this.markOrderAsTakenPort = markOrderAsTakenPort;
		this.verifyExistOrderIdPort = verifyExistOrderIdPort;
	}
	
	public Collection<Order> getReceivedOrdersAll(){
		return getReceivedOrdersPort.getAll();
	}
	public Order getWithId(int id) {
		return getOrderWithIdPort.getWithId(id);
	}
	public int markOrderAsCookingStarted(int id, String status) {
		return markOrderAsCookingStartedPort.updateOrderStatusWithId(id, status);
	}
	public int markOrderAsCookingDone(int id, String status) {
		return markOrderAsCookingDonePort.updateOrderStatusWithId(id, status);
	}
	public int markOrderAsTaken(int id, String status) {
		return markOrderAsTakenPort.updateOrderStatusWithId(id, status);
	}
	public boolean bExistOrderId(int id) {
		return verifyExistOrderIdPort.bExistOrderId(id);
	}
	
}
