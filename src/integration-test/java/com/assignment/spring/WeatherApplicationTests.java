package com.assignment.spring;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest
public class WeatherApplicationTests {

	@Value("classpath:mock/weather.json")
	private Resource mockWeather;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private MockMvc mvc;

	private MockRestServiceServer mockServer;

	@Before
	public void setup() {
		mockServer = MockRestServiceServer.createServer(restTemplate);
	}

	@Test
	public void getWeatherReportTest() throws Exception {

		mockServer.expect(ExpectedCount.once(),
				requestTo(new URI("http://testURL/data/2.5/weather?q=London&APPID=testAppId")))
				.andExpect(method(HttpMethod.GET))
				.andRespond(withStatus(HttpStatus.OK)
						.contentType(MediaType.APPLICATION_JSON)
						.body(mockWeather)
				);

		mvc.perform(get("/weather?city={id}", "London"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").exists())
				.andExpect(jsonPath("$.city").value("London"))
				.andExpect(jsonPath("$.country").value("GB"))
				.andExpect(jsonPath("$.temperature").value(292.64d));

		mockServer.verify();

	}

	@Test
	public void getErrorWhenCityNotExistTest() throws Exception {

		mockServer.expect(ExpectedCount.once(),
				requestTo(new URI("http://testURL/data/2.5/weather?q=London&APPID=testAppId")))
				.andExpect(method(HttpMethod.GET))
				.andRespond(withStatus(HttpStatus.NOT_FOUND)
				);

		mvc.perform(get("/weather?city={id}", "London"))
				.andExpect(status().isNotFound());

		mockServer.verify();

	}

}
