package com.nyanmyohtet.springbatch.persistence.repository;

import com.nyanmyohtet.springbatch.persistence.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
