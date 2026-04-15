package br.com.pismo.customerassessment.repository.services;

import br.com.pismo.customerassessment.entity.account.dto.AccountDTO;

import java.util.Optional;

public interface AccountRepositoryService {

    AccountDTO createAccount(String documentNumber);
    Optional<AccountDTO> getAccountByDocumentNumber(String documentNumber);
    Optional<AccountDTO> getAccountById(Integer accountId);

}
