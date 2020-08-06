package com.tosiatech.microservices.currencyconversionservice;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/* If you use Ribbon, you do not need to use feign client annotation with URL,
 * You are good to go while using only name. Do not forget to specify it in the properties.
 * 
 * @FeignClient(name="currency-exchange-service", url="http://localhost:8000")
 * 
 * @FeignClient(name="currency-exchange-service") --before zuul
 * */

@FeignClient(name="zuul-api-gateway-server")
@RibbonClient("currency-exchange-service")
public interface CurrencyExchangeServiceProxy {

	/* If you use older version < Spring 2.0.0 , 
	 * which means you are using netflix feign instead of openfeign
	 * You need to specify exact path variable after annotations
	 * 
	 * 	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyConversion retrieveExchangeValue(@PathVariable("from") String from, @PathVariable("to") String to);
	
		@GetMapping("/currency-exchange/from/{from}/to/{to}") --> before zuul
	 * */
	
	@GetMapping("/currency-exchange-service/currency-exchange/from/{from}/to/{to}")
	public CurrencyConversion retrieveExchangeValue(@PathVariable String from, @PathVariable String to);
	
}
