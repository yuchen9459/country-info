package com.acc.restcountries.config;

import com.acc.restcountries.clients.CountryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(clients = {CountryClient.class})
public class FeignConfig {

}
