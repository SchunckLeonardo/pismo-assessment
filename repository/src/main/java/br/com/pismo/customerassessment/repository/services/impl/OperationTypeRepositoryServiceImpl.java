package br.com.pismo.customerassessment.repository.services.impl;

import br.com.pismo.customerassessment.domain.OperationType;
import br.com.pismo.customerassessment.entity.operationtype.dto.OperationTypeDTO;
import br.com.pismo.customerassessment.repository.OperationTypeRepository;
import br.com.pismo.customerassessment.repository.services.OperationTypeRepositoryService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OperationTypeRepositoryServiceImpl implements OperationTypeRepositoryService {

    private final OperationTypeRepository operationTypeRepository;

    public OperationTypeRepositoryServiceImpl(OperationTypeRepository operationTypeRepository) {
        this.operationTypeRepository = operationTypeRepository;
    }

    @Override
    public Optional<OperationTypeDTO> getOperationTypeById(Integer operationTypeId) {
        return operationTypeRepository.findById(operationTypeId)
                .map(this::toOperationTypeDTO);
    }

    private OperationTypeDTO toOperationTypeDTO(OperationType operationType) {
        return new OperationTypeDTO(
                operationType.getId(),
                operationType.getDescription()
        );
    }

}
