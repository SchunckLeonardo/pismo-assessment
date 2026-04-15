package br.com.pismo.customerassessment.service.impl;

import br.com.pismo.customerassessment.entity.account.dto.AccountDTO;
import br.com.pismo.customerassessment.entity.account.request.CreateAccountRequestDTO;
import br.com.pismo.customerassessment.entity.account.response.CreateAccountResponseDTO;
import br.com.pismo.customerassessment.entity.account.response.RetrieveAccountResponseDTO;
import br.com.pismo.customerassessment.entity.exceptions.AccountAlreadyExistsException;
import br.com.pismo.customerassessment.entity.exceptions.AccountNotExistsException;
import br.com.pismo.customerassessment.repository.services.AccountRepositoryService;
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
class AccountServiceImplTest {

    @Mock
    private AccountRepositoryService accountRepositoryService;

    @InjectMocks
    private AccountServiceImpl accountService;

    @Test
    void createAccountShouldReturnCreatedAccountWhenDocumentNumberDoesNotExist() {
        CreateAccountRequestDTO requestDTO = new CreateAccountRequestDTO("52998224725");
        AccountDTO accountDTO = new AccountDTO(1, "52998224725");

        when(accountRepositoryService.getAccountByDocumentNumber("52998224725")).thenReturn(Optional.empty());
        when(accountRepositoryService.createAccount("52998224725")).thenReturn(accountDTO);

        CreateAccountResponseDTO responseDTO = accountService.createAccount(requestDTO);

        assertThat(responseDTO.accountId()).isEqualTo(1);
        assertThat(responseDTO.documentNumber()).isEqualTo("52998224725");
        verify(accountRepositoryService).getAccountByDocumentNumber("52998224725");
        verify(accountRepositoryService).createAccount("52998224725");
    }

    @Test
    void createAccountShouldThrowExceptionWhenDocumentNumberAlreadyExists() {
        CreateAccountRequestDTO requestDTO = new CreateAccountRequestDTO("52998224725");

        when(accountRepositoryService.getAccountByDocumentNumber("52998224725"))
                .thenReturn(Optional.of(new AccountDTO(1, "52998224725")));

        assertThatThrownBy(() -> accountService.createAccount(requestDTO))
                .isInstanceOf(AccountAlreadyExistsException.class)
                .hasMessage("Account with document number 52998224725 already exists.");

        verify(accountRepositoryService).getAccountByDocumentNumber("52998224725");
        verify(accountRepositoryService, never()).createAccount(anyString());
    }

    @Test
    void retrieveAccountShouldReturnAccountWhenIdExists() {
        when(accountRepositoryService.getAccountById(1)).thenReturn(Optional.of(new AccountDTO(1, "52998224725")));

        RetrieveAccountResponseDTO responseDTO = accountService.retrieveAccount(1);

        assertThat(responseDTO.accountId()).isEqualTo(1);
        assertThat(responseDTO.documentNumber()).isEqualTo("52998224725");
        verify(accountRepositoryService).getAccountById(1);
    }

    @Test
    void retrieveAccountShouldThrowExceptionWhenIdDoesNotExist() {
        when(accountRepositoryService.getAccountById(99)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> accountService.retrieveAccount(99))
                .isInstanceOf(AccountNotExistsException.class)
                .hasMessage("Account with id 99 does not exist.");

        verify(accountRepositoryService).getAccountById(99);
    }

}
