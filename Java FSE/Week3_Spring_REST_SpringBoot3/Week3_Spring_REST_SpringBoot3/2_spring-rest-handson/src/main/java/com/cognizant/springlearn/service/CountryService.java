package com.cognizant.springlearn.service;

import com.cognizant.springlearn.model.Country;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * CountryService loads Country beans from Spring XML configuration (country.xml)
 * and provides operations on them.
 */
@Service
public class CountryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryService.class);

    /**
     * Returns a single Country bean from XML.
     */
    public Country getCountry() {
        LOGGER.info("Start getCountry()");
        ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
        Country country = (Country) context.getBean("country");
        LOGGER.info("End getCountry()");
        return country;
    }

    /**
     * Returns list of all Country beans from XML.
     */
    @SuppressWarnings("unchecked")
    public List<Country> getAllCountries() {
        LOGGER.info("Start getAllCountries()");
        ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
        List<Country> countries = (List<Country>) context.getBean("countryList");
        LOGGER.info("End getAllCountries()");
        return countries;
    }

    /**
     * HANDS-ON: Get Country by matching code (case-insensitive).
     * Throws IllegalArgumentException if not found.
     */
    public Country getCountryByCode(String code) {
        LOGGER.info("Start getCountryByCode() with code: {}", code);
        List<Country> countries = getAllCountries();
        Country result = countries.stream()
                .filter(c -> c.getCode().equalsIgnoreCase(code))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "Country not found with code: " + code));
        LOGGER.info("End getCountryByCode()");
        return result;
    }
}
