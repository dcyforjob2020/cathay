package com.cathay.interview;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class CallApiTests {
	private final RestTemplate restTemplate = new RestTemplate();

	@Test
	public void testDailyForeignExchangeRates() {
		ResponseEntity<String> resEntity = null;
		try {
			resEntity = restTemplate.getForEntity("https://openapi.taifex.com.tw/v1/DailyForeignExchangeRates",
					String.class);
		} catch (final Exception e) {
			e.printStackTrace();

			fail("call api error");
		}

		assertThat(resEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
}
