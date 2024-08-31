package com.ms.customerflow.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CreateCustomerRequest {

    @NotBlank(message = "Invalid Name: empty customer name")
    @NotNull(message = "Invalid Name: customer name is null")
    private String name;

    @NotBlank(message = "Invalid Name: empty customer lastname")
    @NotNull(message = "Invalid Name: customer lastname is null")
    private String lastname;

    private LocalDate birthday;
}
