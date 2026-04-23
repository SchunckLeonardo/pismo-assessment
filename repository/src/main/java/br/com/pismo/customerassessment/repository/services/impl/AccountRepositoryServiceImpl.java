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
    public AccountDTO createAccount(String documentNumber, Double availableCreditLimit) {
        Account accountCreated = accountRepository.save(
                new Account(
                        documentNumber,
                        availableCreditLimit
                )
        );

        return toAccountDTO(accountCreated);
    }

    @Override
    public Optional<AccountDTO> getAccountByDocumentNumber(String documentNumber) {
        return accountRepository.findByDocumentNumber(documentNumber)
                .map(this::toAccountDTO);
    }

    @Override
    public Optional<AccountDTO> getAccountById(Integer accountId) {
        return accountRepository.findById(accountId)
                .map(this::toAccountDTO);
    }

    @Override
    public Void adjustAvailableCreditLimit(Integer accountId, Double amount) {
        Optional<Account> account = accountRepository.findById(accountId);

        if (account.isEmpty()) {
            return null;
        }

        account.get().setAvailableCreditLimit(account.get().getAvailableCreditLimit() + amount);
        accountRepository.save(account.get());
        return null;
    }

    private AccountDTO toAccountDTO(Account account) {
        return new AccountDTO(
                account.getId(),
                account.getDocumentNumber(),
                account.getAvailableCreditLimit()
        );
    }

}
