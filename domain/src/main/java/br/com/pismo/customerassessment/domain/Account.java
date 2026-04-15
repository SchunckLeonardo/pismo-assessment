package br.com.pismo.customerassessment.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_account")
@Data
public class Account {

    @Id
    @Column(name = "id_account")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "document_number", nullable = false)
    private String documentNumber;

    @OneToMany(mappedBy = "account")
    private List<Transaction> transaction;

}
