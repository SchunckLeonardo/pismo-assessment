package br.com.pismo.customerassessment.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Table(name = "tb_transaction")
@Entity
@Data
public class Transaction {

    @Id
    @Column(name = "id_transaction")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_account")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "id_operation_type")
    private OperationType operationType;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @Column(name = "event_date", nullable = false)
    private LocalDateTime eventDate;

}
