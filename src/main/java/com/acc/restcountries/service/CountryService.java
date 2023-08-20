package com.acc.restcountries.service;

import com.acc.restcountries.clients.CountryClient;
import com.acc.restcountries.entity.Country;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CountryService {

    private final CountryClient countryClient;

    public CountryService(CountryClient countryClient) {
        this.countryClient = countryClient;
    }

    public List<Country> getCountryListSortedByPopulationDensity() {
        List<Country> countries = countryClient.retrieveCountryList();

        // Sort the country list based on population density
        return countries.stream()
                        .sorted((c1, c2) -> Double.compare(calculateDensity(c2), calculateDensity(c1)))
                        .toList();
    }

    public Optional<Country> getCountryWithMostBorderingCountry(String region) {
        List<Country> countries = countryClient.retrieveCountryListByRegion(region);
        Set<String> sameRegionCountries = getAsianCountriesShortCodes(countries);

        return Optional.ofNullable(findAsianCountryWithMostBorderedCountry(sameRegionCountries, countries));
    }

    private Country findAsianCountryWithMostBorderedCountry(Set<String> asianShortCodes, List<Country> countries) {
        // <Country, amount of bordering excluding current region>
        PriorityQueue<Map.Entry<Country, Integer>> sortedList =
                new PriorityQueue<>((c1, c2) -> c2.getValue() - c1.getValue());

        for (Country country : countries) {
            Set<String> borders = country.getBorders();
            borders.removeAll(asianShortCodes);
            sortedList.add(Map.entry(country, borders.size()));
        }

        return sortedList.isEmpty() ? null : sortedList.peek().getKey();
    }

    private Set<String> getAsianCountriesShortCodes(List<Country> countries) {
        return countries.stream()
                        .map(Country::getCca3)
                        .collect(Collectors.toSet());
    }

    private double calculateDensity(Country country) {
        int population = country.getPopulation();
        double area = country.getArea();
        return area != 0 ? (double) population / area : 0;
    }
}
