package com.cathay.interview.batch;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@SpringBatchTest
class BatchTests {
	@Autowired
	private JobLauncherTestUtils jobLauncherTestUtils;

	@Test
	public void testJob(@Autowired final Job job) throws Exception {
		jobLauncherTestUtils.setJob(job);
		final JobExecution jobExecution = jobLauncherTestUtils.launchJob();

		Assert.assertEquals("COMPLETED", jobExecution.getExitStatus().getExitCode());
	}
}