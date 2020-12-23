package com.gqshop.kiosk.entrypoint.rest.customer.ordering;

import java.util.UUID;

public class FoodMenuDto {
	private final String id;
	private final String name;
	private final String description;
	private final String imageUrl;

	public FoodMenuDto(UUID id, String name, String description, String imageUrl) {
		super();
		this.id = id.toString();
		this.name = name;
		this.description = description;
		this.imageUrl = imageUrl;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getImageUrl() {
		return imageUrl;
	}

}
