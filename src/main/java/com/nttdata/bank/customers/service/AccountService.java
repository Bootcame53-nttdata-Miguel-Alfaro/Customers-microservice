package com.nttdata.bank.customers.service;

import com.nttdata.bank.customers.model.Account;
import reactor.core.publisher.Flux;

public interface AccountService {
    Flux<Account> findAccountsByCustomerId(String customerId);
}