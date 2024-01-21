package com.cathay.interview.bean;

import java.util.List;

public class UsdToTwdHistoricalDataViewBean extends ViewBean {
	List<CurrencyToTwdHistoricalData> currency;

	public UsdToTwdHistoricalDataViewBean(final List<CurrencyToTwdHistoricalData> currency) {
		this.currency = currency;
	}

	public List<CurrencyToTwdHistoricalData> getCurrency() {
		return currency;
	}

	public void setCurrency(final List<CurrencyToTwdHistoricalData> currency) {
		this.currency = currency;
	}
}