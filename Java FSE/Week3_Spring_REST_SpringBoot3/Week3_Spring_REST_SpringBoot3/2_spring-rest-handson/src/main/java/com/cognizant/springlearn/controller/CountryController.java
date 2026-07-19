package com.cognizant.springlearn.controller;

import com.cognizant.springlearn.model.Country;
import com.cognizant.springlearn.service.CountryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * HANDS-ON 2: REST - Country Web Service
 * HANDS-ON 3: REST - Get country based on country code
 * 
 * Endpoints:
 *   GET /country       -> Returns a single country (India by default)
 *   GET /countries     -> Returns list of all countries
 *   GET /country/{code} -> Returns country matching the code (e.g. /country/IN)
 */
@RestController
public class CountryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryController.class);

    @Autowired
    private CountryService countryService;

    /**
     * Returns a single Country loaded from country.xml
     * URL: http://localhost:8080/country
     */
    @GetMapping("/country")
    public Country getCountry() {
        LOGGER.info("Start getCountry()");
        Country country = countryService.getCountry();
        LOGGER.debug("Country: {}", country);
        LOGGER.info("End getCountry()");
        return country;
    }

    /**
     * Returns all countries loaded from country.xml
     * URL: http://localhost:8080/countries
     */
    @GetMapping("/countries")
    public List<Country> getAllCountries() {
        LOGGER.info("Start getAllCountries()");
        List<Country> countries = countryService.getAllCountries();
        LOGGER.debug("Countries count: {}", countries.size());
        LOGGER.info("End getAllCountries()");
        return countries;
    }

    /**
     * HANDS-ON: Get country based on country code
     * URL: http://localhost:8080/country/IN
     */
    @GetMapping("/country/{code}")
    public Country getCountryByCode(@PathVariable("code") String code) {
        LOGGER.info("Start getCountryByCode() with code: {}", code);
        Country country = countryService.getCountryByCode(code);
        LOGGER.debug("Country found: {}", country);
        LOGGER.info("End getCountryByCode()");
        return country;
    }
}
