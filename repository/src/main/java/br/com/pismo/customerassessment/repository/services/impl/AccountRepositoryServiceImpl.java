package br.com.pismo.customerassessment.repository.services.impl;

import br.com.pismo.customerassessment.repository.AccountRepository;
import br.com.pismo.customerassessment.repository.services.AccountRepositoryService;
import org.springframework.stereotype.Service;

@Service
public class AccountRepositoryServiceImpl implements AccountRepositoryService {

    public AccountRepositoryServiceImpl(AccountRepository accountRepository) {
    }

}
