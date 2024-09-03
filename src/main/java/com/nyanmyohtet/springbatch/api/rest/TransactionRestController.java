package com.nyanmyohtet.springbatch.api.rest;

import com.nyanmyohtet.springbatch.api.rest.request.UpdateDescriptionRequest;
import com.nyanmyohtet.springbatch.api.rest.response.SearchTransactionResponse;
import com.nyanmyohtet.springbatch.persistence.model.Transaction;
import com.nyanmyohtet.springbatch.service.TransactionService;
import com.nyanmyohtet.springbatch.util.AppConstants;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/v1/transactions")
public class TransactionRestController {

    private static final Logger LOG = LogManager.getLogger(TransactionRestController.class);

    private final TransactionService transactionService;

    @GetMapping
    public ResponseEntity<SearchTransactionResponse> searchTransaction(
            @RequestParam(required = false) String customerId,
            @RequestParam(required = false) String accountNumber,
            @RequestParam(required = false) String description,
            @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {
        LOG.info("searchTransaction with params: customerId={}, accountNumber={}, description={}",
                customerId, accountNumber, description);

        SearchTransactionResponse response = transactionService.searchTransaction(
                customerId, accountNumber, description, pageNo, pageSize, sortBy, sortDir);

        if (response.getContent().isEmpty()) {
            LOG.info("No transactions found for the provided criteria.");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/{transactionId}")
    public ResponseEntity<Transaction> updateDescription(
            @PathVariable Long transactionId,
            @RequestBody UpdateDescriptionRequest updateDescriptionRequest) {

        LOG.info("Attempting to update description for transaction with ID: {} and requestBody: {}", transactionId, updateDescriptionRequest);

        Transaction updatedTransaction = transactionService.updateDescription(transactionId, updateDescriptionRequest);
        LOG.info("Successfully updated description for transaction with ID: {}", transactionId);
        return ResponseEntity.ok(updatedTransaction);
    }
}
