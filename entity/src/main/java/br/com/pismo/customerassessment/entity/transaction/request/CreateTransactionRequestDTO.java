package br.com.pismo.customerassessment.entity.transaction.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateTransactionRequestDTO(
        @NotNull(message = "Account id is required")
        @JsonProperty("account_id")
        Integer accountId,

        @NotNull(message = "Operation type id is required")
        @JsonProperty("operation_type_id")
        Integer operationTypeId,

        @NotNull(message = "Amount is required")
        @Positive(message = "Amount must be greater than zero")
        Double amount
) {
}
