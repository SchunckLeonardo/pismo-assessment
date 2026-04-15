package br.com.pismo.customerassessment.web.controllers;

import br.com.pismo.customerassessment.entity.exceptions.ApiErrorDTO;
import br.com.pismo.customerassessment.entity.transaction.request.CreateTransactionRequestDTO;
import br.com.pismo.customerassessment.entity.transaction.response.CreateTransactionResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;

public interface TransactionController {

    @Operation(
            summary = "Create transaction",
            description = "Creates a transaction for an account. Operation types 1, 2 and 3 are stored as negative amounts, while operation type 4 is stored as positive."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Transaction created successfully",
                    content = @Content(
                            schema = @Schema(implementation = CreateTransactionResponseDTO.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Payment",
                                            value = """
                                                    {
                                                      "transaction_id": 1,
                                                      "account_id": 1,
                                                      "operation_type_id": 4,
                                                      "amount": 123.45
                                                    }
                                                    """
                                    ),
                                    @ExampleObject(
                                            name = "Purchase",
                                            value = """
                                                    {
                                                      "transaction_id": 2,
                                                      "account_id": 1,
                                                      "operation_type_id": 1,
                                                      "amount": -123.45
                                                    }
                                                    """
                                    )
                            }
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request body",
                    content = @Content(
                            schema = @Schema(implementation = ApiErrorDTO.class),
                            examples = @ExampleObject(
                                    value = """
                                            {
                                              "code": "method.argument.not.valid",
                                              "message": "Amount must be greater than zero"
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Account or operation type not found",
                    content = @Content(
                            schema = @Schema(implementation = ApiErrorDTO.class),
                            examples = {
                                    @ExampleObject(
                                            name = "AccountNotFound",
                                            value = """
                                                    {
                                                      "code": "account.not.exists",
                                                      "message": "Account with id 99 does not exist."
                                                    }
                                                    """
                                    ),
                                    @ExampleObject(
                                            name = "OperationTypeNotFound",
                                            value = """
                                                    {
                                                      "code": "operation.type.not.exists",
                                                      "message": "Operation type with id 9 does not exist."
                                                    }
                                                    """
                                    )
                            }
                    )
            )
    })
    ResponseEntity<CreateTransactionResponseDTO> createTransaction(
            @RequestBody(
                    required = true,
                    description = "Transaction creation payload",
                    content = @Content(
                            schema = @Schema(implementation = CreateTransactionRequestDTO.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Payment",
                                            value = """
                                                    {
                                                      "account_id": 1,
                                                      "operation_type_id": 4,
                                                      "amount": 123.45
                                                    }
                                                    """
                                    ),
                                    @ExampleObject(
                                            name = "Purchase",
                                            value = """
                                                    {
                                                      "account_id": 1,
                                                      "operation_type_id": 1,
                                                      "amount": 123.45
                                                    }
                                                    """
                                    )
                            }
                    )
            )
            CreateTransactionRequestDTO requestDTO
    );

}
