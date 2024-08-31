package com.ms.customerflow.service;

import com.ms.customerflow.controller.request.CreateCustomerRequest;
import com.ms.customerflow.controller.response.CustomerResponse;

import java.util.List;

public interface ICustomerService {

    List<CustomerResponse> getAllCustomers();

    List<CustomerResponse> findByCustomerName(String name);

    void deleteByCustomerId(Long id);

    void insert(CreateCustomerRequest customerRequest);
}
