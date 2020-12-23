package com.gqshop.kiosk.core.entity;

import java.util.UUID;

public class FoodMenu{
	private UUID id;
	private String name;
	private String description;
	private String imageUrl;

	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public UUID getId() {
		return id;
	}
	
	public FoodMenu(UUID id, String name, String description, String imageUrl) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.imageUrl = imageUrl;
	}
	public FoodMenu(String name, String description, String imageUrl) {
		super();
		this.id = UUID.randomUUID();
		this.name = name;
		this.description = description;
		this.imageUrl = imageUrl;
	}
	public FoodMenu(FoodMenu foodMenu) {
		super();		
		this.id = foodMenu.id;
		this.name = foodMenu.name;
		this.description = foodMenu.description;
		this.imageUrl = foodMenu.imageUrl;
	}
	
}
