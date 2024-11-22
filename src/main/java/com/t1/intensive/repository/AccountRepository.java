package com.t1.intensive.repository;

import com.t1.intensive.model.entity.Account;
import com.t1.intensive.model.entity.Client;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.t1.intensive.util.ConstantsUtil.ACCOUNT_GRAPH;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    @EntityGraph(ACCOUNT_GRAPH)
    List<Account> findAllByClient(Client client);

    @Query(value = "select a from Account a join fetch a.client where a.id = :id")
    Optional<Account> findByIdWithClient(Long id);

    int deleteByIdAndClient(Long id, Client client);
}
