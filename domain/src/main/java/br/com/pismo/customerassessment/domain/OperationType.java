package br.com.pismo.customerassessment.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Table(name = "tb_operation_type")
@Entity
@Data
public class OperationType {

    @Id
    @Column(name = "id_operation_type")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "description", nullable = false)
    private String description;

    @OneToMany(mappedBy = "operationType")
    private List<Transaction> transaction;

}
