package br.com.pismo.customerassessment.entity.exceptions;

public class TransactionAmountExceedsAvailableCreditLimitException extends RuntimeException {
    public final String code = "transaction.amount.exceeds.available.credit.limit";

    public TransactionAmountExceedsAvailableCreditLimitException(Double availableCreditLimit) {
        super("Transaction amount exceeds available credit limit: " + availableCreditLimit + ".");
    }
}
