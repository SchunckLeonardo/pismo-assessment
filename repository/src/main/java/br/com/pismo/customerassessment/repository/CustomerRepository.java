package br.com.pismo.customerassessment.repository;

import br.com.pismo.customerassessment.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {

}
