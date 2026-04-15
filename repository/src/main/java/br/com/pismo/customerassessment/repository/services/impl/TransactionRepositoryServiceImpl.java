package br.com.pismo.customerassessment.repository.services.impl;

import br.com.pismo.customerassessment.domain.Account;
import br.com.pismo.customerassessment.domain.OperationType;
import br.com.pismo.customerassessment.domain.Transaction;
import br.com.pismo.customerassessment.entity.transaction.dto.TransactionDTO;
import br.com.pismo.customerassessment.repository.AccountRepository;
import br.com.pismo.customerassessment.repository.OperationTypeRepository;
import br.com.pismo.customerassessment.repository.TransactionRepository;
import br.com.pismo.customerassessment.repository.services.TransactionRepositoryService;
import org.springframework.stereotype.Service;

@Service
public class TransactionRepositoryServiceImpl implements TransactionRepositoryService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final OperationTypeRepository operationTypeRepository;

    public TransactionRepositoryServiceImpl(
            TransactionRepository transactionRepository,
            AccountRepository accountRepository,
            OperationTypeRepository operationTypeRepository
    ) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
        this.operationTypeRepository = operationTypeRepository;
    }

    @Override
    public TransactionDTO createTransaction(Integer accountId, Integer operationTypeId, Double amount) {
        Account account = accountRepository.getReferenceById(accountId);
        OperationType operationType = operationTypeRepository.getReferenceById(operationTypeId);

        Transaction transactionCreated = transactionRepository.save(
                buildTransaction(account, operationType, amount)
        );

        return new TransactionDTO(
                transactionCreated.getId(),
                transactionCreated.getAccount().getId(),
                transactionCreated.getOperationType().getId(),
                transactionCreated.getAmount()
        );
    }

    private Transaction buildTransaction(Account account, OperationType operationType, Double amount) {
        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setOperationType(operationType);
        transaction.setAmount(amount);
        return transaction;
    }

}
