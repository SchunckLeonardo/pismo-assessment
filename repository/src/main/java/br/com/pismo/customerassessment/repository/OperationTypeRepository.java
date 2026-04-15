package br.com.pismo.customerassessment.repository;

import br.com.pismo.customerassessment.domain.OperationType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationTypeRepository extends JpaRepository<OperationType, Integer> {
}
