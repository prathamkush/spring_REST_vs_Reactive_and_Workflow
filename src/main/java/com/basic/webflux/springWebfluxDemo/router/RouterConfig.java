package com.basic.webflux.springWebfluxDemo.router;


import com.basic.webflux.springWebfluxDemo.handler.CustomerHandler;
import com.basic.webflux.springWebfluxDemo.handler.CustomerStreamHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

// Doing the configuration by Router-Handler approach (Functional Endpoints)
// instead of Controller-Service approach
@Configuration
public class RouterConfig {

    @Autowired
    private CustomerHandler handler;

    @Autowired
    private CustomerStreamHandler streamHandler;


    @Bean
    public RouterFunction<ServerResponse> routerFunction(){
        return RouterFunctions.route()
                .GET("/router/customers", handler::loadCustomers)
                .GET("/router/customers/stream", streamHandler::getCustomers)
                .GET("/router/customer/{id}", handler::findCustomerById)
                .POST("/router/customer/save-customer", handler::saveCustomer)
                .build();
    }



}
