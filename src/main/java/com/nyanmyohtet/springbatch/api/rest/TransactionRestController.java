package com.nyanmyohtet.springbatch.api.rest;

import com.nyanmyohtet.springbatch.api.rest.request.UpdateDescriptionRequest;
import com.nyanmyohtet.springbatch.persistence.model.Transaction;
import com.nyanmyohtet.springbatch.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/v1/transactions")
public class TransactionRestController {

    private static final Logger LOG = LogManager.getLogger(TransactionRestController.class);

    private final TransactionService transactionService;

    @GetMapping
    public ResponseEntity<List<Transaction>> searchTransaction(
            @RequestParam String customerId,
            @RequestParam String accountNumber,
            @RequestParam String description) {
        LOG.info("searchTransaction with params: customerId={}, accountNumber={}, description={}", customerId, accountNumber, description);
        return new ResponseEntity<>(transactionService.searchTransaction(), HttpStatus.OK);
    }

    @PatchMapping("/{transactionId}")
    public ResponseEntity<?> updateDescription(@PathVariable Long transactionId, @RequestBody UpdateDescriptionRequest updateDescriptionRequest) {
        LOG.info("updateDescription: with transactionId: {} and requestBody: {}", transactionId, updateDescriptionRequest);
        return new ResponseEntity<>(transactionService.updateDescription(transactionId, updateDescriptionRequest), HttpStatus.OK);
    }
}
