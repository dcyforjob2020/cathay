package com.cathay.interview.bean;

public class ViewBean extends BaseBean {
	Error error = new Error();

	public ViewBean() {
	}

	public ViewBean(final Error error) {
		this.error = error;
	}

	public Error getError() {
		return error;
	}

	public void setError(final Error error) {
		this.error = error;
	}
}