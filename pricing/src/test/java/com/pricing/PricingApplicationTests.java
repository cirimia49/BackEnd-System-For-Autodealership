package com.pricing;

import com.pricing.domain.Price;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URI;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class PricingApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private JacksonTester<Price> jacksonTester;

	@Test
	void contextLoads() {
	}

	/**
	 * Now we will test the prices method to see if we get back a list of prices
	 * Also it takes care of handling exceptions
	 *
	 * Goes in the first JSON object and checks if the returned
	 * values are the same as expected
	 */

	@Test
	public void listPrices() throws Exception{

		mockMvc.perform(
				get(new URI("/prices"))
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("_embedded.prices", hasSize(5)))
				.andExpect(jsonPath("_embedded.prices[0].currency", is("USD")))
				.andExpect(jsonPath("_embedded.prices[0].price", is(30000.00)))
				.andExpect(jsonPath("_embedded.prices[0].vehicleId", is(1)));
	}

	/**
	 * Tests the seconf operation just by reading one price in the
	 * values. Also it throws an exception if it fails
	 */

	@Test
	public void findPrice() throws Exception{

		mockMvc.perform(
				get(new URI("/prices/2"))
				.accept(MediaType.APPLICATION_JSON) )
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("currency", is("USD")))
				.andExpect(jsonPath("price", is(25000.00)))
				.andExpect(jsonPath("vehicleId", is(2)));


	}

}
