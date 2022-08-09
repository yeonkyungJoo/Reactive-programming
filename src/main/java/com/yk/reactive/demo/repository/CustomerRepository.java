package com.yk.reactive.demo.repository;

import com.yk.reactive.demo.vo.Customer;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
// https://spring.io/guides/gs/accessing-data-r2dbc/
public interface CustomerRepository extends ReactiveCrudRepository<Customer, Long> {

    @Query("SELECT * FROM customer WHERE last_name = :lastName")
    Flux<Customer> findByLastName(String lastName);
}
