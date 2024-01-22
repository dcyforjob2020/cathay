package com.cathay.interview;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.cathay.interview.bean.UsdToTwdHistoricalDataParameterBean;

@SpringBootTest
@AutoConfigureMockMvc
public class TestingWebApplicationTest {
	@Autowired
	private MockMvc mockMvc;

	final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY/MM/dd");

	@Test
	public void getUsdToTwdHistoricalData() throws Exception {
		final UsdToTwdHistoricalDataParameterBean parameterBean = new UsdToTwdHistoricalDataParameterBean();

		parameterBean.setCurrency("usd");
		parameterBean.setStartDate("2024/01/01");
		parameterBean.setEndDate("2024/01/01");

		mockMvc.perform(post("/getUsdToTwdHistoricalData").contentType(MediaType.APPLICATION_JSON)
				.content(parameterBean.toString())).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.error.code").value("0000"));
	}

	@Test
	public void getUsdToTwdHistoricalDataEndDateOverAYear() throws Exception {
		final Calendar calendar = Calendar.getInstance();

		calendar.add(Calendar.YEAR, -1);
		calendar.add(Calendar.DATE, -1);

		final Date endDate = calendar.getTime();

		calendar.add(Calendar.DATE, 3);

		final UsdToTwdHistoricalDataParameterBean parameterBean = new UsdToTwdHistoricalDataParameterBean();

		parameterBean.setCurrency("usd");
		parameterBean.setStartDate(simpleDateFormat.format(calendar.getTime()));
		parameterBean.setEndDate(simpleDateFormat.format(endDate));

		mockMvc.perform(post("/getUsdToTwdHistoricalData").contentType(MediaType.APPLICATION_JSON)
				.content(parameterBean.toString())).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.error.code").value("E001"));
	}

	@Test
	public void getUsdToTwdHistoricalDataEndDateToday() throws Exception {
		final Calendar calendar = Calendar.getInstance();
		final Date endDate = calendar.getTime();

		calendar.add(Calendar.DATE, -3);

		final UsdToTwdHistoricalDataParameterBean parameterBean = new UsdToTwdHistoricalDataParameterBean();

		parameterBean.setCurrency("usd");
		parameterBean.setStartDate(simpleDateFormat.format(calendar.getTime()));
		parameterBean.setEndDate(simpleDateFormat.format(endDate));

		mockMvc.perform(post("/getUsdToTwdHistoricalData").contentType(MediaType.APPLICATION_JSON)
				.content(parameterBean.toString())).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.error.code").value("E001"));
	}

	@Test
	public void getUsdToTwdHistoricalDataStartDateOverAYear() throws Exception {
		final Calendar calendar = Calendar.getInstance();

		calendar.add(Calendar.YEAR, -1);
		calendar.add(Calendar.DATE, -1);

		final Date starDate = calendar.getTime();

		calendar.add(Calendar.DATE, 3);

		final UsdToTwdHistoricalDataParameterBean parameterBean = new UsdToTwdHistoricalDataParameterBean();

		parameterBean.setCurrency("usd");
		parameterBean.setStartDate(simpleDateFormat.format(starDate));
		parameterBean.setEndDate(simpleDateFormat.format(calendar.getTime()));

		mockMvc.perform(post("/getUsdToTwdHistoricalData").contentType(MediaType.APPLICATION_JSON)
				.content(parameterBean.toString())).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.error.code").value("E001"));
	}

	@Test
	public void getUsdToTwdHistoricalDataStartDateToday() throws Exception {
		final Calendar calendar = Calendar.getInstance();
		final Date startDate = calendar.getTime();

		calendar.add(Calendar.DATE, -3);

		final UsdToTwdHistoricalDataParameterBean parameterBean = new UsdToTwdHistoricalDataParameterBean();

		parameterBean.setCurrency("usd");
		parameterBean.setStartDate(simpleDateFormat.format(startDate));
		parameterBean.setEndDate(simpleDateFormat.format(calendar.getTime()));

		mockMvc.perform(post("/getUsdToTwdHistoricalData").contentType(MediaType.APPLICATION_JSON)
				.content(parameterBean.toString())).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.error.code").value("E001"));
	}
}