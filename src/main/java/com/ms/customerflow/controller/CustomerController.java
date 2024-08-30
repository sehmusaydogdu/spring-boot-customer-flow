package com.ms.customerflow.controller;

import com.ms.customerflow.controller.response.CustomerResponse;
import com.ms.customerflow.service.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final ICustomerService customerService;

    @GetMapping("/retrieve")
    public List<CustomerResponse> retrieveCustomers(){
        return customerService.getAllCustomers();
    }

    @GetMapping("/search/{customerName}")
    public List<CustomerResponse> retrieveCustomers(@PathVariable("customerName") String customerName){
        return customerService.findByCustomerName(customerName);
    }
}
