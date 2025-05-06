package com.nyanmyohtet.springbatch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBatchApplication implements CommandLineRunner {

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private Job sampleJob;

	public static void main(String[] args) {
		SpringApplication.run(SpringBatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		JobParameters params = new JobParametersBuilder()
				.addLong("run.id", System.currentTimeMillis())
				.toJobParameters();

		jobLauncher.run(sampleJob, params);
	}
}
