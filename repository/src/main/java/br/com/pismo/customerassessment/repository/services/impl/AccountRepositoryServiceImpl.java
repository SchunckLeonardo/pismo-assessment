package br.com.pismo.customerassessment.repository.services.impl;

import br.com.pismo.customerassessment.domain.Account;
import br.com.pismo.customerassessment.entity.account.dto.AccountDTO;
import br.com.pismo.customerassessment.repository.AccountRepository;
import br.com.pismo.customerassessment.repository.services.AccountRepositoryService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountRepositoryServiceImpl implements AccountRepositoryService {

    private final AccountRepository accountRepository;

    public AccountRepositoryServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDTO createAccount(String documentNumber) {
        Account accountCreated = accountRepository.save(
                new Account(
                        documentNumber
                )
        );

        return toAccountDTO(accountCreated);
    }

    @Override
    public Optional<AccountDTO> getAccountByDocumentNumber(String documentNumber) {
        return accountRepository.findByDocumentNumber(documentNumber)
                .map(this::toAccountDTO);
    }

    private AccountDTO toAccountDTO(Account account) {
        return new AccountDTO(
                account.getId(),
                account.getDocumentNumber()
        );
    }

}
