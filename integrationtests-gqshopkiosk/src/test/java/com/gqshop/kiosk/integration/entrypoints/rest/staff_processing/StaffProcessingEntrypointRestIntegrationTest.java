package com.gqshop.kiosk.integration.entrypoints.rest.staff_processing;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Test;
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class StaffProcessingEntrypointRestIntegrationTest {

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
	public void whenGetOrders_thenReturnsOrders() throws Exception {

		final String finalUrlFormat = String.format("%s/order/all",apiUrl);
		
		mvc.perform(MockMvcRequestBuilders.get(finalUrlFormat)).andDo(print())
				.andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(7)));
	}

	@Test
	public void whenGetOrdersWithOrderId_thenReturnsOrder() throws Exception{
		
		final int orderId = 1;
		
		final String finalUrlFormat = String.format("%s/order/one/%d",apiUrl,orderId);

		//when
		final ResultActions actions = mvc.perform(
				MockMvcRequestBuilders.get(finalUrlFormat))
				.andDo(print());
		
		//then
		actions
	        .andExpect(status().isOk())
	        .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
	        .andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.is(orderId)))
	        .andExpect(MockMvcResultMatchers.jsonPath("$.status", CoreMatchers.is("done")));

	}
	
	@Test
	public void whenGetOrdersWithWrongOrderId_thenReturnsNothing() throws Exception{
		
		final int orderId = 320;
		
		final String finalUrlFormat = String.format("%s/order/one/%d",apiUrl,orderId);

		//when
		final ResultActions actions = mvc.perform(
				MockMvcRequestBuilders.get(finalUrlFormat))
				.andDo(print());
		
		//then
		actions
	        .andExpect(status().isOk());
		
		String strRes = actions.andReturn().getResponse().getContentAsString();
	
		assertEquals(strRes,"");

	}

}
