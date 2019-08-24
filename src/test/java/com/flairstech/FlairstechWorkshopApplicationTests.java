package com.flairstech;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.flairstech.dto.CountryRepresention;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class FlairstechWorkshopApplicationTests {

	@Autowired
	private TestRestTemplate testRestTemplate;
	
	
	
	@Test
	public void testSuccessfulFlowComponent() {
		String url = "/BHR";
		ResponseEntity<CountryRepresention> response = testRestTemplate.getForEntity(url, CountryRepresention.class);
		Assert.assertEquals("Arabic", response.getBody().getCountry_language());
		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());

	}

	@Test
	public void missingCountryCodeExceptionTest() {
		String url = "/12";		
		ResponseEntity<CountryRepresention> response = testRestTemplate.getForEntity(url, CountryRepresention.class);		
		
		Assert.assertEquals(500,response.getStatusCodeValue());
		
		Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
		
	}


}
