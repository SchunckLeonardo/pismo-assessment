package br.com.pismo.customerassessment.repository.services;

import br.com.pismo.customerassessment.entity.operationtype.dto.OperationTypeDTO;

import java.util.Optional;

public interface OperationTypeRepositoryService {

    Optional<OperationTypeDTO> getOperationTypeById(Integer operationTypeId);

}
