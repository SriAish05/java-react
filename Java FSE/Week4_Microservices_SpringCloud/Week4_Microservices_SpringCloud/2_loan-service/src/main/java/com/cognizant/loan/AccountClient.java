package com.cognizant.loan;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Feign Client - declarative REST client.
 * 
 * Instead of writing RestTemplate/WebClient code manually, we just declare
 * an interface. Feign generates the HTTP call implementation at runtime.
 * 
 * name = "account-service" -> Feign looks up this service in Eureka
 * and load-balances across its instances automatically.
 */
@FeignClient(name = "account-service")
public interface AccountClient {

    @GetMapping("/accounts/{number}")
    Account getAccount(@PathVariable("number") String number);
}
