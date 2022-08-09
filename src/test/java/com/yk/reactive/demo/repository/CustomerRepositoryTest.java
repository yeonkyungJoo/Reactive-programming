package com.yk.reactive.demo.repository;

import com.yk.reactive.demo.Init;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.context.annotation.Import;
import reactor.test.StepVerifier;

@DataR2dbcTest
@Import(Init.class)
class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    @Test
    public void findById() {
//        customerRepository.findById(1L).subscribe(customer -> {
//            System.out.println(customer);
//        });

        StepVerifier
                .create(customerRepository.findById(1L))
                .expectNextMatches(customer -> customer.getFirstName().equals("John"))
                .expectComplete()
                .verify();
    }
}