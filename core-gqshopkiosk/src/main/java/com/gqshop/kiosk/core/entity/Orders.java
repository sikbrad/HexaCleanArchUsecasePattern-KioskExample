package com.gqshop.kiosk.core.entity;

import java.util.List;

public class Orders {

	private String status;
	private int id;
	private String createdAt;
	private List<OrdersHasMenu> menus;

	public int getId() {
		return id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public List<OrdersHasMenu> getMenus(){
		return menus;
	}
	public void setMenus(List<OrdersHasMenu>  menus) {
		this.menus = menus;
	}
	
	public Orders(int id,  String status, String createdAt) {
		super();
		this.id = id;
		this.status = status;
		this.createdAt = createdAt;
	}

}
