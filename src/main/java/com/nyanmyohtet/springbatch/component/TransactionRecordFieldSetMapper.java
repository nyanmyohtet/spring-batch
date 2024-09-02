package com.nyanmyohtet.springbatch.component;

import com.nyanmyohtet.springbatch.persistence.model.Transaction;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Component
public class TransactionRecordFieldSetMapper implements FieldSetMapper<Transaction> {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Override
    public Transaction mapFieldSet(FieldSet fieldSet) {
        Transaction record = new Transaction();
        record.setAccountNumber(fieldSet.readString("accountNumber"));
        record.setTrxAmount(fieldSet.readDouble("trxAmount"));
        record.setDescription(fieldSet.readString("description"));
        record.setTrxDate(LocalDate.parse(fieldSet.readString("trxDate"), DATE_FORMATTER));
        record.setTrxTime(LocalTime.parse(fieldSet.readString("trxTime"), TIME_FORMATTER));
        record.setCustomerId(fieldSet.readString("customerId"));
        return record;
    }
}
