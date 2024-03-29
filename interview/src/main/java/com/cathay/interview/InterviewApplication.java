package com.cathay.interview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class InterviewApplication {
	public static void main(final String[] args) {
		SpringApplication.run(InterviewApplication.class, args);
	}
}