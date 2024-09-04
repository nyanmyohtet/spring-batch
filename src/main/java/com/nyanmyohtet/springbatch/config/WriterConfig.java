package com.nyanmyohtet.springbatch.config;

import com.nyanmyohtet.springbatch.persistence.model.Transaction;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Configuration
public class WriterConfig {

    @Bean
    public ItemWriter<Transaction> writer(JpaRepository<Transaction, Long> repository) {
        return items -> {
            items.forEach(System.out::println);
            repository.saveAll(items);
        };
    }
}
