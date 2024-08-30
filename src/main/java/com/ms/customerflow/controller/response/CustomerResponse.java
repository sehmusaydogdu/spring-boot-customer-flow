package com.ms.customerflow.controller.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class CustomerResponse {
    private Long customerId;
    private String name;
    private String lastname;
    private LocalDate birthday;
}
