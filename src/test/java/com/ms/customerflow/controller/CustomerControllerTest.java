package com.ms.customerflow.controller;

import com.ms.customerflow.service.ICustomerService;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @MockBean
    ICustomerService customerService;
    @Autowired
    MockMvc mockMvc;
    @Test
    @DisplayName("Retrieve get all customers")
    void thenReturnCustomerList() throws Exception {
        final String url = "/api/customer/retrieve";
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(url)).andReturn();
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }

    @Test
    @DisplayName("Customer search by name")
    void givenCustomerName_whenSearchName_thenReturnCustomers() throws Exception {
        final String url = "/api/customer/search/%s";
        final String newUrl = String.format(url, "Alice");
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(newUrl)).andReturn();
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }
}