package br.com.pismo.customerassessment.entity.account.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "CreateAccountResponse", description = "Representation of a newly created account.")
public record CreateAccountResponseDTO(
        @Schema(description = "Generated account identifier", example = "1")
        @JsonProperty("account_id")
        Integer accountId,

        @Schema(description = "CPF document number associated with the account", example = "52998224725")
        @JsonProperty("document_number")
        String documentNumber,

        Double availableCreditLimit
) {
}
