package com.cognizant.springlearn.controller;

import com.cognizant.springlearn.dto.CountryDTO;
import com.cognizant.springlearn.model.Country;
import com.cognizant.springlearn.service.CountryService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * FULL CRUD REST Controller with:
 * - Path Variables & Query Parameters
 * - Request Body validation (@Valid)
 * - DTOs
 * - HATEOAS links
 * - Proper HTTP status codes
 * - Content negotiation (JSON/XML)
 */
@RestController
@RequestMapping("/api/countries")
public class CountryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryController.class);

    @Autowired
    private CountryService countryService;

    /**
     * GET /api/countries -> Get all countries
     */
    @GetMapping
    public ResponseEntity<List<Country>> getAll() {
        LOGGER.info("Getting all countries");
        return ResponseEntity.ok(countryService.getAllCountries());
    }

    /**
     * GET /api/countries/{code} -> Get one country + HATEOAS links
     */
    @GetMapping("/{code}")
    public EntityModel<Country> getByCode(@PathVariable String code) {
        LOGGER.info("Getting country with code: {}", code);
        Country country = countryService.getCountryByCode(code);

        // HATEOAS - add navigable links to the response
        Link selfLink = linkTo(methodOn(CountryController.class).getByCode(code)).withSelfRel();
        Link allLink = linkTo(methodOn(CountryController.class).getAll()).withRel("all-countries");
        return EntityModel.of(country, selfLink, allLink);
    }

    /**
     * GET /api/countries/dto -> Get all as DTOs
     */
    @GetMapping("/dto")
    public ResponseEntity<List<CountryDTO>> getAllAsDTO() {
        return ResponseEntity.ok(countryService.getAllCountriesAsDTO());
    }

    /**
     * GET /api/countries/search?name=xxx -> Query parameter demo
     */
    @GetMapping("/search")
    public ResponseEntity<List<Country>> search(@RequestParam("name") String name) {
        List<Country> results = countryService.getAllCountries().stream()
                .filter(c -> c.getName().toLowerCase().contains(name.toLowerCase()))
                .toList();
        return ResponseEntity.ok(results);
    }

    /**
     * POST /api/countries -> Create new country with validation
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Country create(@Valid @RequestBody Country country) {
        LOGGER.info("Creating country: {}", country);
        return countryService.addCountry(country);
    }

    /**
     * PUT /api/countries/{code} -> Update country
     */
    @PutMapping("/{code}")
    public Country update(@PathVariable String code, @Valid @RequestBody Country country) {
        LOGGER.info("Updating country with code: {}", code);
        return countryService.updateCountry(code, country);
    }

    /**
     * DELETE /api/countries/{code} -> Delete country
     */
    @DeleteMapping("/{code}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String code) {
        LOGGER.info("Deleting country with code: {}", code);
        countryService.deleteCountry(code);
    }
}
