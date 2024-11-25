package com.t1.intensive.model.entity;

import com.t1.intensive.model.enumeration.AccountStatus;
import com.t1.intensive.model.enumeration.AccountType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Objects;

import static com.t1.intensive.util.ConstantsUtil.ACCOUNT_GRAPH;

@Getter
@ToString
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "accounts")
@NamedEntityGraph(name = ACCOUNT_GRAPH, attributeNodes = @NamedAttributeNode("client"))
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    @ToString.Exclude
    private Client client;

    @Column(name = "account_type")
    @Enumerated(value = EnumType.STRING)
    private AccountType accountType;

    @Column(name = "balance", precision = 19, scale = 2)
    private BigDecimal balance;

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private AccountStatus status;

    @Column(name = "frozen_amount", precision = 19, scale = 2)
    private BigDecimal frozenAmount;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id)
                && accountType == account.accountType
                && Objects.equals(balance, account.balance)
                && Objects.equals(status, account.status)
                && Objects.equals(frozenAmount, account.frozenAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, accountType, balance, status, frozenAmount);
    }
}
