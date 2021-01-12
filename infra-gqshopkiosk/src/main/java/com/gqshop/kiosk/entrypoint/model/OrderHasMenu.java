package com.gqshop.kiosk.entrypoint.model;

public class OrderHasMenu {
	private int orderId;
	private String menuId;
	private int amount;
	private String menuName;
	
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	
	public OrderHasMenu(int orderId,  String menuId, int amount, String menuName) {
		super();
		this.orderId = orderId;
		this.menuId = menuId;
		this.amount = amount;
		this.menuName = menuName;
	}

}
