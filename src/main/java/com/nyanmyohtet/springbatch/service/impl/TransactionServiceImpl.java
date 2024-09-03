package com.nyanmyohtet.springbatch.service.impl;

import com.nyanmyohtet.springbatch.api.rest.TransactionRestController;
import com.nyanmyohtet.springbatch.api.rest.request.UpdateDescriptionRequest;
import com.nyanmyohtet.springbatch.api.rest.response.SearchTransactionResponse;
import com.nyanmyohtet.springbatch.exception.ResourceNotFoundException;
import com.nyanmyohtet.springbatch.persistence.model.Transaction;
import com.nyanmyohtet.springbatch.persistence.repository.TransactionRepository;
import com.nyanmyohtet.springbatch.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TransactionServiceImpl implements TransactionService {

    private static final Logger LOG = LogManager.getLogger(TransactionRestController.class);

    private final TransactionRepository transactionRepository;

    @Override
    public SearchTransactionResponse searchTransaction(String customerId, String accountNumber, String description,
                                                       int pageNo, int pageSize, String sortBy, String sortDir) {
        try {
            Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                    : Sort.by(sortBy).descending();

            Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

            Specification<Transaction> spec = Specification.where(null);

            if (StringUtils.hasText(customerId)) {
                spec = spec.and((root, query, cb) -> cb.equal(root.get("customerId"), customerId));
            }
            if (StringUtils.hasText(accountNumber)) {
                spec = spec.and((root, query, cb) -> cb.equal(root.get("accountNumber"), accountNumber));
            }
            if (StringUtils.hasText(description)) {
                spec = spec.and((root, query, cb) -> cb.like(root.get("description"), "%" + description + "%"));
            }

            Page<Transaction> transactionsPage = transactionRepository.findAll(spec, pageable);
            List<Transaction> transactions = transactionsPage.getContent();

            return new SearchTransactionResponse(
                    transactions,
                    transactionsPage.getNumber(),
                    transactionsPage.getSize(),
                    transactionsPage.getTotalElements(),
                    transactionsPage.getTotalPages(),
                    transactionsPage.isLast()
            );
        } catch (Exception e) {
            LOG.error("Error occurred while searching for transactions", e);
            throw new RuntimeException("Failed to search transactions", e);
        }
    }

    @Override
    @Transactional
    public Transaction updateDescription(Long transactionId, UpdateDescriptionRequest updateDescriptionRequest) {
        LOG.info("Attempting to update description for transaction with ID: {}", transactionId);

        Optional<Transaction> transactionOptional = transactionRepository.findById(transactionId);
        if (transactionOptional.isEmpty()) {
            LOG.error("Transaction not found with ID: {}", transactionId);
            throw new ResourceNotFoundException("Transaction not found with id: " + transactionId);
        }

        Transaction transaction = transactionOptional.get();
        transaction.setDescription(updateDescriptionRequest.getDescription());

        try {
            Transaction updatedTransaction = transactionRepository.saveAndFlush(transaction);
            LOG.info("Successfully updated description for transaction with ID: {}", transactionId);
            return updatedTransaction;
        } catch (OptimisticLockException ex) {
            LOG.error("Update failed due to concurrent modification for transaction with ID: {}", transactionId, ex);
            throw new ConcurrentModificationException("Update failed due to concurrent modification", ex);
        } catch (Exception ex) {
            LOG.error("An unexpected error occurred while updating the transaction with ID: {}", transactionId, ex);
            throw new RuntimeException("An unexpected error occurred", ex);
        }
    }
}
