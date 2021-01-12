package com.gqshop.kiosk.dataprovider;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.gqshop.kiosk.core.entity.FoodMenu;
import com.gqshop.kiosk.core.entity.Order;
import com.gqshop.kiosk.core.usecase.staff.processing.GetOrderWithIdPort;
import com.gqshop.kiosk.core.usecase.staff.processing.GetReceivedOrdersPort;
import com.gqshop.kiosk.core.usecase.staff.processing.MarkOrderAsCookingDonePort;
import com.gqshop.kiosk.core.usecase.staff.processing.MarkOrderAsCookingStartedPort;
import com.gqshop.kiosk.core.usecase.staff.processing.MarkOrderAsTakenPort;
import com.gqshop.kiosk.core.usecase.staff.processing.VerifyExistOrderIdPort;
import com.gqshop.kiosk.entrypoint.model.OrderHasMenu;

@Component
public class StaffCheckingJdbcDataProvider implements GetReceivedOrdersPort, 
													GetOrderWithIdPort,
													MarkOrderAsCookingStartedPort, 
													MarkOrderAsCookingDonePort, 
													MarkOrderAsTakenPort,
													VerifyExistOrderIdPort{
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private JdbcTemplate jdbcTemplate;

	public StaffCheckingJdbcDataProvider(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Collection<Order> getAll(){
		String sql = "SELECT id as orderId, status as status, created_at as createdAt FROM gqshop.orders;";
		
		Collection<Order> orderList = new ArrayList<Order>();
		
		List<Map<String, Object>> orders = jdbcTemplate.queryForList(sql);

		for(Map<String, Object> orderMap : orders) {
			
			Order order = new Order((int)orderMap.get("orderId"), (String)orderMap.get("status"), (Timestamp)orderMap.get("createdAt"));
			int orderId = order.getId();
			String sql2 =  "select m.orders_id as orderId, m.amount as amount, f.id as menuId, f.name as menuName from gqshop.orders_has_food_menu as m\r\n" + 
					  "join gqshop.food_menu as f on f.id=m.food_menu_id\r\n" + 
					  "where m.orders_id=?;";

			List<Map<String, Object>> orderHasMenus = jdbcTemplate.queryForList(sql2,  new Object[] {orderId});

			List<FoodMenu> foodMenus = new ArrayList<FoodMenu>();
			for(Map<String, Object> row : orderHasMenus) {
				String menuId = (String)row.get("menuId");
				System.out.println("menuId : " + menuId);
				FoodMenu foodMenu = jdbcTemplate.queryForObject(
						"SELECT id,name,description,image_url FROM gqshop.food_menu WHERE id = ?;", rowFoodMenuMapper, menuId);
				
				foodMenus.add(foodMenu);
			}
			
			order.setMenus(foodMenus);
			
			orderList.add(order);
		}
		return orderList;
	}
	
	@Override
	public int updateOrderStatusWithId(int id, String status) {
		int nRes = jdbcTemplate.update("update gqshop.orders set status=? where id=?",
				status, id);
		return nRes;
	}
	
	@Override
	public boolean bExistOrderId(int id) {
		int iCount = jdbcTemplate.queryForObject( "SELECT count(*) FROM gqshop.orders WHERE id = ?", Integer.class , id);
		return iCount > 0;
	}

	@Override
	public Order getWithId(int id) {

		String sql = "select id as orderId, status as status, created_at as createdAt from gqshop.orders where id=?";
		
		List<Map<String, Object>> orders = jdbcTemplate.queryForList(sql, new Object[] {id});
		Map<String, Object> orderMap = orders.get(0);

		Order order = new Order((int)orderMap.get("orderId"), (String)orderMap.get("status"), (Timestamp)orderMap.get("createdAt"));
		
		String sql2 =  "select m.orders_id as orderId, m.amount as amount, f.id as menuId, f.name as menuName from gqshop.orders_has_food_menu as m\r\n" + 
				  "join gqshop.food_menu as f on f.id=m.food_menu_id\r\n" + 
				  "where m.orders_id=?;";

		List<Map<String, Object>> orderHasMenus = jdbcTemplate.queryForList(sql2,  new Object[] {id});

		List<FoodMenu> foodMenus = new ArrayList<FoodMenu>();
		for(Map<String, Object> row : orderHasMenus) {
			String menuId = (String)row.get("menuId");
			System.out.println("menuId : " + menuId);
			FoodMenu foodMenu = jdbcTemplate.queryForObject(
					"SELECT id,name,description,image_url FROM gqshop.food_menu WHERE id = ?;", rowFoodMenuMapper, menuId);
			
			foodMenus.add(foodMenu);
		}
		
		order.setMenus(foodMenus);
		
		return order;

	}

	private RowMapper<FoodMenu> rowFoodMenuMapper = (ResultSet rs, int rowNum) -> {

		UUID id = UUID.fromString(rs.getString(1));
		String name = rs.getString(2);
		String description = rs.getString(3);
		String imageUrl = rs.getString(4);

		FoodMenu foodMenu = new FoodMenu(id, name, description, imageUrl);
		return foodMenu;

	};
	
}
