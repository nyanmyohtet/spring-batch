package com.nyanmyohtet.springbatch.config;

import com.nyanmyohtet.springbatch.component.TransactionRecordFieldSetMapper;
import com.nyanmyohtet.springbatch.persistence.model.Transaction;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class BatchConfiguration {
    @Bean
    public FlatFileItemReader<Transaction> reader() {
        return new FlatFileItemReaderBuilder<Transaction>()
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
