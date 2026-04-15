package br.com.pismo.customerassessment.repository;

import br.com.pismo.customerassessment.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
}
