package com.nyanmyohtet.springbatch.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class CsvFileReader {

    private final Logger logger = LoggerFactory.getLogger(CsvFileReader.class);

    public void readFile() {
        String delimiter = "\\|";

        try {
            ClassPathResource resource = new ClassPathResource("transactions.txt");
            InputStream inputStream = resource.getInputStream();

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] values = line.split(delimiter);
                    for (String value : values) {
                        System.out.print(value + " | ");
                    }
                    System.out.println();
                }
            }

        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}
