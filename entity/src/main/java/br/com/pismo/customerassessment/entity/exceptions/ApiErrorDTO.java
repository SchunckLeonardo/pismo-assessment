package br.com.pismo.customerassessment.entity.exceptions;

public record ApiErrorDTO(
        String message,
        String code
) {
}
