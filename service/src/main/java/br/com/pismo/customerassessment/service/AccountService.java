package br.com.pismo.customerassessment.service;

import br.com.pismo.customerassessment.entity.account.request.CreateAccountRequestDTO;
import br.com.pismo.customerassessment.entity.account.response.CreateAccountResponseDTO;
import br.com.pismo.customerassessment.entity.account.response.RetrieveAccountResponseDTO;

public interface AccountService {

    public CreateAccountResponseDTO createAccount(CreateAccountRequestDTO requestDTO);

    public RetrieveAccountResponseDTO retrieveAccount(Integer accountId);

}
