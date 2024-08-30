package com.ms.customerflow.command;

import com.ms.customerflow.model.Customer;
import com.ms.customerflow.repository.ICustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
@Slf4j
public class FakeDataAdapter implements ApplicationRunner {

    private final ICustomerRepository customerRepository;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        Customer customer = new Customer();
        customer.setName("Alice");
        customer.setLastname("Fallen");
        customer.setBirthday(LocalDate.now());
        log.info("{}",customerRepository.save(customer));

        customer = new Customer();
        customer.setName("Alex");
        customer.setLastname("Max");
        customer.setBirthday(LocalDate.now());
        log.info("{}",customerRepository.save(customer));
    }
}
