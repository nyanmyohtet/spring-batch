package com.nyanmyohtet.springbatch.service.impl;

import com.nyanmyohtet.springbatch.persistence.model.Transaction;
import com.nyanmyohtet.springbatch.persistence.repository.TransactionRepository;
import com.nyanmyohtet.springbatch.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    @Override
    public List<Transaction> searchTransaction() {
        return transactionRepository.findAll();
    }
}
