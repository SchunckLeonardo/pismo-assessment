package br.com.pismo.customerassessment.service.impl;

import br.com.pismo.customerassessment.entity.account.dto.AccountDTO;
import br.com.pismo.customerassessment.entity.account.request.CreateAccountRequestDTO;
import br.com.pismo.customerassessment.entity.account.response.CreateAccountResponseDTO;
import br.com.pismo.customerassessment.entity.account.response.RetrieveAccountResponseDTO;
import br.com.pismo.customerassessment.entity.exceptions.AccountAlreadyExistsException;
import br.com.pismo.customerassessment.entity.exceptions.AccountNotExistsException;
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
    public CreateAccountResponseDTO createAccount(CreateAccountRequestDTO requestDTO) {
        String documentNumber = requestDTO.documentNumber();
        Double availableCreditLimit = requestDTO.availableCreditLimit();

        accountRepositoryService.getAccountByDocumentNumber(documentNumber)
                .ifPresent(account -> {
                    throw new AccountAlreadyExistsException(documentNumber);
                });

        AccountDTO createdAccount = accountRepositoryService.createAccount(documentNumber, availableCreditLimit);

        return new CreateAccountResponseDTO(
                createdAccount.accountId(),
                createdAccount.documentNumber(),
                createdAccount.availableCreditLimit()
        );
    }

    @Override
    public RetrieveAccountResponseDTO retrieveAccount(Integer accountId) {
        AccountDTO account = accountRepositoryService.getAccountById(accountId)
                .orElseThrow(() -> new AccountNotExistsException(accountId));

        return new RetrieveAccountResponseDTO(
                account.accountId(),
                account.documentNumber(),
                account.availableCreditLimit()
        );
    }

}
