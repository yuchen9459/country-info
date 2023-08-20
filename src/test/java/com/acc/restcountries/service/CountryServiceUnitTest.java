package com.acc.restcountries.service;

import com.acc.restcountries.clients.CountryClient;
import com.acc.restcountries.entity.Country;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CountryServiceUnitTest {

    private CountryClient countryClient;
    private CountryService countryService;

    @BeforeEach
    void setUp() {
        countryClient = mock(CountryClient.class);
        countryService = new CountryService(countryClient);
    }

    @Test
    void testGetCountryListSortedByPopulationDensity() {
        // Prepare mock data
        Country country1 = createCountry("Country1", 100, 50, new HashSet<>());
        Country country2 = createCountry("Country2", 200, 50, new HashSet<>());
        List<Country> mockCountries = Arrays.asList(country1, country2);

        // Mock the behavior of countryClient
        when(countryClient.retrieveCountryList()).thenReturn(mockCountries);

        // Call the method
        List<Country> sortedCountries = countryService.getCountryListSortedByPopulationDensity();

        // Assert the result
        assertEquals(sortedCountries.get(0), country2);
        assertEquals(sortedCountries.get(1), country1);
    }

    @Test
    void testGetCountryWithMostBorderingCountry() {
        // Prepare mock data
        Country country1 = createCountry("Country1", 100, 50, new HashSet<>(Arrays.asList("Border1", "Border2")));
        Country country2 = createCountry("Country2", 200, 100, new HashSet<>(Collections.singletonList("Border1")));
        List<Country> mockCountries = Arrays.asList(country1, country2);

        // Mock the behavior of countryClient
        when(countryClient.retrieveCountryListByRegion(anyString())).thenReturn(mockCountries);

        // Call the method
        Optional<Country> countryWithMostBorders = countryService.getCountryWithMostBorderingCountry("Asia");

        // Assert the result
        assertTrue(countryWithMostBorders.isPresent());
        assertEquals(countryWithMostBorders.get(), country1);
    }

    // Helper method to create a Country instance
    private Country createCountry(String cca3, int population, double area, Set<String> borders) {
        Country country = new Country();
        country.setCca3(cca3);
        country.setPopulation(population);
        country.setArea(area);
        country.setBorders(borders);
        return country;
    }
}
