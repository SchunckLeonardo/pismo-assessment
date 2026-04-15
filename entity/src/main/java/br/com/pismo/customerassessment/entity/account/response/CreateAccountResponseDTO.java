package br.com.pismo.customerassessment.entity.account.response;

public record CreateAccountResponseDTO(
        Integer accountId,
        String documentNumber
) {
}
