package br.com.pismo.customerassessment.entity.transaction.dto;

public record TransactionDTO(
        Integer transactionId,
        Integer accountId,
        Integer operationTypeId,
        Double amount
) {
}
