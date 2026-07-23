package com.cognizant.loan.client;

import com.cognizant.loan.model.Account;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * FEIGN CLIENT - declarative REST client for calling account-service.
 *
 * Instead of manually using RestTemplate/WebClient, we just declare an
 * interface. Feign generates the implementation at runtime.
 *
 * name = "account-service" -> Feign looks up this service name in Eureka
 * and load-balances across its instances automatically.
 */
@FeignClient(name = "account-service")
public interface AccountClient {

    /**
     * Maps to GET /accounts/{number} on the account-service.
     */
    @GetMapping("/accounts/{number}")
    Account getAccount(@PathVariable("number") String number);
}
