package br.com.pismo.customerassessment.repository.services.impl;

import br.com.pismo.customerassessment.repository.AccountRepository;
import br.com.pismo.customerassessment.repository.CustomerRepository;
import br.com.pismo.customerassessment.repository.services.AccountRepositoryService;
import br.com.pismo.customerassessment.repository.services.CustomerRepositoryService;
import org.springframework.stereotype.Service;

@Service
public class CustomerRepositoryServiceImpl implements CustomerRepositoryService {

    public CustomerRepositoryServiceImpl(CustomerRepository customerRepository) {
    }

}
