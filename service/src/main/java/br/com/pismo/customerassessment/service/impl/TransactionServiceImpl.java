package br.com.pismo.customerassessment.service.impl;

import br.com.pismo.customerassessment.entity.exceptions.AccountNotExistsException;
import br.com.pismo.customerassessment.entity.exceptions.OperationTypeNotExistsException;
import br.com.pismo.customerassessment.entity.operationtype.dto.OperationTypeDTO;
import br.com.pismo.customerassessment.entity.transaction.dto.TransactionDTO;
import br.com.pismo.customerassessment.entity.transaction.request.CreateTransactionRequestDTO;
import br.com.pismo.customerassessment.entity.transaction.response.CreateTransactionResponseDTO;
import br.com.pismo.customerassessment.repository.services.AccountRepositoryService;
import br.com.pismo.customerassessment.repository.services.OperationTypeRepositoryService;
import br.com.pismo.customerassessment.repository.services.TransactionRepositoryService;
import br.com.pismo.customerassessment.service.TransactionService;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {

    private static final Integer PAYMENT_OPERATION_TYPE_ID = 4;

    private final AccountRepositoryService accountRepositoryService;
    private final OperationTypeRepositoryService operationTypeRepositoryService;
    private final TransactionRepositoryService transactionRepositoryService;

    public TransactionServiceImpl(
            AccountRepositoryService accountRepositoryService,
            OperationTypeRepositoryService operationTypeRepositoryService,
            TransactionRepositoryService transactionRepositoryService
    ) {
        this.accountRepositoryService = accountRepositoryService;
        this.operationTypeRepositoryService = operationTypeRepositoryService;
        this.transactionRepositoryService = transactionRepositoryService;
    }

    @Override
    public CreateTransactionResponseDTO createTransaction(CreateTransactionRequestDTO requestDTO) {
        Integer accountId = requestDTO.accountId();
        Integer operationTypeId = requestDTO.operationTypeId();

        accountRepositoryService.getAccountById(accountId)
                .orElseThrow(() -> new AccountNotExistsException(accountId));

        OperationTypeDTO operationType = operationTypeRepositoryService.getOperationTypeById(operationTypeId)
                .orElseThrow(() -> new OperationTypeNotExistsException(operationTypeId));

        Double normalizedAmount = normalizeAmount(operationType, requestDTO.amount());

        TransactionDTO transaction = transactionRepositoryService.createTransaction(
                accountId,
                operationTypeId,
                normalizedAmount
        );

        return new CreateTransactionResponseDTO(
                transaction.transactionId(),
                transaction.accountId(),
                transaction.operationTypeId(),
                transaction.amount()
        );
    }

    private Double normalizeAmount(OperationTypeDTO operationType, Double amount) {
        double absoluteAmount = Math.abs(amount);
        boolean isPayment = PAYMENT_OPERATION_TYPE_ID.equals(operationType.operationTypeId());
        return isPayment ? absoluteAmount : -absoluteAmount;
    }

}
