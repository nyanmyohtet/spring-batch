package com.nyanmyohtet.springbatch.service.impl;

import com.nyanmyohtet.springbatch.api.rest.request.UpdateDescriptionRequest;
import com.nyanmyohtet.springbatch.persistence.model.Transaction;
import com.nyanmyohtet.springbatch.persistence.repository.TransactionRepository;
import com.nyanmyohtet.springbatch.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    @Override
    public List<Transaction> searchTransaction() {
        return transactionRepository.findAll();
    }

    @Override
    @Transactional
    public Transaction updateDescription(UpdateDescriptionRequest updateDescriptionRequest) {
        Optional<Transaction> transactionOptional = transactionRepository.findById(updateDescriptionRequest.getId());
        if (transactionOptional.isEmpty()) {
            throw new RuntimeException("Transaction not found with id: " + updateDescriptionRequest.getId());
        }

        Transaction transaction = transactionOptional.get();
        transaction.setDescription(updateDescriptionRequest.getDescription());

        try {
            transactionRepository.saveAndFlush(transaction);
        } catch (OptimisticLockException ex) {
            throw new RuntimeException("Update failed due to concurrent modification", ex);
        }

        return transaction;
    }
}
