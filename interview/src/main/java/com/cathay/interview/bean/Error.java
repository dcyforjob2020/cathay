package com.cathay.interview.bean;

public class Error extends BaseBean {
	String code = "0000";
	String message = "成功";

	public Error() {
	}

	public Error(final String code, final String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public void setCode(final String code) {
		this.code = code;
	}

	public void setMessage(final String message) {
		this.message = message;
	}
}