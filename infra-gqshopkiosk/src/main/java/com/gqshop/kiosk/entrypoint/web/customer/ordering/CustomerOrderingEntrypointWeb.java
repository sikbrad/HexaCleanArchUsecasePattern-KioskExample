package com.gqshop.kiosk.entrypoint.web.customer.ordering;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gqshop.kiosk.core.entity.FoodMenu;
import com.gqshop.kiosk.core.usecase.customer.ordering.CustomerOrderingUsecase;

@Controller
public class CustomerOrderingEntrypointWeb implements CommandLineRunner{

	@Autowired
	Environment env;

	@Autowired
	CustomerOrderingUsecase customerOrderingUsecase;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping("/")
	public String homePage(Model model){
		String currProfiles = String.join(",", env.getActiveProfiles());
		if(currProfiles.length() == 0) {
			currProfiles = "(profile undecided - it is set as default)";
		}		
		model.addAttribute("profile", currProfiles);
		return "home";
	}

	@GetMapping("/customer")
	public String customer_view(Model model) {		
		logger.info("entered customer_view web");
		return "customer_view";
	}	
	
	@GetMapping("/customer/food_menu")
	public String food_menu(Model model) {		
		return "food_menu";
	}

	@GetMapping("/customer/food_menu/{foodname}")
	public String index(@PathVariable(required=true,name="foodname") String foodname, Model model) {
		try {
			foodname = URLDecoder.decode(foodname, "ASCII");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //decode to utf8(space and etc)
		
		FoodMenu foodMenu = customerOrderingUsecase.getWithName(foodname);
		if(foodMenu != null) {
			System.out.println(String.format("foodname : [%s]", foodname));
			model.addAttribute("foodname", foodname);
			System.out.println(String.format("foodname1 : [%s]", foodname));
			
			model.addAttribute("foodMenuUuid", foodMenu.getId().toString());
			System.out.println(String.format("foodname2 : [%s]", foodname));	
		}
		
		return "food_menu_detail";
	}
	
	@GetMapping("/customer/wait_board")
	public String wait_board() {
		return "wait_board";
	}

	@GetMapping("/customer/cart")
	public String mycart() {		
		return "cart";
	}
	
	@Override
	public void run(String... args) throws Exception {
		logger.info("CustomerOrderingEntrypointWeb bean created");
		
	}
}
