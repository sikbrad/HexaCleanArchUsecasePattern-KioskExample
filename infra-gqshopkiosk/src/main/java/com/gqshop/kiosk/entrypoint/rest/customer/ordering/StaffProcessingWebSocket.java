package com.gqshop.kiosk.entrypoint.rest.customer.ordering;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gqshop.kiosk.core.usecase.staff.processing.StaffProcessingUsecase;

@RestController
@RequestMapping("/app")
public class StaffProcessingWebSocket  implements CommandLineRunner {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	StaffProcessingUsecase staffProcessingUsecase;

	@Override
	public void run(String... args) throws Exception {
		logger.info("StaffProcessingWebSocket bean created");
	}
	
	@MessageMapping("/welcome")
	@SendTo("/topic/greetings")
	public String greeting(String payload) {
		System.out.println("Generating new greeting message for " + payload);
		return "Hello, " + payload + "!";
	}

//	@SubscribeMapping("/chat")
//	  public MessageVO sendWelcomeMessageOnSubscription() {
//		MessageVO welcomeMessage = new MessageVO("Hello World!", 0, "");
//	    return welcomeMessage;
//	}
	 
	@MessageMapping("/order/status")		
	@SendTo("/topic/reflection")			
	public MessageVO broadcasting(MessageVO msg) throws Exception{

		int nRes = -1;

		String status = msg.getStatus();
		int id = msg.getId();
		
		if(status.equals("doing")) {
			nRes = staffProcessingUsecase.markOrderAsCookingStarted(id, status);
		}
	
		if(status.equals("done")) {
			nRes = staffProcessingUsecase.markOrderAsCookingDone(id, status);
		}
		
		if(status.equals("end")) {
			nRes = staffProcessingUsecase.markOrderAsCookingStarted(id, status);
		}

		System.out.println("nRes : " + nRes);
		
		String result = "";
		if(nRes > 0) {
			result = "success";
		}else {
			result = "fail";
		}
		
		MessageVO test = new MessageVO(result, msg.getId(), msg.getStatus()); 
		
		return test;
	}
}
