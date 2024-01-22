package com.cathay.interview;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cathay.interview.bean.Error;
import com.cathay.interview.bean.UsdToTwdHistoricalDataParameterBean;
import com.cathay.interview.bean.ViewBean;

@RestController
public class Controller {
	@Autowired
	CurrencyService currencyService;

	@PostMapping("/getUsdToTwdHistoricalData")
	public ViewBean getUsdToTwdHistoricalData(@RequestBody final UsdToTwdHistoricalDataParameterBean parameterBean) {
		if (!validateDateRangeWithinOneYearAgoToYesterday(parameterBean.getStartDate())
				|| !validateDateRangeWithinOneYearAgoToYesterday(parameterBean.getEndDate())) {
			return new ViewBean(new Error("E001", "日期區間不符"));
		}

		return currencyService.getUsdToTwdHistoricalData(parameterBean);
	}

	private Calendar getStartTimeFromToday() {
		final Calendar calendar = Calendar.getInstance();

		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		return calendar;
	}

	private boolean validateDateRangeWithinOneYearAgoToYesterday(final String dateString) {
		final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date;

		try {
			date = simpleDateFormat.parse(dateString);
		} catch (final ParseException e) {
			e.printStackTrace();
			return false;
		}

		final Calendar oneYearAgoStartTimeCalendar = getStartTimeFromToday();
		final Calendar yesterdayEndTimeCalendar = getStartTimeFromToday();

		oneYearAgoStartTimeCalendar.add(Calendar.YEAR, -1);
		yesterdayEndTimeCalendar.add(Calendar.MILLISECOND, -1);

		if (date.before(oneYearAgoStartTimeCalendar.getTime()) || date.after(yesterdayEndTimeCalendar.getTime())) {
			return false;
		}

		return true;
	}
}