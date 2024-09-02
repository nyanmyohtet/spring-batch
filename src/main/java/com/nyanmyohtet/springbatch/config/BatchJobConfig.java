package com.nyanmyohtet.springbatch.config;

import com.nyanmyohtet.springbatch.component.TransactionItemProcessor;
import com.nyanmyohtet.springbatch.persistence.model.Transaction;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchJobConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private FlatFileItemReader<Transaction> reader;

    @Autowired
    private TransactionItemProcessor processor;

    @Autowired
    private ItemWriter<Transaction> writer;

    @Bean
    public Job importTransactionJob() {
        return jobBuilderFactory.get("importTransactionJob")
                .start(step1())
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<Transaction, Transaction>chunk(10)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }
}
