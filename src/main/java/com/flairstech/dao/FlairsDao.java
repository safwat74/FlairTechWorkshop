package com.flairstech.dao;


import com.flairstech.dto.CountryRepresention;

public interface FlairsDao {
	
	CountryRepresention getCountryDetails(String code);

}
