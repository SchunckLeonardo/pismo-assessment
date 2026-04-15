package br.com.pismo.customerassessment.service.impl;

import br.com.pismo.customerassessment.entity.account.dto.AccountDTO;
import br.com.pismo.customerassessment.entity.account.response.CreateAccountResponseDTO;
import br.com.pismo.customerassessment.entity.exceptions.AccountAlreadyExistsException;
import br.com.pismo.customerassessment.repository.services.AccountRepositoryService;
import br.com.pismo.customerassessment.service.AccountService;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepositoryService accountRepositoryService;

    public AccountServiceImpl(AccountRepositoryService accountRepositoryService) {
        this.accountRepositoryService = accountRepositoryService;
    }

    @Override
    public CreateAccountResponseDTO createAccount(String documentNumber) {
        accountRepositoryService.getAccountByDocumentNumber(documentNumber)
                .ifPresent(account -> {
                    throw new AccountAlreadyExistsException(documentNumber);
                });

        AccountDTO createdAccount = accountRepositoryService.createAccount(documentNumber);

        return new CreateAccountResponseDTO(
                createdAccount.accountId(),
                createdAccount.documentNumber()
        );
    }

}
