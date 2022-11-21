package com.basic.webflux.springWebfluxDemo.service;


import com.basic.webflux.springWebfluxDemo.model.Customer;
import com.basic.webflux.springWebfluxDemo.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    public List<Customer> loadAllCustomers(){
        long start = System.currentTimeMillis();
        List<Customer> customers = repository.getCustomers();

        long end = System.currentTimeMillis();
        System.out.println("Total execution time : "+ (end-start));

        return customers;
    }

    public Flux<Customer> loadAllCustomersStream(){
        long start = System.currentTimeMillis();
        Flux<Customer> customers = repository.getCustomersStream();

        long end = System.currentTimeMillis();
        System.out.println("Total execution time : "+ (end-start));

        return customers;
    }

}
