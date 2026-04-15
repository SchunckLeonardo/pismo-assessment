package br.com.pismo.customerassessment.service;

import br.com.pismo.customerassessment.entity.transaction.request.CreateTransactionRequestDTO;
import br.com.pismo.customerassessment.entity.transaction.response.CreateTransactionResponseDTO;

public interface TransactionService {

    CreateTransactionResponseDTO createTransaction(CreateTransactionRequestDTO requestDTO);

}
