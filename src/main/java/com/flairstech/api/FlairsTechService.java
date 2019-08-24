package com.flairstech.api;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.flairstech.Exceptions.InvalidCountryCodeException;
import com.flairstech.dao.FlairsDao;
import com.flairstech.dto.CountryRepresention;



@RestController
public class FlairsTechService {

	private static final Logger logger = LoggerFactory.getLogger(FlairsTechService.class);
	@Autowired
	FlairsDao flairsDao;
	
	@RequestMapping(value = "/{code}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity<CountryRepresention> getCountryDetails(@PathVariable String code,HttpServletRequest httpRequest){
		
		logger.info("The Country Code is : "+ code);
		CountryRepresention countryRepresention=flairsDao.getCountryDetails(code);
		return new ResponseEntity<CountryRepresention>(countryRepresention, HttpStatus.OK);
		
	}
	
}
