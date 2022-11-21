package com.basic.webflux.springWebfluxDemo.handler;


import com.basic.webflux.springWebfluxDemo.model.Customer;
import com.basic.webflux.springWebfluxDemo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerHandler {

    @Autowired
    private CustomerRepository repository;

    public Mono<ServerResponse> loadCustomers(ServerRequest request){
        Flux<Customer> customerList = repository.getCustomerList();
        return ServerResponse.ok().body(customerList, Customer.class);
    }

    public Mono<ServerResponse> findCustomerById(ServerRequest request) {
        int customer_id = Integer.valueOf(request.pathVariable("id"));
//        repository.getCustomersList().filter(c -> c.getId()==customer_id).take(1).single();
        Mono<Customer> customerMono = repository.getCustomerList().filter(c -> c.getId() == customer_id).next();

        return ServerResponse.ok().body(customerMono, Customer.class);
    }


    public Mono<ServerResponse> saveCustomer(ServerRequest request){

         Mono<Customer> customerMono = request.bodyToMono(Customer.class);
         Mono<String> savedResponse = customerMono.map(model -> model.getId() + " : " + model.getName());

         return ServerResponse.ok().body(savedResponse, Customer.class);
    }
}
