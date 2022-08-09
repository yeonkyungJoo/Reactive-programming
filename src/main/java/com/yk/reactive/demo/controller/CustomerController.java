package com.yk.reactive.demo.controller;

import com.yk.reactive.demo.repository.CustomerRepository;
import com.yk.reactive.demo.vo.Customer;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

import java.time.Duration;

//@RequiredArgsConstructor
@RestController
public class CustomerController {

    private final CustomerRepository customerRepository;
    private final Sinks.Many<Customer> sink;

    public CustomerController(CustomerRepository customerRepository, Sinks.Many<Customer> sink) {
        this.customerRepository = customerRepository;
        this.sink = Sinks.many().multicast().onBackpressureBuffer();
    }

    @GetMapping("/flux")
    public Flux<Integer> flux() {
        return Flux.just(1, 2, 3, 4, 5)
                .delayElements(Duration.ofSeconds(1)).log();
    }

    @GetMapping(value = "/flux-stream", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Integer> fluxStream() {
        return Flux.just(1, 2, 3, 4, 5)
                .delayElements(Duration.ofSeconds(1)).log();
    }

    @GetMapping("/customer")
    public Flux<Customer> findAll() {
        return customerRepository.findAll()
                .delayElements(Duration.ofSeconds(1)).log();
    }

    @GetMapping("/customer/{id}")
    public Mono<Customer> findAll(@PathVariable Long id) {
        return customerRepository.findById(id).log();
    }

    @GetMapping(value = "/customer/sse", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent<Customer>> findAllSSE() {
//        return customerRepository.findAll()
//                .delayElements(Duration.ofSeconds(1)).log();
        return sink.asFlux().map(customer -> ServerSentEvent.builder(customer).build())
                .doOnCancel(() -> sink.asFlux().blockLast());
    }

    @PostMapping("/customer")
    public Mono<Customer> save() {
        return customerRepository.save(new Customer("gildong", "Hong"))
                .doOnNext(customer -> sink.tryEmitNext(customer));
    }
}
