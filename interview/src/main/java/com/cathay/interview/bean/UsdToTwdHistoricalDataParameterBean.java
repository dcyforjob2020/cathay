package com.cathay.interview.bean;

public class UsdToTwdHistoricalDataParameterBean extends BaseBean {
	String currency;
	String endDate;
	String startDate;

	public String getCurrency() {
		return currency;
	}

	public String getEndDate() {
		return endDate;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setCurrency(final String currency) {
		this.currency = currency;
	}

	public void setEndDate(final String endDate) {
		this.endDate = endDate;
	}

	public void setStartDate(final String startDate) {
		this.startDate = startDate;
	}
}