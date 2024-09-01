package com.nyanmyohtet.springbatch.component;

import com.nyanmyohtet.springbatch.persistence.model.TransactionRecord;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Component
public class TransactionItemProcessor implements ItemProcessor<TransactionRecord, TransactionRecord> {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Override
    public TransactionRecord process(TransactionRecord item) throws Exception {
        item.setTrxDate(LocalDate.parse(item.getTrxDate().toString(), DATE_FORMATTER));
        item.setTrxTime(LocalTime.parse(item.getTrxTime().toString(), TIME_FORMATTER));
        return item;
    }
}
