package br.com.pismo.customerassessment.web.controllers.impl;

import br.com.pismo.customerassessment.entity.account.request.CreateAccountRequestDTO;
import br.com.pismo.customerassessment.entity.account.response.CreateAccountResponseDTO;
import br.com.pismo.customerassessment.entity.account.response.RetrieveAccountResponseDTO;
import br.com.pismo.customerassessment.service.AccountService;
import br.com.pismo.customerassessment.web.controllers.AccountController;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
@Tag(name = "Accounts", description = "Endpoints for customer account creation and lookup.")
public class AccountControllerImpl implements AccountController {

    private final AccountService accountService;

    public AccountControllerImpl(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<CreateAccountResponseDTO> createAccount(@RequestBody @Valid CreateAccountRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(accountService.createAccount(requestDTO));
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<RetrieveAccountResponseDTO> retrieveAccount(@PathVariable(name = "accountId") Integer accountId) {
        return ResponseEntity.ok(accountService.retrieveAccount(accountId));
    }

}
