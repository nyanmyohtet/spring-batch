package com.nyanmyohtet.springbatch.component;

import com.nyanmyohtet.springbatch.persistence.model.Transaction;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class TransactionItemProcessor implements ItemProcessor<Transaction, Transaction> {
    @Override
    public Transaction process(Transaction item) throws Exception {
        return item;
    }
}
