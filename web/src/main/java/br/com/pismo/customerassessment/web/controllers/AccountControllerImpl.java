package br.com.pismo.customerassessment.web.controllers;

import br.com.pismo.customerassessment.entity.account.response.CreateAccountResponseDTO;
import br.com.pismo.customerassessment.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
public class AccountControllerImpl implements AccountController{

    private final AccountService accountService;

    public AccountControllerImpl(AccountService accountService) {
        this.accountService = accountService;
    }

    public ResponseEntity<CreateAccountResponseDTO> createAccount(String documentNumber) {
        return ResponseEntity.ok(accountService.createAccount(documentNumber));
    }

}
