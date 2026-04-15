package br.com.pismo.customerassessment.web.controllers;

import br.com.pismo.customerassessment.entity.account.request.CreateAccountRequestDTO;
import br.com.pismo.customerassessment.entity.account.response.CreateAccountResponseDTO;
import br.com.pismo.customerassessment.entity.account.response.RetrieveAccountResponseDTO;
import br.com.pismo.customerassessment.entity.exceptions.ApiErrorDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;

public interface AccountController {

    @Operation(summary = "Create account", description = "Creates a customer account from a CPF document number.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Account created successfully",
                    content = @Content(
                            schema = @Schema(implementation = CreateAccountResponseDTO.class),
                            examples = @ExampleObject(
                                    value = """
                                            {
                                              "account_id": 1,
                                              "document_number": "52998224725"
                                            }
                                            """
                            )
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
                                              "message": "Document number must be a valid CPF"
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Account already exists",
                    content = @Content(
                            schema = @Schema(implementation = ApiErrorDTO.class),
                            examples = @ExampleObject(
                                    value = """
                                            {
                                              "code": "account.already.exists",
                                              "message": "Account with document number 52998224725 already exists."
                                            }
                                            """
                            )
                    )
            )
    })
    ResponseEntity<CreateAccountResponseDTO> createAccount(
            @RequestBody(
                    required = true,
                    description = "Account creation payload",
                    content = @Content(
                            schema = @Schema(implementation = CreateAccountRequestDTO.class),
                            examples = @ExampleObject(
                                    value = """
                                            {
                                              "documentNumber": "52998224725"
                                            }
                                            """
                            )
                    )
            )
            CreateAccountRequestDTO requestDTO
    );

    @Operation(summary = "Retrieve account", description = "Returns a customer account by its identifier.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Account found",
                    content = @Content(
                            schema = @Schema(implementation = RetrieveAccountResponseDTO.class),
                            examples = @ExampleObject(
                                    value = """
                                            {
                                              "account_id": 1,
                                              "document_number": "52998224725"
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Account not found",
                    content = @Content(
                            schema = @Schema(implementation = ApiErrorDTO.class),
                            examples = @ExampleObject(
                                    value = """
                                            {
                                              "code": "account.not.exists",
                                              "message": "Account with id 99 does not exist."
                                            }
                                            """
                            )
                    )
            )
    })
    ResponseEntity<RetrieveAccountResponseDTO> retrieveAccount(
            @Parameter(description = "Identifier of the account to retrieve", example = "1")
            Integer accountId
    );

}
