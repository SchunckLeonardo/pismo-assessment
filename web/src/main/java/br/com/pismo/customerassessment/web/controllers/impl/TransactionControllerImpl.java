package br.com.pismo.customerassessment.web.controllers.impl;

import br.com.pismo.customerassessment.entity.transaction.request.CreateTransactionRequestDTO;
import br.com.pismo.customerassessment.entity.transaction.response.CreateTransactionResponseDTO;
import br.com.pismo.customerassessment.service.TransactionService;
import br.com.pismo.customerassessment.web.controllers.TransactionController;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
@Tag(name = "Transactions", description = "Endpoints for account transaction creation.")
public class TransactionControllerImpl implements TransactionController {

    private final TransactionService transactionService;

    public TransactionControllerImpl(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<CreateTransactionResponseDTO> createTransaction(@RequestBody @Valid CreateTransactionRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(transactionService.createTransaction(requestDTO));
    }

}
