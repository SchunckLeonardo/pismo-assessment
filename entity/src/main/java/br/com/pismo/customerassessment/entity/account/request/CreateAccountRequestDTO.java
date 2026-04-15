package br.com.pismo.customerassessment.entity.account.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

@Schema(name = "CreateAccountRequest", description = "Payload used to create a customer account.")
public record CreateAccountRequestDTO(
        @Schema(description = "CPF document number for the account owner", example = "52998224725")
        @NotBlank(message = "Document number is required")
        @CPF(message = "Document number must be a valid CPF")
        String documentNumber
) {
}
