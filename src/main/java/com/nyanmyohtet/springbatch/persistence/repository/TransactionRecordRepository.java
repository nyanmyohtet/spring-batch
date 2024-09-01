package com.nyanmyohtet.springbatch.persistence.repository;

import com.nyanmyohtet.springbatch.persistence.model.TransactionRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRecordRepository extends JpaRepository<TransactionRecord, Long> {
}
