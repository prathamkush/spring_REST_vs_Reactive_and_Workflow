package com.basic.webflux.springWebfluxDemo.handler;


import com.basic.webflux.springWebfluxDemo.model.Customer;
import com.basic.webflux.springWebfluxDemo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerStreamHandler {

    @Autowired
    private CustomerRepository repository;

    // asynchronous and non-blocking
    public Mono<ServerResponse> getCustomers(ServerRequest request){
        Flux<Customer> customerStream = repository.getCustomersStream();
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(customerStream, Customer.class);
    }


}
