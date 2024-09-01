package com.nyanmyohtet.springbatch.config;

import com.nyanmyohtet.springbatch.component.TransactionRecordFieldSetMapper;
import com.nyanmyohtet.springbatch.persistence.model.TransactionRecord;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class BatchConfiguration {
    @Bean
    public FlatFileItemReader<TransactionRecord> reader() {
        return new FlatFileItemReaderBuilder<TransactionRecord>()
                .name("transactionItemReader")
                .resource(new ClassPathResource("transactions.txt"))
                .linesToSkip(1) // skip header
                .delimited()
                .delimiter("|")
                .names("accountNumber", "trxAmount", "description", "trxDate", "trxTime", "customerId")
                .fieldSetMapper(fieldSetMapper())
                .build();
    }

    @Bean
    public TransactionRecordFieldSetMapper fieldSetMapper() {
        return new TransactionRecordFieldSetMapper();
    }
}
