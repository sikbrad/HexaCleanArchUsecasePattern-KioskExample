package com.gqshop.kiosk.entrypoint.rest.customer.ordering;

import java.util.List;
import com.gqshop.kiosk.core.entity.OrdersHasMenu;

public class OrdersDto {

	private final String status;
	private final int id;
	private final List<OrdersHasMenu> menus;
	private final String createdAt;
	
	public OrdersDto(int id, String status, String createdAt, List<OrdersHasMenu> menus) {
		super();
		this.id=id;
		this.status=status;
		this.createdAt=createdAt;
		this.menus=menus;
	}
	
	public int getId() {
		return id;
	}
	public String getStatus() {
		return status;
	}
	public List<OrdersHasMenu> getMenus(){
		return menus;
	}
	public String getCreatedAt() {
		return createdAt;
	}
}
