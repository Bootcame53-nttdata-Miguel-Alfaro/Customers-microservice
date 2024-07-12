package com.nttdata.bank.customers.controller;

import com.nttdata.bank.customers.api.CustomersApi;
import com.nttdata.bank.customers.mapper.CustomerMapper;
import com.nttdata.bank.customers.mapper.CustomerSummaryMapper;
import com.nttdata.bank.customers.model.Customer;
import com.nttdata.bank.customers.model.CustomerSummary;

import com.nttdata.bank.customers.service.CustomerService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CustomerController implements CustomersApi {

    private final CustomerService customerService;
    private final CustomerMapper customerMapper;
    private final CustomerSummaryMapper customerSummaryMapper;

    public CustomerController(CustomerService customerService, CustomerMapper customerMapper, CustomerSummaryMapper customerSummaryMapper) {
        this.customerService = customerService;
        this.customerMapper = customerMapper;
        this.customerSummaryMapper = customerSummaryMapper;
    }

    @Override
    public Mono<ResponseEntity<Map<String, Object>>> addCustomer(Mono<Customer> customer, ServerWebExchange exchange) {
        Map<String, Object> response = new HashMap<>();
        return customerService.save(customer.map(customerMapper::toDomain))
                .map(customerMapper::toModel)
                .map(c -> {
                   response.put("customer", c);
                   response.put("message", "Customer created successfully");
                    return ResponseEntity.status(HttpStatus.CREATED).body(response);
                });
    }

    @Override
    public Mono<ResponseEntity<Customer>> getCustomerById(String id, ServerWebExchange exchange) {
        return customerService.findById(id)
                .map(customerMapper::toModel)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Override
    public Mono<ResponseEntity<CustomerSummary>> getCustomerSummaryById(String id, ServerWebExchange exchange) {
        return customerService.summarize(id)
                .map(customerSummaryMapper::toModel)
                .map(customerSummary -> {
                    System.out.println("Sending response with summary: " + customerSummary);
                    return ResponseEntity.ok(customerSummary);
                })
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Override
    public Mono<ResponseEntity<Map<String, Object>>> updateCustomer(String id, Mono<Customer> customer, ServerWebExchange exchange) {
        Map<String, Object> response = new HashMap<>();
        return customerService.update(id, customer.map(customerMapper::toDomain))
                .map(customerMapper::toModel)
                .map(c -> {
                    response.put("customer", c);
                    response.put("message", "Customer updated successfully");
                    return ResponseEntity.status(HttpStatus.OK).body(response);
                })
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteCustomer(String id, ServerWebExchange exchange) {
        return customerService.findById(id)
                .flatMap(c -> customerService.delete(id)
                        .then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT))))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
