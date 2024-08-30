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

    @Test
    void givenCustomerName_whenSearchName_thenReturnCustomers(){
        List<Customer> customers = List.of(
                new Customer(1L, "Alex", "Max", LocalDate.now()),
                new Customer(2L, "Alice", "Jany",LocalDate.now()),
                new Customer(3L, "Katey","Burke",LocalDate.now())
        );

        Mockito.when(customerRepository.findByNameContainingIgnoreCase("Alice")).thenReturn(customers);
        assertEquals(1, customerService.findByCustomerName("Alice").size());

        Mockito.when(customerRepository.findByNameContainingIgnoreCase("Katey")).thenReturn(customers);
        assertEquals(1, customerService.findByCustomerName("Katey").size());

        Mockito.when(customerRepository.findByNameContainingIgnoreCase("Al")).thenReturn(customers);
        assertEquals(2, customerService.findByCustomerName("Al").size());

        Mockito.when(customerRepository.findByNameContainingIgnoreCase("John")).thenReturn(customers);
        assertEquals(0, customerService.findByCustomerName("John").size());
    }
}