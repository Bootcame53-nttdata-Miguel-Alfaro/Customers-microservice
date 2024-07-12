package com.nttdata.bank.customers.service;

import com.nttdata.bank.customers.model.Credit;
import reactor.core.publisher.Flux;

public interface LoanService {
    Flux<Credit> findLoansByCustomerId(String customerId);
}
