package com.cathay.interview;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import com.cathay.interview.bean.CurrencyToTwdHistoricalData;
import com.cathay.interview.bean.ForexTransaction;
import com.cathay.interview.bean.UsdToTwdHistoricalDataParameterBean;
import com.cathay.interview.bean.UsdToTwdHistoricalDataViewBean;

@Service
public class CurrencyService {
	@Autowired
	private MongoTemplate mongoTemplate;

	public UsdToTwdHistoricalDataViewBean getUsdToTwdHistoricalData(
			final UsdToTwdHistoricalDataParameterBean parameterBean) {
		final String startDateString = parameterBean.getStartDate().replace("/", "");
		final String endDateString = parameterBean.getEndDate().replace("/", "");

		final List<ForexTransaction> result = mongoTemplate.query(ForexTransaction.class)
				.matching(Criteria.where("date").gte(startDateString).lte(endDateString)).all();

		final ArrayList<CurrencyToTwdHistoricalData> currencyToTwdHistoricalDataList = new ArrayList<>();

		for (final ForexTransaction forexTransaction : result) {
			currencyToTwdHistoricalDataList
					.add(new CurrencyToTwdHistoricalData(forexTransaction.getDate(), forexTransaction.getUsd2ntd()));
		}

		return new UsdToTwdHistoricalDataViewBean(currencyToTwdHistoricalDataList);
	}
}