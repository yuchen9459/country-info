package com.acc.restcountries.clients;

import com.acc.restcountries.entity.Country;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "country-client", url = "${country.url}")
public interface CountryClient {

    @GetMapping("/all?fields=name,area,population,borders,cca3")
    List<Country> retrieveCountryList();

    @GetMapping("/region/{region}?fields=name,area,population,borders,cca3")
    List<Country> retrieveCountryListByRegion(@PathVariable String region);
}
