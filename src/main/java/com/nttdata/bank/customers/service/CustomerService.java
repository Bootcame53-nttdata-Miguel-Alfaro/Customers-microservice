package com.nttdata.bank.customers.service;

import com.nttdata.bank.customers.domain.Customer;
import com.nttdata.bank.customers.domain.CustomerSummary;
import reactor.core.publisher.Mono;

public interface CustomerService {
    Mono<Customer> save(Mono<Customer> customer);
    Mono<Customer> findById(String id);
    Mono<Customer> update(String id, Mono<Customer> customer);
    Mono<Void> delete(String id);
    Mono<CustomerSummary> summarize(String id);
}
