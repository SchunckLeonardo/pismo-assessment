package br.com.pismo.customerassessment.service.impl;

import br.com.pismo.customerassessment.entity.account.dto.AccountDTO;
import br.com.pismo.customerassessment.entity.exceptions.AccountNotExistsException;
import br.com.pismo.customerassessment.entity.exceptions.OperationTypeNotExistsException;
import br.com.pismo.customerassessment.entity.operationtype.dto.OperationTypeDTO;
import br.com.pismo.customerassessment.entity.transaction.dto.TransactionDTO;
import br.com.pismo.customerassessment.entity.transaction.request.CreateTransactionRequestDTO;
import br.com.pismo.customerassessment.entity.transaction.response.CreateTransactionResponseDTO;
import br.com.pismo.customerassessment.repository.services.AccountRepositoryService;
import br.com.pismo.customerassessment.repository.services.OperationTypeRepositoryService;
import br.com.pismo.customerassessment.repository.services.TransactionRepositoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransactionServiceImplTest {

    @Mock
    private AccountRepositoryService accountRepositoryService;

    @Mock
    private OperationTypeRepositoryService operationTypeRepositoryService;

    @Mock
    private TransactionRepositoryService transactionRepositoryService;

    @InjectMocks
    private TransactionServiceImpl transactionService;

    @Test
    void createTransactionShouldStorePositiveAmountForPaymentOperation() {
        CreateTransactionRequestDTO requestDTO = new CreateTransactionRequestDTO(1, 4, 123.45);

        when(accountRepositoryService.getAccountById(1)).thenReturn(Optional.of(new AccountDTO(1, "52998224725")));
        when(operationTypeRepositoryService.getOperationTypeById(4)).thenReturn(Optional.of(new OperationTypeDTO(4, "PAYMENT")));
        when(transactionRepositoryService.createTransaction(1, 4, 123.45))
                .thenReturn(new TransactionDTO(10, 1, 4, 123.45));

        CreateTransactionResponseDTO responseDTO = transactionService.createTransaction(requestDTO);

        assertThat(responseDTO.transactionId()).isEqualTo(10);
        assertThat(responseDTO.accountId()).isEqualTo(1);
        assertThat(responseDTO.operationTypeId()).isEqualTo(4);
        assertThat(responseDTO.amount()).isEqualTo(123.45);
        verify(transactionRepositoryService).createTransaction(1, 4, 123.45);
    }

    @Test
    void createTransactionShouldStoreNegativeAmountForDebtOperation() {
        CreateTransactionRequestDTO requestDTO = new CreateTransactionRequestDTO(1, 1, 123.45);

        when(accountRepositoryService.getAccountById(1)).thenReturn(Optional.of(new AccountDTO(1, "52998224725")));
        when(operationTypeRepositoryService.getOperationTypeById(1)).thenReturn(Optional.of(new OperationTypeDTO(1, "PURCHASE")));
        when(transactionRepositoryService.createTransaction(1, 1, -123.45))
                .thenReturn(new TransactionDTO(11, 1, 1, -123.45));

        CreateTransactionResponseDTO responseDTO = transactionService.createTransaction(requestDTO);

        assertThat(responseDTO.transactionId()).isEqualTo(11);
        assertThat(responseDTO.amount()).isEqualTo(-123.45);
        verify(transactionRepositoryService).createTransaction(1, 1, -123.45);
    }

    @Test
    void createTransactionShouldThrowExceptionWhenAccountDoesNotExist() {
        CreateTransactionRequestDTO requestDTO = new CreateTransactionRequestDTO(99, 4, 123.45);

        when(accountRepositoryService.getAccountById(99)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> transactionService.createTransaction(requestDTO))
                .isInstanceOf(AccountNotExistsException.class)
                .hasMessage("Account with id 99 does not exist.");

        verify(accountRepositoryService).getAccountById(99);
        verifyNoInteractions(operationTypeRepositoryService, transactionRepositoryService);
    }

    @Test
    void createTransactionShouldThrowExceptionWhenOperationTypeDoesNotExist() {
        CreateTransactionRequestDTO requestDTO = new CreateTransactionRequestDTO(1, 9, 123.45);

        when(accountRepositoryService.getAccountById(1)).thenReturn(Optional.of(new AccountDTO(1, "52998224725")));
        when(operationTypeRepositoryService.getOperationTypeById(9)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> transactionService.createTransaction(requestDTO))
                .isInstanceOf(OperationTypeNotExistsException.class)
                .hasMessage("Operation type with id 9 does not exist.");

        verify(accountRepositoryService).getAccountById(1);
        verify(operationTypeRepositoryService).getOperationTypeById(9);
        verifyNoInteractions(transactionRepositoryService);
    }

}
