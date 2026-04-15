package br.com.pismo.customerassessment.repository.services;

import br.com.pismo.customerassessment.entity.transaction.dto.TransactionDTO;

public interface TransactionRepositoryService {

    TransactionDTO createTransaction(Integer accountId, Integer operationTypeId, Double amount);

}
