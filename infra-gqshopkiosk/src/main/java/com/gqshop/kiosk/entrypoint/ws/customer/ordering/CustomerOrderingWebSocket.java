package com.gqshop.kiosk.entrypoint.ws.customer.ordering;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gqshop.kiosk.core.entity.FoodMenu;
import com.gqshop.kiosk.core.entity.Order;
import com.gqshop.kiosk.core.usecase.customer.ordering.CustomerOrderingUsecase;
import com.gqshop.kiosk.core.usecase.staff.processing.StaffProcessingUsecase;
import com.gqshop.kiosk.entrypoint.model.MessageVO;
import com.gqshop.kiosk.entrypoint.model.OrderHasMenu;
import com.gqshop.kiosk.entrypoint.model.OrdersDto;

@RestController
@RequestMapping("/app")
public class CustomerOrderingWebSocket implements CommandLineRunner {
	
	ObjectMapper mapper = new ObjectMapper(); 
	
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CustomerOrderingUsecase customerOrderingUsecase;
	
	@Autowired
	StaffProcessingUsecase staffProcessingUsecase;
	
	@Override
	public void run(String... args) throws Exception {
		logger.info("CustomerOrderingWebSocket bean created");
	}

	@MessageMapping("/order")		
	@SendTo("/topic/reflection")
	public MessageVO postorder(@RequestBody Map<String, Object> requestBody) throws Exception{
		
		List<Map<String, Object>> orders = mapper.convertValue(requestBody.get("order"), new TypeReference<List<Map<String, Object>>>(){});

		int orderId = customerOrderingUsecase.createOrder(orders);
		System.out.println("created orderId is " + orderId);

		OrdersDto dto = toOrdersDto(staffProcessingUsecase.getWithId(orderId));
		
		MessageVO msgVO = new MessageVO("success", dto.getId(), dto.getStatus()); 
		
		return msgVO;
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
