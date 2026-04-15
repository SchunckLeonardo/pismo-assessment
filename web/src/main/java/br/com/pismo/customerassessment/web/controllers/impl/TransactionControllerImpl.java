package br.com.pismo.customerassessment.web.controllers.impl;

import br.com.pismo.customerassessment.entity.transaction.request.CreateTransactionRequestDTO;
import br.com.pismo.customerassessment.entity.transaction.response.CreateTransactionResponseDTO;
import br.com.pismo.customerassessment.service.TransactionService;
import br.com.pismo.customerassessment.web.controllers.TransactionController;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
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
