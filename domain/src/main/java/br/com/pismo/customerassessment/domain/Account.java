package br.com.pismo.customerassessment.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "tb_account")
@Data
@NoArgsConstructor
public class Account {

    @Id
    @Column(name = "id_account")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "document_number", nullable = false)
    private String documentNumber;

    @Column(name = "available_credit_limit", nullable = true)
    private Double availableCreditLimit = 5000.0;

    @OneToMany(mappedBy = "account")
    private List<Transaction> transaction;

    public Account(String documentNumber, Double availableCreditLimit) {
        this.documentNumber = documentNumber;
        this.availableCreditLimit = availableCreditLimit;
    }
}
