package com.t1.intensive.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Objects;

@Getter
@ToString
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "blocked_for")
    private Boolean blockedFor;

    @Column(name = "blocked_whom")
    private String blockedWhom;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
    @ToString.Exclude
    private List<Account> accounts;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id)
                && Objects.equals(firstName, client.firstName)
                && Objects.equals(lastName, client.lastName)
                && Objects.equals(middleName, client.middleName)
                && Objects.equals(blockedFor, client.blockedFor)
                && Objects.equals(blockedWhom, client.blockedWhom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, middleName, blockedFor, blockedWhom);
    }
}
