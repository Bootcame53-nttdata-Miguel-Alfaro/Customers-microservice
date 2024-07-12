package com.nttdata.bank.customers.mapper;

import com.nttdata.bank.customers.model.CustomerSummary;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public  class CustomerSummaryMapper implements EntityMapper<CustomerSummary, com.nttdata.bank.customers.domain.CustomerSummary> {
    @Override
    public com.nttdata.bank.customers.domain.CustomerSummary toDomain(CustomerSummary model) {
        com.nttdata.bank.customers.domain.CustomerSummary domain = new com.nttdata.bank.customers.domain.CustomerSummary();
        BeanUtils.copyProperties(model, domain);
        return domain;
    }

    @Override
    public  CustomerSummary toModel(com.nttdata.bank.customers.domain.CustomerSummary domain) {
        CustomerSummary model = new CustomerSummary();
        BeanUtils.copyProperties(domain, model);
        return model;
    }
}
