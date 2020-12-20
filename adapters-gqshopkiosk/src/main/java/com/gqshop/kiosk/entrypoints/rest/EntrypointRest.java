package com.gqshop.kiosk.entrypoints.rest;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EntrypointRest  implements CommandLineRunner {

	@Autowired
	Environment env;

	Logger logger = LoggerFactory.getLogger(this.getClass());

	
	@GetMapping
	public String home() {
		JSONObject jo = new JSONObject();
		
		jo.put("name", "gqshop APIs");
		jo.put("version", "1.0.0");		
		String currProfiles = String.join(";", env.getActiveProfiles());
		if(currProfiles.length() == 0) {
			currProfiles = "(profile undecided - it is set as default)";
		}				
		jo.put("profile", currProfiles);
		
		
		logger.debug(String.format("/api returns : %s", jo.toString()));
		logger.info(String.format("/api returns : %s", jo.toString()));
		
		return jo.toString();
	}
	@Override
	public void run(String... args) throws Exception {
		logger.info("EntrypointRest bean created");
	}
}
