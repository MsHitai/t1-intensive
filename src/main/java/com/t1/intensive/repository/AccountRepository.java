package com.t1.intensive.repository;

import com.t1.intensive.model.entity.Account;
import com.t1.intensive.model.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findAllByClient(Client client);

    int deleteByIdAndClient(Long id, Client client);
}
