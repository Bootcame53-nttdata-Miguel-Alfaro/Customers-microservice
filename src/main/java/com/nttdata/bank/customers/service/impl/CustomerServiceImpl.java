package com.nttdata.bank.customers.service.impl;

import com.nttdata.bank.customers.domain.Customer;
import com.nttdata.bank.customers.domain.CustomerSummary;
import com.nttdata.bank.customers.model.Account;
import com.nttdata.bank.customers.model.Credit;
import com.nttdata.bank.customers.repository.CustomerRepository;
import com.nttdata.bank.customers.service.AccountService;
import com.nttdata.bank.customers.service.CustomerService;
import com.nttdata.bank.customers.service.LoanService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final AccountService accountService;
    private final LoanService loanService;

    public CustomerServiceImpl(CustomerRepository customerRepository, AccountService accountService, LoanService loanService) {
        this.customerRepository = customerRepository;
        this.accountService = accountService;
        this.loanService = loanService;
    }

    @Override
    public Mono<Customer> save(Mono<Customer> customer) {
        return customer.flatMap(customerRepository::insert);
    }

    @Override
    public Mono<Customer> findById(String id) {
        return customerRepository.findById(id);
    }

    @Override
    public Mono<Customer> update(String id, Mono<Customer> customer) {
        return customerRepository.findById(id)
                .flatMap(c -> customer)
                .doOnNext(e -> e.setId(id))
                .flatMap(customerRepository::save);
    }

    @Override
    public Mono<Void> delete(String id) {
        return customerRepository.findById(id)
                .flatMap(c -> customerRepository.deleteById(id));
    }

    @Override
    public Mono<CustomerSummary> summarize(String id) {
        Flux<Account> accountsFlux = accountService.findAccountsByCustomerId(id)
                .doOnError(error -> System.err.println("Error fetching accounts: " + error.getMessage()));

        Flux<Credit> creditsFlux = loanService.findLoansByCustomerId(id)
                .doOnError(error -> System.err.println("Error fetching credits: " + error.getMessage()));

        return Mono.zip(accountsFlux.collectList(), creditsFlux.collectList())
                .map(tuple -> new CustomerSummary(tuple.getT1(), tuple.getT2()))
                .doOnError(error -> System.err.println("Error creating CustomerSummary: " + error.getMessage()));
    }
}
