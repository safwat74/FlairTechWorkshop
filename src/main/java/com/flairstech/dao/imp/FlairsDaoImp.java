package com.flairstech.dao.imp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.flairstech.Exceptions.DownConnectionException;
import com.flairstech.Exceptions.InvalidCountryCodeException;
import com.flairstech.dao.FlairsDao;
import com.flairstech.dto.CountryRepresention;

@Component
public class FlairsDaoImp implements FlairsDao {

	private static final Logger logger = LoggerFactory.getLogger(FlairsDaoImp.class);

	@Value("${flairs.url}")
	private String url;

	@Value("${flairs.username}")
	private String username;

	@Value("${flairs.password}")
	private String password;

	@Override
	public CountryRepresention getCountryDetails(String code) {
		CountryRepresention countryRepresention = new CountryRepresention();
		logger.info("Trying to connect to the DataBase");
		try (Connection connection = DriverManager.getConnection(url, username, password)) {

			logger.info("Connected to PostgreSQL database!");

			String sql = "select country_language.language, country.code,country.name ,"
					+ "country.continent ,country.Life_expectancy ,country.population "
					+ "from country inner join country_language on country.code = country_language.country_code "
					+ "where code=? and country_language.is_official=true ;";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, code);
			ResultSet resultSet = stmt.executeQuery();

			if (!resultSet.next()) {
				logger.error("There is no rows fitched ");
				throw new InvalidCountryCodeException("INVALID_COUNTRY_CODE");
			} else {
				do {
					countryRepresention.setName(resultSet.getString("name"));
					countryRepresention.setContinent(resultSet.getString("continent"));
					countryRepresention.setPopulation(Integer.valueOf(resultSet.getString("population")));
					countryRepresention.setLife_expectancy(Double.valueOf(resultSet.getString("life_expectancy")));
					countryRepresention.setCountry_language(resultSet.getString("language"));
				} while (resultSet.next());
			}

		} catch (SQLException e) {
			logger.error("There is an Error when trying to connect to DB with message : " + e.getMessage());
			throw new DownConnectionException("INTERNAL_ERROR");
		} catch (InvalidCountryCodeException e) {
			logger.error("There is an Exception with message : " + e.getMessage());
			throw e;
		}
		return countryRepresention;
	}

}
