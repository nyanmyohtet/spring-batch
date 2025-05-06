package com.nyanmyohtet.springbatch.component;

import com.nyanmyohtet.springbatch.persistence.model.Transaction;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

@Component
public class TransactionItemProcessor implements ItemProcessor<Transaction, Transaction> {
    @Override
    public Transaction process(Transaction item) throws Exception {
        System.out.println();
        System.out.println(item.getAccountNumber() + ": " + LocalTime.now());
        TimeUnit.SECONDS.sleep(1);
        return item;
    }
}
