package com.ms.customerflow.controller;

import com.ms.customerflow.controller.response.CustomerResponse;
import com.ms.customerflow.service.ICustomerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    private final ICustomerService customerService;

    public CustomerController(ICustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/retrieve")
    public List<CustomerResponse> retrieveCustomers(){
        return customerService.getAllCustomers();
    }
}
