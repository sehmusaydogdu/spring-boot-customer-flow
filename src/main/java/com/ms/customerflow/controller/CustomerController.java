package com.ms.customerflow.controller;

import com.ms.customerflow.controller.response.CustomerResponse;
import com.ms.customerflow.service.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final ICustomerService customerService;

    @GetMapping("/retrieve")
    public ResponseEntity<List<CustomerResponse>> retrieveCustomers(){
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @GetMapping("/search/{customerName}")
    public ResponseEntity<List<CustomerResponse>> retrieveCustomers(@PathVariable("customerName") String customerName){
        return ResponseEntity.ok(customerService.findByCustomerName(customerName));
    }

    @DeleteMapping("/delete/{customerId}")
    public void deleteByCustomerId(@PathVariable("customerId") Long customerId) {
        customerService.deleteByCustomerId(customerId);
    }
}
