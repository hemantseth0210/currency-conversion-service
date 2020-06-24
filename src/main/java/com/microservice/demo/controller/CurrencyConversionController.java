package com.microservice.demo.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.microservice.demo.domain.CurrencyConversion;
import com.microservice.demo.service.CurrencyConversionService;

@RestController
public class CurrencyConversionController {

	@Autowired
	CurrencyConversionService currencyConversionService;
	
	//Using RestTemplate
	@GetMapping("/currency-converter/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion convertCurrency(@PathVariable String from, 
			@PathVariable String to, @PathVariable BigDecimal quantity) {
		
		return currencyConversionService.convertCurrency(from, to, quantity);
		
	}
	
	
}
