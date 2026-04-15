package br.com.pismo.customerassessment.entity.transaction.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateTransactionResponseDTO(
        @JsonProperty("transaction_id")
        Integer transactionId,

        @JsonProperty("account_id")
        Integer accountId,

        @JsonProperty("operation_type_id")
        Integer operationTypeId,

        Double amount
) {
}
