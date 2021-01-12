package com.gqshop.kiosk.core.entity;

import java.sql.Timestamp;
import java.util.List;

public class Order {

	private String status;
	private int id;
	private Timestamp createdAt;
	private List<FoodMenu> menus;

	public int getId() {
		return id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	public List<FoodMenu> getMenus(){
		return menus;
	}
	public void setMenus(List<FoodMenu>  menus) {
		this.menus = menus;
	}
	
	public Order(int id,  String status, Timestamp createdAt) {
		super();
		this.id = id;
		this.status = status;
		this.createdAt = createdAt;
	}

}
