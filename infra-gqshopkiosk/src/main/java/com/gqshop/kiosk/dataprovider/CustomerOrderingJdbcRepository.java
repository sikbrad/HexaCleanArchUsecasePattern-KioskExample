package com.gqshop.kiosk.dataprovider;

import java.sql.ResultSet;
import java.util.Collection;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.gqshop.kiosk.core.entity.FoodMenu;
import com.gqshop.kiosk.core.usecase.customer.ordering.GetAllFoodMenuPort;
import com.gqshop.kiosk.core.usecase.customer.ordering.GetFoodMenuWithIdPort;
import com.gqshop.kiosk.core.usecase.customer.ordering.GetFoodMenuWithNamePort;

@Component
public class CustomerOrderingJdbcRepository implements GetAllFoodMenuPort, GetFoodMenuWithIdPort, GetFoodMenuWithNamePort{
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private JdbcTemplate jdbcTemplate;

	public CustomerOrderingJdbcRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public FoodMenu getWithName(String name) {
		FoodMenu foodMenu = null;		
		foodMenu = jdbcTemplate.queryForObject(String.format("SELECT id,name,description,image_url FROM GQSHOP.FOOD_MENU WHERE name = '%s';",name), rowMapper);
		return foodMenu;
	}

	@Override
	public FoodMenu getWithId(String id) {
		FoodMenu foodMenu = jdbcTemplate.queryForObject(
				"SELECT id,name,description,image_url FROM GQSHOP.FOOD_MENU WHERE id = ?;", rowMapper, id.toString());
		return foodMenu;
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
