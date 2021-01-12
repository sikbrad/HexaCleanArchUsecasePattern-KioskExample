package com.gqshop.kiosk.dataprovider;

import java.sql.ResultSet;
import java.sql.SQLException;
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

import com.gqshop.kiosk.core.entity.Orders;
import com.gqshop.kiosk.core.usecase.staff.processing.GetReceivedOrdersPort;
import com.gqshop.kiosk.core.usecase.staff.processing.MarkOrderAsCookingDonePort;
import com.gqshop.kiosk.core.usecase.staff.processing.MarkOrderAsCookingStartedPort;
import com.gqshop.kiosk.core.usecase.staff.processing.MarkOrderAsTakenPort;
import com.gqshop.kiosk.core.usecase.staff.processing.VerifyExistOrderIdPort;

@Component
public class StaffCheckingJdbcDataProvider implements GetReceivedOrdersPort, 
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
	public Collection<Orders> getAll(){
		return jdbcTemplate.query("SELECT * FROM gqshop.orders;", rowOrdersMapper);
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
	
	private RowMapper<Orders> rowOrdersMapper = (ResultSet rs, int rowNum) -> {

		int orderId = rs.getInt(1);
		String status = rs.getString(2);
		String createdAt = rs.getString(3);

//		List<Map<String, Object>> orderHasMenus  = jdbcTemplate.queryForObject(
//		  "select m.orders_id, m.amount, f.id, f.name from gqshop.orders_has_food_menu as m\r\n" + 
//		  "join gqshop.food_menu as f on f.id=m.food_menu_id\r\n" + 
//		  "where m.orders_id=?",
//		  new OrderHasFoodMenuRowMapper(),
//		  orderId);

		Orders order = new Orders(orderId, status, createdAt);

		return order;
	};
}
