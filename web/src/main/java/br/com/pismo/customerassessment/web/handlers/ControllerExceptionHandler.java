package br.com.pismo.customerassessment.web.handlers;

import br.com.pismo.customerassessment.entity.exceptions.AccountAlreadyExistsException;
import br.com.pismo.customerassessment.entity.exceptions.ApiErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(AccountAlreadyExistsException.class)
    public ApiErrorDTO handleAccountAlreadyExistsException(AccountAlreadyExistsException e) {
        return new ApiErrorDTO(e.getMessage(), "account.already.exists");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiErrorDTO handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<String> errors = new ArrayList<>();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            errors.add(error.getDefaultMessage());
        });
        return new ApiErrorDTO(errors.getFirst(), "method.argument.not.valid");
    }

}
