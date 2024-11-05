package com.t1.intensive.repository;

import com.t1.intensive.model.entity.Account;
import com.t1.intensive.model.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findAllByAccount(Account account);

    int deleteByIdAndAccount(Long id, Account account);
}
