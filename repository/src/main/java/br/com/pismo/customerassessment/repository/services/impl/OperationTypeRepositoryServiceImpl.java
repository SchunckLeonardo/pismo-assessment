package br.com.pismo.customerassessment.repository.services.impl;

import br.com.pismo.customerassessment.repository.CustomerRepository;
import br.com.pismo.customerassessment.repository.OperationTypeRepository;
import br.com.pismo.customerassessment.repository.services.CustomerRepositoryService;
import br.com.pismo.customerassessment.repository.services.OperationTypeRepositoryService;
import org.springframework.stereotype.Service;

@Service
public class OperationTypeRepositoryServiceImpl implements OperationTypeRepositoryService {

    public OperationTypeRepositoryServiceImpl(OperationTypeRepository operationTypeRepository) {
    }

}
