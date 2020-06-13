package com.microservice.demo.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.microservice.demo.domain.CurrencyConversion;

@Service
public class CurrencyConversionService {

	@Value("${curreny.exchange.service.url}")
	private String currencyExchangeServiceUrl;
	
	public CurrencyConversion convertCurrency(String from, String to, BigDecimal quantity) {
		//Rest Template Problem 1
		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);
		
		ResponseEntity<CurrencyConversion> forEntity = new RestTemplate()
				.getForEntity(currencyExchangeServiceUrl + "/currency-exchange/{from}/to/{to}", 
						CurrencyConversion.class, uriVariables);
		CurrencyConversion response = forEntity.getBody();
		
		return new CurrencyConversion(response.getId(), from, to, 
				response.getConversionMultiple(), quantity, 
				quantity.multiply(response.getConversionMultiple()));
	}

}
