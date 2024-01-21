package com.cathay.interview.bean;

public class CurrencyToTwdHistoricalData extends BaseBean {
	String date;
	String usd;

	public CurrencyToTwdHistoricalData(final String date, final String usd) {
		this.date = date;
		this.usd = usd;
	}

	public String getDate() {
		return date;
	}

	public String getUsd() {
		return usd;
	}

	public void setDate(final String date) {
		this.date = date;
	}

	public void setUsd(final String usd) {
		this.usd = usd;
	}
}