package com.nyanmyohtet.springbatch.service;

import com.nyanmyohtet.springbatch.api.rest.request.UpdateDescriptionRequest;
import com.nyanmyohtet.springbatch.api.rest.response.SearchTransactionResponse;
import com.nyanmyohtet.springbatch.persistence.model.Transaction;

public interface TransactionService {
    SearchTransactionResponse searchTransaction(String customerId, String accountNumber, String description,
                                                int pageNo, int pageSize, String sortBy, String sortDir);
    Transaction updateDescription(Long transactionId, UpdateDescriptionRequest updateDescriptionRequest);
}
