package com.gqshop.kiosk.entrypoint.web.customer.ordering;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.gqshop.kiosk.core.usecase.customer.ordering.CustomerOrderingUsecase;
import com.gqshop.kiosk.core.usecase.staff.processing.StaffProcessingUsecase;

@Controller
public class StaffProcessingEntrypointWeb {

	@Autowired
	Environment env;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	StaffProcessingUsecase staffProcessingUsecase;
	
	@Autowired
	CustomerOrderingUsecase customerOrderingUsecase;
	
	@GetMapping("/staff")
	public String staff() {
		return "staff";
	}
	
}
