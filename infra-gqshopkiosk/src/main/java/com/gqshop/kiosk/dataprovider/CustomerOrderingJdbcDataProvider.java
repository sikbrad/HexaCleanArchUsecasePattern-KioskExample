package com.gqshop.kiosk.dataprovider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import com.gqshop.kiosk.core.entity.FoodMenu;
import com.gqshop.kiosk.core.usecase.customer.ordering.CreateOrderPort;
import com.gqshop.kiosk.core.usecase.customer.ordering.GetAllFoodMenuPort;
import com.gqshop.kiosk.core.usecase.customer.ordering.GetFoodMenuWithIdPort;
import com.gqshop.kiosk.core.usecase.customer.ordering.GetFoodMenuWithNamePort;

@Component
public class CustomerOrderingJdbcDataProvider implements GetAllFoodMenuPort, 
														GetFoodMenuWithIdPort, 
														GetFoodMenuWithNamePort,
														CreateOrderPort{
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private JdbcTemplate jdbcTemplate;

	public CustomerOrderingJdbcDataProvider(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public FoodMenu getWithName(String name) {
		FoodMenu foodMenu = null;		
		foodMenu = jdbcTemplate.queryForObject(String.format("SELECT id,name,description,image_url FROM gqshop.food_menu WHERE name = '%s';",name), rowMapper);
		return foodMenu;
	}

	@Override
	public FoodMenu getWithId(String id) {
		FoodMenu foodMenu = jdbcTemplate.queryForObject(
				"SELECT id,name,description,image_url FROM gqshop.food_menu WHERE id = ?;", rowMapper, id.toString());
		return foodMenu;
	}

	@Override
	public Collection<FoodMenu> getAll() {
		return jdbcTemplate.query("SELECT id,name,description,image_url FROM gqshop.food_menu;", rowMapper);
	}

	@Override
	public int createOrder(List<Map<String, Object>> orders) {

		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
	        @Override
	        public PreparedStatement createPreparedStatement(Connection con) throws SQLException{
	 
	            PreparedStatement pstmt = con.prepareStatement(
	                    "INSERT INTO gqshop.orders(status) VALUES (?)",
						new String[]{"id"}
						// 두번째 파라미터에 자동생성되는 컬럼의 이름을 넣음.
	            );
	
	            pstmt.setString(1, "todo");
	            
	            return pstmt;
	        }
	    }, keyHolder); // update() 메소드의 두번째 파라미터로 keyHolder 전달
 
        int orderId = keyHolder.getKey().intValue();

        for(Map<String, Object> order:orders) {
			
			String menuId = (String)order.get("id");
			int amount = Integer.parseInt((String)order.get("amount"));

			jdbcTemplate.update(new PreparedStatementCreator() {
		        @Override
		        public PreparedStatement createPreparedStatement(Connection con) throws SQLException{
		 
		            PreparedStatement pstmt = con.prepareStatement(
		                    "INSERT INTO gqshop.orders_has_food_menu(orders_id, food_menu_id, amount) VALUES (?, ?, ?)",
							new String[]{"idx"}
		            );
		
		            pstmt.setInt(1, orderId);
		            pstmt.setString(2, menuId);
		            pstmt.setInt(3, amount);
		            
		            return pstmt;
		        }
		    }, keyHolder); // update() 메소드의 두번째 파라미터로 keyHolder 전달
		}

//        int ordersHasFoodMenuId = keyHolder.getKey().intValue();
//
//        int iCount = jdbcTemplate.queryForObject( "SELECT count(*) FROM gqshop.orders_has_food_menu", Integer.class);

        return orderId;
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
