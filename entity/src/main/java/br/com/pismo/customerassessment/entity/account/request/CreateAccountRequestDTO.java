package br.com.pismo.customerassessment.entity.account.request;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

public record CreateAccountRequestDTO(
        @NotBlank(message = "Document number is required")
        @CPF(message = "Document number must be a valid CPF")
        String documentNumber
) {
}
