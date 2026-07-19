package com.cognizant.springlearn.service;

import com.cognizant.springlearn.dto.CountryDTO;
import com.cognizant.springlearn.exception.CountryNotFoundException;
import com.cognizant.springlearn.model.Country;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * CountryService - in-memory implementation with full CRUD operations.
 */
@Service
public class CountryService {

    private final Map<String, Country> countries = new HashMap<>();

    @PostConstruct
    public void init() {
        // Preload some sample data
        addCountry(new Country("IN", "India"));
        addCountry(new Country("US", "United States of America"));
        addCountry(new Country("UK", "United Kingdom"));
        addCountry(new Country("JA", "Japan"));
        addCountry(new Country("AU", "Australia"));
    }

    public List<Country> getAllCountries() {
        return new ArrayList<>(countries.values());
    }

    public Country getCountryByCode(String code) {
        Country country = countries.get(code.toUpperCase());
        if (country == null) {
            throw new CountryNotFoundException("Country not found with code: " + code);
        }
        return country;
    }

    public Country addCountry(Country country) {
        countries.put(country.getCode().toUpperCase(), country);
        return country;
    }

    public Country updateCountry(String code, Country updated) {
        if (!countries.containsKey(code.toUpperCase())) {
            throw new CountryNotFoundException("Cannot update. Country not found: " + code);
        }
        updated.setCode(code.toUpperCase());
        countries.put(code.toUpperCase(), updated);
        return updated;
    }

    public void deleteCountry(String code) {
        if (!countries.containsKey(code.toUpperCase())) {
            throw new CountryNotFoundException("Cannot delete. Country not found: " + code);
        }
        countries.remove(code.toUpperCase());
    }

    /**
     * Returns list of CountryDTO - demonstrates entity-to-DTO mapping.
     */
    public List<CountryDTO> getAllCountriesAsDTO() {
        return countries.values().stream()
                .map(c -> new CountryDTO(c.getCode(), c.getName()))
                .collect(Collectors.toList());
    }
}
