package com.nyanmyohtet.springbatch.persistence.repository;

import com.nyanmyohtet.springbatch.persistence.model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Page<Transaction> findAll(Specification<Transaction> spec, Pageable pageable);
}
