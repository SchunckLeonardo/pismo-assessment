package br.com.pismo.customerassessment.entity.exceptions;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "ApiError", description = "Standard error payload returned by the API.")
public record ApiErrorDTO(
        @Schema(description = "Stable application error code", example = "account.not.exists")
        String code,

        @Schema(description = "Human-readable error message", example = "Account with id 99 does not exist.")
        String message
) {
}
