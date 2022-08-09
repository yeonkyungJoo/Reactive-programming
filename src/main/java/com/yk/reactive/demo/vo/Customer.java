package com.yk.reactive.demo.vo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@RequiredArgsConstructor
@Getter @Setter
public class Customer {

    @Id
    private Long id;
    private final String firstName;
    private final String lastName;
}
