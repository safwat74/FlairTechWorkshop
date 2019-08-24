package com.flairstech;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.flairstech.Exceptions.InvalidCountryCodeException;
import com.flairstech.dao.FlairsDao;
import com.flairstech.dto.CountryRepresention;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class FlairsDaoTest {

	@Autowired
	private FlairsDao flairsDao;
		


	@Test(expected=InvalidCountryCodeException.class)
	public void testSuccessfulFlowComponent() {
		
		CountryRepresention response = flairsDao.getCountryDetails("12");
		
		
	}


}
