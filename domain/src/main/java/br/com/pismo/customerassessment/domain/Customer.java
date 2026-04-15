package br.com.pismo.customerassessment.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "tb_customer")
@Data
public class Customer {

    @Id
    @Column(name = "id_customer")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "nm_customer", nullable = false)
    private String name;

    @Column(name = "document_number", nullable = false)
    private String documentNumber;

    @OneToOne
    private Account account;

}
