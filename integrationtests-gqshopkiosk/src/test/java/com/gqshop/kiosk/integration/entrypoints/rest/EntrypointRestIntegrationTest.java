package com.gqshop.kiosk.integration.entrypoints.rest;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class EntrypointRestIntegrationTest implements CommandLineRunner {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	

	@Autowired
	private MockMvc mvc;

	public String apiUrl;

	@Autowired
	Environment env;

	@BeforeEach
	public void setUp() throws Exception {
		logger.info("called test setup");

		apiUrl = String.format("http://localhost:%s/api", env.getProperty("server.port"));
	}	

	@Test
	public void returnsApiInfo() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get(apiUrl).accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.name").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.version").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.version").isNotEmpty());
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("EntrypointRestIntegrationTest bean created");				
	}
}
