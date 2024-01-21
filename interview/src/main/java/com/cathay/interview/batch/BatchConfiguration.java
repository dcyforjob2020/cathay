package com.cathay.interview.batch;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.data.builder.MongoItemWriterBuilder;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

import com.cathay.interview.bean.ForexTransaction;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
	private static final Logger log = LoggerFactory.getLogger(BatchConfiguration.class);

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public MongoTemplate mongoTemplate;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	JobLauncher jobLauncher;

	@Bean
	public Job importForexTransactionJob(final JobCompletionNotificationListener listener, final Step step1) {
		return jobBuilderFactory.get("importForexTransactionJob").incrementer(new RunIdIncrementer()).listener(listener)
				.flow(step1).end().build();
	}

	@Scheduled(cron = "0 0 18 * * *")
	public void perform() throws Exception {
		log.info("Job Started at :" + new Date());

		final JobParameters param = new JobParametersBuilder()
				.addString("JobID", String.valueOf(System.currentTimeMillis())).toJobParameters();

		final JobExecution execution = jobLauncher
				.run(importForexTransactionJob(new JobCompletionNotificationListener(mongoTemplate), step1()), param);

		log.info("Job finished with status :" + execution.getStatus());
	}

	@Bean
	public ForexTransactionItemProcessor processor() {
		return new ForexTransactionItemProcessor();
	}

	@Bean
	public JsonItemReader<ForexTransaction> reader() {
		final ResponseEntity<String> resEntity = new RestTemplate()
				.getForEntity("https://openapi.taifex.com.tw/v1/DailyForeignExchangeRates", String.class);

		return new JsonItemReaderBuilder<ForexTransaction>()
				.jsonObjectReader(new JacksonJsonObjectReader<>(ForexTransaction.class))
				.resource(new ByteArrayResource(processData(resEntity.getBody()).getBytes()))
				.name("transactionJsonItemReader").build();
	}

	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1").<ForexTransaction, ForexTransaction>chunk(10).reader(reader())
				.processor(processor()).writer(writer()).build();
	}

	@Bean
	public MongoItemWriter<ForexTransaction> writer() {
		return new MongoItemWriterBuilder<ForexTransaction>().template(mongoTemplate).collection("ForexTransaction")
				.build();
	}

	private String processData(final String dataString) {
		return dataString.toLowerCase().replace("/", "2");
	}
}