package com.cognizant.springlearn.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * HANDS-ON 2: Hello World RESTful Web Service
 * 
 * Access: http://localhost:8080/hello
 * Returns: "Hello World!!"
 */
@RestController
public class HelloWorldController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldController.class);

    @GetMapping("/hello")
    public String sayHello() {
        LOGGER.info("Start sayHello()");
        LOGGER.info("End sayHello()");
        return "Hello World!!";
    }
}
