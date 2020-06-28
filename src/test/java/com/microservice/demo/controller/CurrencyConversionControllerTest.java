package com.microservice.demo.controller;



import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.demo.domain.CurrencyConversion;
import com.microservice.demo.service.CurrencyConversionService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CurrencyConversionControllerTest {

	private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    CurrencyConversionService currencyConversionService;

    @Autowired
    ObjectMapper objectMapper;
    
    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }
	
	@Test
	public void testConvertCurrency() throws Exception {
		CurrencyConversion currencyConversion = new CurrencyConversion();
		currencyConversion.setFrom("USD");
		currencyConversion.setTo("INR");
		currencyConversion.setQuantity(BigDecimal.valueOf(1011.63));
		currencyConversion.setConversionMultiple(BigDecimal.valueOf(65.00));
		currencyConversion.setTotalCalculatedAmout(BigDecimal.valueOf(65755.95));
		currencyConversion.setId(10001L);
		when(currencyConversionService.convertCurrency(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(currencyConversion);
		
		mockMvc.perform(get("/currency-converter/USD/to/INR/quantity/1011.63"))
        .andExpect(status().isOk());
	}

}
