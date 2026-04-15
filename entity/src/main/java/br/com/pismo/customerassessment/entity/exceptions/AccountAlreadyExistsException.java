package br.com.pismo.customerassessment.entity.exceptions;

public class AccountAlreadyExistsException extends RuntimeException {

    public AccountAlreadyExistsException(String documentNumber) {
        super("Account with document number " + documentNumber + " already exists.");
    }
}
