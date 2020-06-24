package com.microservice.demo.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.microservice.demo.controller.CurrencyConversionController;
import com.microservice.demo.domain.CurrencyConversion;

@Service
public class CurrencyConversionService {

	private static final Logger logger = LoggerFactory.getLogger(CurrencyConversionService.class);

	
	@Value("${curreny.exchange.service.url}")
	private String currencyExchangeServiceUrl;
	
	public CurrencyConversion convertCurrency(String from, String to, BigDecimal quantity) {
		//Rest Template Problem 1
		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);
		logger.info("Calling the Currency Exchange Service {} to convert currency from {} to {}", currencyExchangeServiceUrl, from, to);
		ResponseEntity<CurrencyConversion> forEntity = new RestTemplate()
				.getForEntity(currencyExchangeServiceUrl + "/currency-exchange/{from}/to/{to}", 
						CurrencyConversion.class, uriVariables);
		CurrencyConversion response = forEntity.getBody();
		logger.info("Response received from Currency Exchange Service {} ", response);
		if(response == null) {
			throw new RuntimeException("Exchange rate is not found for from "+ from + " and to "+ to);
		}
		
		return new CurrencyConversion(response.getId(), from, to, 
				response.getConversionMultiple(), quantity, 
				quantity.multiply(response.getConversionMultiple()));
	}

}
