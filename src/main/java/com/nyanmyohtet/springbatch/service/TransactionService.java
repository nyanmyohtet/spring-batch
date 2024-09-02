package com.nyanmyohtet.springbatch.service;

import com.nyanmyohtet.springbatch.api.rest.request.UpdateDescriptionRequest;
import com.nyanmyohtet.springbatch.persistence.model.Transaction;

import java.util.List;

public interface TransactionService {
    List<Transaction> searchTransaction();
    Transaction updateDescription(UpdateDescriptionRequest updateDescriptionRequest);
}
