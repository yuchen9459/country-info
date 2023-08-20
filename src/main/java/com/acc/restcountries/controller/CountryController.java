package com.acc.restcountries.controller;

import com.acc.restcountries.entity.Country;
import com.acc.restcountries.service.CountryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@Tag(name = "Country information API")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/get-countries-sorted-by-population-density")
    public List<Country> getCountryListSortedByPopulationDensity() {
        return countryService.getCountryListSortedByPopulationDensity();
    }

    @GetMapping("/get-most-bordering-country")
    public Country getMostBorderingCountry(@RequestParam(required = false, defaultValue = "Asia") String region) {
        Optional<Country> country = countryService.getCountryWithMostBorderingCountry(region);
        return country.orElse(null);
    }
}
