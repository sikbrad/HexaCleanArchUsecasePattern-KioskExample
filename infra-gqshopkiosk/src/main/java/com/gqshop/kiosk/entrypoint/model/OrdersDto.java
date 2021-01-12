package com.gqshop.kiosk.entrypoint.model;

import java.util.List;

public class OrdersDto {

	private final String status;
	private final int id;
	private final List<OrderHasMenu> menus;
	private final String createdAt;
	
	public OrdersDto(int id, String status, String createdAt, List<OrderHasMenu> menus) {
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
	public List<OrderHasMenu> getMenus(){
		return menus;
	}
	public String getCreatedAt() {
		return createdAt;
	}
}
