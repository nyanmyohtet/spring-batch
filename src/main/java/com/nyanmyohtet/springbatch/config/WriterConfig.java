package com.nyanmyohtet.springbatch.config;

import com.nyanmyohtet.springbatch.persistence.model.TransactionRecord;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Configuration
public class WriterConfig {

    @Bean
    public ItemWriter<TransactionRecord> writer(JpaRepository<TransactionRecord, Long> repository) {
        return new ItemWriter<>() {
            @Override
            public void write(List<? extends TransactionRecord> items) {
                items.forEach(System.out::println);
                repository.saveAll(items);
            }
        };
    }
}
