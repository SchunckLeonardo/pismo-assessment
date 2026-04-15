package br.com.pismo.customerassessment.entity.exceptions;

import lombok.Getter;

@Getter
public class AccountAlreadyExistsException extends RuntimeException {

    private final String code = "account.already.exists";

    public AccountAlreadyExistsException(String documentNumber) {
        super("Account with document number " + documentNumber + " already exists.");
    }
}
