package com.cognizant.springlearn;

import com.cognizant.springlearn.model.Country;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * HANDS-ON 1: Spring Web Project with XML-based Bean Loading
 * 
 * This main class:
 * 1. Boots up Spring
 * 2. Loads country.xml configuration
 * 3. Retrieves the Country bean and prints it
 * 4. Retrieves the list of countries and prints them
 */
@SpringBootApplication
public class SpringLearnApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringLearnApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringLearnApplication.class, args);

        // Demonstrate loading Country bean from XML
        LOGGER.info("=== Loading Country from Spring Configuration XML ===");
        displayCountry();
        displayCountryList();
    }

    /**
     * Loads a single Country bean from country.xml and logs it.
     */
    private static void displayCountry() {
        LOGGER.info("Start displayCountry()");
        ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
        Country country = (Country) context.getBean("country");
        LOGGER.debug("Country: {}", country);
        LOGGER.info("End displayCountry()");
    }

    /**
     * Loads the list of Country beans from country.xml and logs each one.
     */
    @SuppressWarnings("unchecked")
    private static void displayCountryList() {
        LOGGER.info("Start displayCountryList()");
        ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
        List<Country> countryList = (List<Country>) context.getBean("countryList");
        for (Country country : countryList) {
            LOGGER.debug("Country: {}", country);
        }
        LOGGER.info("End displayCountryList()");
    }
}
