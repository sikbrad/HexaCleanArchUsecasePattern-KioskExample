package com.gqshop.kiosk.integration.entrypoints.rest.customer_ordering;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
public class CustomerOrderingEntrypointRestIntegrationTest {

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
	public void whenGetFoodMenus_thenReturnsMenus() throws Exception {
		final String[] foodMenuNames = new String[] { "Big Mac", "Cheeseburger", "Coca-Cola"};
		mvc.perform(MockMvcRequestBuilders.get(String.format("%s/foodmenu", apiUrl))).andDo(print())
				.andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)))
				.andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].name", CoreMatchers.is(foodMenuNames[0])))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].name", CoreMatchers.is(foodMenuNames[1])))
				.andExpect(MockMvcResultMatchers.jsonPath("$[2].name", CoreMatchers.is(foodMenuNames[2])));
	}
	
	@Test
	public void whenGetFoodMenusWithName_thenReturnsFoodMenu() throws Exception {
		final String foodMenuName = "Big Mac";
		final String foodMenuDetail = "Big Mac signature burger";
		
		mvc.perform(MockMvcRequestBuilders.get(String.format("%s/foodmenu/%s", apiUrl, foodMenuName))).andDo(print())
		.andExpect(status().isOk())
//				.andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
				.andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(foodMenuName)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.description", CoreMatchers.is(foodMenuDetail)));
	}
	
	@Test
	public void whenGetFoodMenusWithWrongName_thenReturnsNothing() throws Exception {
		final String wrongFoodMenuName = "BurgerKingBurger";		
		
		mvc.perform(MockMvcRequestBuilders.get(String.format("%s/foodmenu/%s", apiUrl, wrongFoodMenuName))).andDo(print())
		.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(wrongFoodMenuName)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.description", CoreMatchers.is(wrongFoodMenuName)));
	}
}
