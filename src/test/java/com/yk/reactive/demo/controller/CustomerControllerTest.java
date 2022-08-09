package com.yk.reactive.demo.controller;

import com.yk.reactive.demo.repository.CustomerRepository;
import com.yk.reactive.demo.vo.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.when;

//@SpringBootTest
//@AutoConfigureWebTestClient
@WebFluxTest
class CustomerControllerTest {

    @Autowired
    WebTestClient webTestClient;
    @MockBean
    CustomerRepository customerRepository;

    @Test
    public void findById() {

        // given
        Mono<Customer> customer = Mono.just(new Customer("Jack", "Bauer"));
        when(customerRepository.findById(1L)).thenReturn(customer);

        // when
        // then
        webTestClient.get().uri("/customer/{id}", 1L)
                .exchange()
                .expectBody()
                .jsonPath("$.firstName").isEqualTo("Jack")
                .jsonPath("$.lastName").isEqualTo("Bauer");

    }
}