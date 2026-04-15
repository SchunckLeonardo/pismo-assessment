package br.com.pismo.customerassessment.repository;

import br.com.pismo.customerassessment.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    Optional<Account> findByDocumentNumber(String documentNumber);
}
