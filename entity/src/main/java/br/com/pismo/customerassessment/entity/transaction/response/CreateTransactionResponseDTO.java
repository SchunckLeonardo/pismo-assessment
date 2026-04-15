package br.com.pismo.customerassessment.entity.transaction.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "CreateTransactionResponse", description = "Representation of a created transaction.")
public record CreateTransactionResponseDTO(
        @Schema(description = "Generated transaction identifier", example = "1")
        @JsonProperty("transaction_id")
        Integer transactionId,

        @Schema(description = "Identifier of the related account", example = "1")
        @JsonProperty("account_id")
        Integer accountId,

        @Schema(description = "Operation type identifier", example = "4")
        @JsonProperty("operation_type_id")
        Integer operationTypeId,

        @Schema(
                description = "Stored transaction amount. Payments are positive and debt operations are negative.",
                example = "123.45"
        )
        Double amount
) {
}
