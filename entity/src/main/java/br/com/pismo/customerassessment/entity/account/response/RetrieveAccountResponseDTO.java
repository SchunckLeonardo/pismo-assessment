package br.com.pismo.customerassessment.entity.account.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "RetrieveAccountResponse", description = "Representation of an existing account.")
public record RetrieveAccountResponseDTO(
        @Schema(description = "Account identifier", example = "1")
        @JsonProperty("account_id")
        Integer accountId,

        @Schema(description = "CPF document number associated with the account", example = "52998224725")
        @JsonProperty("document_number")
        String documentNumber,

        Double availableCreditLimit
) {
}
