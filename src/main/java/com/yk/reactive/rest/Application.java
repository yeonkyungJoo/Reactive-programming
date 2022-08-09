package com.yk.reactive.rest;

import com.yk.reactive.ReactiveApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Application {

    @RestController
    public static class MyController {

        RestTemplate restTemplate = new RestTemplate();

        @GetMapping("/rest")
        public String rest(int index) {
            // restTemplate.getForObject("http://localhost:")
            return "rest " + index;
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(ReactiveApplication.class, args);
    }
}
