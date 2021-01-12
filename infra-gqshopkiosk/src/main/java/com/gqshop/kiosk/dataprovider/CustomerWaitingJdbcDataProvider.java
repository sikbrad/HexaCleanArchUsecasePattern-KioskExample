package com.gqshop.kiosk.dataprovider;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.gqshop.kiosk.core.entity.Order;
import com.gqshop.kiosk.core.usecase.customer.waiting.GetCookedOrderPort;
import com.gqshop.kiosk.core.usecase.customer.waiting.GetNotCookedOrderPort;

@Component
public class CustomerWaitingJdbcDataProvider implements GetCookedOrderPort, GetNotCookedOrderPort {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private JdbcTemplate jdbcTemplate;
	
	public CustomerWaitingJdbcDataProvider(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Collection<Order> getOrderWithStatus(String status) {
		// TODO Auto-generated method stub
		
		String sql = "select * from gqshop.orders where status=?";
		
		Collection<Order> order = jdbcTemplate.queryForObject(sql, new OrderHasFoodMenuRowMapper(), status);
		
		return order;
	}
	
	private class OrderHasFoodMenuRowMapper implements RowMapper<Collection<Order>> {

		public Collection<Order> mapRow(ResultSet rs, int rowCnt) throws SQLException{
			 
			Collection<Order> orderList = new ArrayList<Order>();
			 
			while(rs.next()) {
				Order order = new Order(
						rs.getInt(1), 
						rs.getString(2), 
						rs.getTimestamp(3));
				orderList.add(order);
			}
	        return orderList;
	   }
	}

}
