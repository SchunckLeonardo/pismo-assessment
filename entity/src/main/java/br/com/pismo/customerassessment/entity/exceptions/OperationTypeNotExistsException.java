package br.com.pismo.customerassessment.entity.exceptions;

import lombok.Getter;

@Getter
public class OperationTypeNotExistsException extends RuntimeException {

    private final String code = "operation.type.not.exists";

    public OperationTypeNotExistsException(Integer operationTypeId) {
        super("Operation type with id " + operationTypeId + " does not exist.");
    }
}
