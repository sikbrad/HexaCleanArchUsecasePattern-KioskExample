package com.gqshop.kiosk.persistence;

import java.sql.ResultSet;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.gqshop.kiosk.app.domain.FoodMenu;
import com.gqshop.kiosk.app.port.outgoing.customer_ordering.GetAllFoodMenuPort;
import com.gqshop.kiosk.app.port.outgoing.customer_ordering.GetFoodMenuWithIdPort;
import com.gqshop.kiosk.app.port.outgoing.customer_ordering.GetFoodMenuWithNamePort;

public class CustomerOrderingJdbcRepository implements GetAllFoodMenuPort, GetFoodMenuWithIdPort, GetFoodMenuWithNamePort{
	private JdbcTemplate jdbcTemplate;

	public CustomerOrderingJdbcRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public Optional<FoodMenu> getWithName(String name) {
		FoodMenu foodMenu = jdbcTemplate.queryForObject(String.format("SELECT id,name,description,image_url FROM GQSHOP.FOOD_MENU WHERE name = '%s';",name), rowMapper);
		return Optional.ofNullable(foodMenu);
	}

	@Override
	public Optional<FoodMenu> getWithId(String id) {
		FoodMenu foodMenu = jdbcTemplate.queryForObject(
				"SELECT id,name,description,image_url FROM GQSHOP.FOOD_MENU WHERE id = ?;", rowMapper, id.toString());
		return Optional.ofNullable(foodMenu);
	}

	@Override
	public Collection<FoodMenu> getAll() {
		return jdbcTemplate.query("SELECT id,name,description,image_url FROM GQSHOP.FOOD_MENU;", rowMapper);
	}
	

	// @ref rowmapper tutorial https://www.youtube.com/watch?v=XL8lp0cCVks
	private RowMapper<FoodMenu> rowMapper = (ResultSet rs, int rowNum) -> {

		UUID id = UUID.fromString(rs.getString(1));
		String name = rs.getString(2);
		String description = rs.getString(3);
		String imageUrl = rs.getString(4);

		FoodMenu foodMenu = new FoodMenu(id, name, description, imageUrl);
		return foodMenu;

	};

}
