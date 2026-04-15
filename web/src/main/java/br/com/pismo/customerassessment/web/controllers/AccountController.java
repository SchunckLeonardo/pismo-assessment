package br.com.pismo.customerassessment.web.controllers;

import br.com.pismo.customerassessment.entity.account.request.CreateAccountRequestDTO;
import br.com.pismo.customerassessment.entity.account.response.CreateAccountResponseDTO;
import org.springframework.http.ResponseEntity;

public interface AccountController {

    ResponseEntity<CreateAccountResponseDTO> createAccount(CreateAccountRequestDTO requestDTO);

}
