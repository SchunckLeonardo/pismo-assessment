package br.com.pismo.customerassessment.web.controllers;

import br.com.pismo.customerassessment.entity.account.request.CreateAccountRequestDTO;
import br.com.pismo.customerassessment.entity.account.response.CreateAccountResponseDTO;
import br.com.pismo.customerassessment.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
public class AccountControllerImpl implements AccountController{

    private final AccountService accountService;

    public AccountControllerImpl(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<CreateAccountResponseDTO> createAccount(@RequestBody @Valid CreateAccountRequestDTO requestDTO) {
        return ResponseEntity.ok(accountService.createAccount(requestDTO));
    }

}
