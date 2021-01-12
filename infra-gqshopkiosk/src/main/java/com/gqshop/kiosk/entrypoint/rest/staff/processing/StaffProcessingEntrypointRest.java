package com.gqshop.kiosk.entrypoint.rest.staff.processing;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gqshop.kiosk.core.entity.FoodMenu;
import com.gqshop.kiosk.core.entity.Order;
import com.gqshop.kiosk.core.usecase.staff.processing.StaffProcessingUsecase;
import com.gqshop.kiosk.entrypoint.model.OrderHasMenu;
import com.gqshop.kiosk.entrypoint.model.OrdersDto;

@RestController
@RequestMapping("/api")
public class StaffProcessingEntrypointRest  implements CommandLineRunner {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	StaffProcessingUsecase staffProcessingUsecase;

	@Override
	public void run(String... args) throws Exception {
		logger.info("StaffCheckingEntrypointRest bean created");
	}
	
	@GetMapping(value = "/order/all")
	public Collection<OrdersDto> getOrdersList(){

		Collection<Order> allOrders = staffProcessingUsecase.getReceivedOrdersAll();
		System.out.println("allOrders size : " + allOrders.size());
		return toOrderCollectionDto(allOrders);
	}
	
	@GetMapping(value = "/order/one/id/{id}")
	public OrdersDto getOrder(@PathVariable(value = "id") int id) {
		
		boolean bExistOrderId = staffProcessingUsecase.bExistOrderId(id);
		
		OrdersDto dto = null;
		if(bExistOrderId) {
			dto = toOrdersDto(staffProcessingUsecase.getWithId(id));
		}
		return dto;
	}

	private Collection<OrdersDto> toOrderCollectionDto(Collection<Order> allOrders){
		return allOrders.stream().map(x -> toOrdersDto(x)).collect(Collectors.toList());
	}
	
	private OrdersDto toOrdersDto(Order order) {
		if(order == null) {
			return null;
		}

		List<OrderHasMenu> orderHasMenus = new ArrayList<OrderHasMenu>();
		
		List<FoodMenu> menus = order.getMenus();
		
		int orderId = order.getId();
		for(FoodMenu menu : menus) {
			String menuId = menu.getId().toString();
			String menuName = menu.getName();

			
			OrderHasMenu orderHasMenu = new OrderHasMenu(orderId, menuId, order.getMenus().size(), menuName);
			orderHasMenus.add(orderHasMenu);
		}

		return new OrdersDto(order.getId(), order.getStatus(), order.getCreatedAt().toString(), orderHasMenus);
	}
	
}
