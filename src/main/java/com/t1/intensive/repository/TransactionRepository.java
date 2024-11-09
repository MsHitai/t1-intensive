package com.t1.intensive.repository;

import com.t1.intensive.model.entity.Account;
import com.t1.intensive.model.entity.Transaction;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.t1.intensive.util.ConstantsUtil.TRANSACTION_GRAPH;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @EntityGraph(TRANSACTION_GRAPH)
    List<Transaction> findAllByAccount(Account account);

    int deleteByIdAndAccount(Long id, Account account);

    @Query(value = "select t from Transaction t join fetch t.account where t.id = :id")
    Optional<Transaction> findByIdWithAccount(Long id);
}
