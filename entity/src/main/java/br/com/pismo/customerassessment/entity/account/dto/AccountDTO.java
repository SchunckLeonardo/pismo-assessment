package br.com.pismo.customerassessment.entity.account.dto;

public record AccountDTO(
        Integer accountId,
        String documentNumber,
        Double availableCreditLimit
) {
}
