package br.com.pismo.customerassessment.repository.services.impl;

import br.com.pismo.customerassessment.repository.OperationTypeRepository;
import br.com.pismo.customerassessment.repository.TransactionRepository;
import br.com.pismo.customerassessment.repository.services.OperationTypeRepositoryService;
import br.com.pismo.customerassessment.repository.services.TransactionRepositoryService;
import org.springframework.stereotype.Service;

@Service
public class TransactionRepositoryServiceImpl implements TransactionRepositoryService {

    public TransactionRepositoryServiceImpl(TransactionRepository transactionRepository) {
    }

}
