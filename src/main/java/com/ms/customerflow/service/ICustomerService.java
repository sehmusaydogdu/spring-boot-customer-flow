package com.ms.customerflow.service;

import com.ms.customerflow.controller.response.CustomerResponse;

import java.util.List;

public interface ICustomerService {

    List<CustomerResponse> getAllCustomers();
}
