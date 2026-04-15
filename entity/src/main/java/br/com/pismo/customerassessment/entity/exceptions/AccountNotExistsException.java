package br.com.pismo.customerassessment.entity.exceptions;

import lombok.Getter;

@Getter
public class AccountNotExistsException extends RuntimeException {

    private final String code = "account.not.exists";

    public AccountNotExistsException(Integer accountId) {
        super("Account with id " + accountId + " does not exist.");
    }
}
