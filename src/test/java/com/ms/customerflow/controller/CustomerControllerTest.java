package com.ms.customerflow.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.customerflow.controller.request.CreateCustomerRequest;
import com.ms.customerflow.service.ICustomerService;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @MockBean
    ICustomerService customerService;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
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

    @Test
    @DisplayName("Customer delete by customerId ")
    void givenCustomerId_whenDeleteById_thenReturnSuccess() throws Exception {
        final String url = "/api/customer/delete/%s";
        final String newUrl = String.format(url, "1");
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete(newUrl)).andReturn();
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }

    @Test
    @DisplayName("Customer insert data")
    void givenCustomerRequest_whenInsertData_thenReturnSuccess() throws Exception {
        CreateCustomerRequest request = new CreateCustomerRequest();
        request.setName("Emma");
        request.setLastname("Attorney");
        request.setBirthday(LocalDate.now());
        final String url = "/api/customer/insert";
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))).andReturn();
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }

    @Test
    @DisplayName("Customer insert data with customer name is null")
    void givenCustomerRequest_withNameNull_whenInsertData_thenReturnFailed() throws Exception {
        CreateCustomerRequest request = new CreateCustomerRequest();
        request.setLastname("Attorney");
        request.setBirthday(LocalDate.now());
        final String url = "/api/customer/insert";
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))).andReturn();
        assertEquals(HttpStatus.BAD_REQUEST.value(), result.getResponse().getStatus());
    }

    @Test
    @DisplayName("Customer insert data with customer lastname is null")
    void givenCustomerRequest_withLastNameNull_whenInsertData_thenReturnFailed() throws Exception {
        CreateCustomerRequest request = new CreateCustomerRequest();
        request.setName("Emma");
        request.setBirthday(LocalDate.now());
        final String url = "/api/customer/insert";
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))).andReturn();
        assertEquals(HttpStatus.BAD_REQUEST.value(), result.getResponse().getStatus());
    }
}