package com.ms.customerflow.service.impl;

import com.ms.customerflow.controller.request.CreateCustomerRequest;
import com.ms.customerflow.controller.request.UpdateCustomerRequest;
import com.ms.customerflow.controller.response.CustomerResponse;
import com.ms.customerflow.model.Customer;
import com.ms.customerflow.repository.ICustomerRepository;
import com.ms.customerflow.service.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

    private final ICustomerRepository customerRepository;

    @Override
    public List<CustomerResponse> getAllCustomers() {
        List<Customer> dbCustomerList = customerRepository.findAll(Sort.by("customerId"));
        List<CustomerResponse> customerResponseList = new ArrayList<>();
        dbCustomerList.forEach(item-> customerResponseList.add(new CustomerResponse(item.getCustomerId(), item.getName(), item.getLastname(), item.getBirthday())));
        return customerResponseList;
    }

    @Override
    public List<CustomerResponse> findByCustomerName(String name) {
        List<Customer> dbCustomerList = customerRepository.findByNameContainingIgnoreCase(name);
        List<Customer> dbSearchResult = dbCustomerList.stream().filter(item -> item.getName().contains(name)).toList();
        List<CustomerResponse> customerResponseList = new ArrayList<>();
        dbSearchResult.forEach(item-> customerResponseList.add(new CustomerResponse(item.getCustomerId(), item.getName(), item.getLastname(), item.getBirthday())));
        return customerResponseList;
    }

    @Override
    public void deleteByCustomerId(Long id) {
        int effectedRecord = customerRepository.deleteByCustomerId(id);
        Assert.isTrue(effectedRecord > 0, "No records found to delete.");
    }

    @Override
    public void insert(CreateCustomerRequest customerRequest) {
        Customer customer = new Customer();
        customer.setName(customerRequest.getName());
        customer.setLastname(customerRequest.getLastname());
        customer.setBirthday(customerRequest.getBirthday());
        Customer saved = customerRepository.save(customer);
        Assert.notNull(saved, "An error occurred while adding a record");
    }

    @Override
    public void update(Long customerId, UpdateCustomerRequest customerRequest) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        Customer customer = optionalCustomer.orElseThrow();
        customer.setName(customerRequest.getName());
        customer.setLastname(customerRequest.getLastname());
        customer.setBirthday(customerRequest.getBirthday());
        Customer updated = customerRepository.save(customer);
        Assert.notNull(updated, "An error occurred while updating a record");
    }
}
