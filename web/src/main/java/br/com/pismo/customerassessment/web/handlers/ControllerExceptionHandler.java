package br.com.pismo.customerassessment.web.handlers;

import br.com.pismo.customerassessment.entity.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(AccountAlreadyExistsException.class)
    public ApiErrorDTO handleAccountAlreadyExistsException(AccountAlreadyExistsException ex) {
        return new ApiErrorDTO(ex.getCode(), ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiErrorDTO handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            errors.add(error.getDefaultMessage());
        });
        return new ApiErrorDTO("method.argument.not.valid", errors.getFirst());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(AccountNotExistsException.class)
    public ApiErrorDTO handleAccountNotExistsException(AccountNotExistsException ex) {
        return new ApiErrorDTO(ex.getCode(), ex.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(OperationTypeNotExistsException.class)
    public ApiErrorDTO handleOperationTypeNotExistsException(OperationTypeNotExistsException ex) {
        return new ApiErrorDTO(ex.getCode(), ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ApiErrorDTO handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        return new ApiErrorDTO("method.argument.type.mismatch", ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(TransactionAmountExceedsAvailableCreditLimitException.class)
    public ApiErrorDTO handleTransactionAmountExceedsAvailableCreditLimitException(TransactionAmountExceedsAvailableCreditLimitException ex) {
        return new ApiErrorDTO(ex.code, ex.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public ApiErrorDTO handleRuntimeException(RuntimeException ex) {
        return new ApiErrorDTO("internal.server.error", ex.getMessage());
    }

}
