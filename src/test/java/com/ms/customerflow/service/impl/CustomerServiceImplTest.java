package com.ms.customerflow.service.impl;

import com.ms.customerflow.controller.response.CustomerResponse;
import com.ms.customerflow.model.Customer;
import com.ms.customerflow.repository.ICustomerRepository;
import com.ms.customerflow.service.ICustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    ICustomerRepository customerRepository;
    ICustomerService customerService;

    @BeforeEach
    public void init(){
        customerRepository = mock();
        customerService = new CustomerServiceImpl(customerRepository);
    }
    @Test
    void thenReturnCustomerList(){
        Customer customer = new Customer();
        customer.setCustomerId(1L);
        customer.setName("Alice");
        customer.setLastname("Fallen");
        customer.setBirthday(LocalDate.now());
        List<Customer> customers = List.of(customer);

        Mockito.when(customerRepository.findAll(Sort.by("customerId"))).thenReturn(customers);
        List<CustomerResponse> responses = customerService.getAllCustomers();
        assertEquals(1, responses.size());
    }
}