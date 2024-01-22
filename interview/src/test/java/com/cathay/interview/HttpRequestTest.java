package com.cathay.interview;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import com.cathay.interview.bean.UsdToTwdHistoricalDataParameterBean;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void getUsdToTwdHistoricalData() throws Exception {
		final UsdToTwdHistoricalDataParameterBean parameterBean = new UsdToTwdHistoricalDataParameterBean();

		parameterBean.setCurrency("usd");
		parameterBean.setStartDate("2024/01/01");
		parameterBean.setEndDate("2024/01/01");

		final HttpHeaders headers = new HttpHeaders();
		final HttpEntity<UsdToTwdHistoricalDataParameterBean> request = new HttpEntity<>(parameterBean, headers);

		assertThat(restTemplate.postForObject("http://localhost:" + port + "/getUsdToTwdHistoricalData", request,
				String.class)).contains("0000");
	}
}
