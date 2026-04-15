package br.com.pismo.customerassessment.service;

import br.com.pismo.customerassessment.entity.account.request.CreateAccountRequestDTO;
import br.com.pismo.customerassessment.entity.account.response.CreateAccountResponseDTO;

public interface AccountService {

    public CreateAccountResponseDTO createAccount(CreateAccountRequestDTO requestDTO);

}
