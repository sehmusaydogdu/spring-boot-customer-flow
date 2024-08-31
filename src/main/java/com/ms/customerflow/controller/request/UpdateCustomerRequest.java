package com.ms.customerflow.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UpdateCustomerRequest {

    @NotNull(message = "Customer id must not be null")
    private Long customerId;

    @NotBlank(message = "Customer name must not be empty")
    @NotNull(message = "Customer name must not be null")
    private String name;

    @NotBlank(message = "Customer lastname must not be empty")
    @NotNull(message = "Customer lastname must not be null")
    private String lastname;

    private LocalDate birthday;
}
