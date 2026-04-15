package br.com.pismo.customerassessment.entity.transaction.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Schema(name = "CreateTransactionRequest", description = "Payload used to create a transaction for an account.")
public record CreateTransactionRequestDTO(
        @Schema(description = "Identifier of the account owner", example = "1")
        @NotNull(message = "Account id is required")
        @JsonProperty("account_id")
        Integer accountId,

        @Schema(
                description = "Operation type identifier. 1=PURCHASE, 2=INSTALLMENT_PURCHASE, 3=WITHDRAWAL, 4=PAYMENT",
                allowableValues = {"1", "2", "3", "4"},
                example = "4"
        )
        @NotNull(message = "Operation type id is required")
        @JsonProperty("operation_type_id")
        Integer operationTypeId,

        @Schema(
                description = "Positive transaction amount informed by the client. The API stores it as negative for debt operations and positive for payments.",
                example = "123.45"
        )
        @NotNull(message = "Amount is required")
        @Positive(message = "Amount must be greater than zero")
        Double amount
) {
}
