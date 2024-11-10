package com.t1.intensive.model.entity;

import com.t1.intensive.model.enumeration.TransactionStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

import static com.t1.intensive.util.ConstantsUtil.TRANSACTION_GRAPH;

@Getter
@ToString
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transactions")
@NamedEntityGraph(name = TRANSACTION_GRAPH, attributeNodes = @NamedAttributeNode("account"))
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    @ToString.Exclude
    private Account account;

    @Column(name = "amount", precision = 19, scale = 2)
    private BigDecimal amount;

    @Column(name = "transaction_time")
    private LocalDateTime transactionTime;

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private TransactionStatus status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(id, that.id)
                && Objects.equals(amount, that.amount)
                && Objects.equals(transactionTime, that.transactionTime)
                && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, transactionTime, status);
    }
}
