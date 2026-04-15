package br.com.pismo.customerassessment.entity.account.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record RetrieveAccountResponseDTO(
        @JsonProperty("account_id")
        Integer accountId,

        @JsonProperty("document_number")
        String documentNumber
) {
}
