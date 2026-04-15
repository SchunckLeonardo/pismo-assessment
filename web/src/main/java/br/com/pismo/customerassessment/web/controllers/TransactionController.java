package br.com.pismo.customerassessment.web.controllers;

import br.com.pismo.customerassessment.entity.transaction.request.CreateTransactionRequestDTO;
import br.com.pismo.customerassessment.entity.transaction.response.CreateTransactionResponseDTO;
import org.springframework.http.ResponseEntity;

public interface TransactionController {

    ResponseEntity<CreateTransactionResponseDTO> createTransaction(CreateTransactionRequestDTO requestDTO);

}
