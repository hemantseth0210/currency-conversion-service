package com.microservice.demo.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CurrencyConversionServiceTest {

	@Autowired
	CurrencyConversionService currencyConversionService;
	
	@Test
	void testConvertCurrency() {
		Assertions.assertEquals(10, 11);
	}

}
