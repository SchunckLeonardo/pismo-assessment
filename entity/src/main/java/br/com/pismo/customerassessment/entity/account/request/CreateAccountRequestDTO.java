package br.com.pismo.customerassessment.entity.account.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.br.CPF;

@Schema(name = "CreateAccountRequest", description = "Payload used to create a customer account.")
public record CreateAccountRequestDTO(
        @Schema(description = "CPF document number for the account owner", example = "52998224725")
        @NotBlank(message = "Document number is required")
        @CPF(message = "Document number must be a valid CPF")
        String documentNumber,

        @NotNull(message = "Available Credit limit is required")
        @Positive(message = "Available Credit limit needs to be a positive number")
        Double availableCreditLimit
) {
}
