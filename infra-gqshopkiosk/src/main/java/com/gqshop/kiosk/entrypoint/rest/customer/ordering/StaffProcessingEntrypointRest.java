package com.gqshop.kiosk.entrypoint.rest.customer.ordering;

import java.util.Collection;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gqshop.kiosk.core.entity.Orders;
import com.gqshop.kiosk.core.usecase.customer.ordering.CustomerOrderingUsecase;
import com.gqshop.kiosk.core.usecase.staff.processing.StaffProcessingUsecase;

@RestController
@RequestMapping("/api")
public class StaffProcessingEntrypointRest  implements CommandLineRunner {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	StaffProcessingUsecase staffProcessingUsecase;
	
	@Autowired
	CustomerOrderingUsecase customerOrderingUsecase;

	@Override
	public void run(String... args) throws Exception {
		logger.info("StaffCheckingEntrypointRest bean created");
	}
	
	@GetMapping(value = "/order/all")
	public Collection<OrdersDto> getOrdersList(){

		Collection<Orders> allOrders = staffProcessingUsecase.getReceivedOrdersAll();
		
		return toOrderCollectionDto(allOrders);
	}
	
	@GetMapping(value = "/order/one/{id}")
	public OrdersDto getOrder(@PathVariable(value = "id") int id) {
		
		boolean bExistOrderId = staffProcessingUsecase.bExistOrderId(id);
		
		OrdersDto dto = null;
		if(bExistOrderId) {
			dto = toOrdersDto(customerOrderingUsecase.getWithId(id));
		}
		return dto;
	}

	private Collection<OrdersDto> toOrderCollectionDto(Collection<Orders> allOrders){
		return allOrders.stream().map(x -> toOrdersDto(x)).collect(Collectors.toList());
	}
	
	private OrdersDto toOrdersDto(Orders order) {
		if(order == null) {
			return null;
		}

		return new OrdersDto(order.getId(), order.getStatus(), order.getCreatedAt(), order.getMenus());
	}
	
}
