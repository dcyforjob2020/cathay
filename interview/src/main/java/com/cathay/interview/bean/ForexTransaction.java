package com.cathay.interview.bean;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ForexTransaction")
public class ForexTransaction extends BaseBean {
	private String aud2usd;
	@Id
	private String date;
	private String eur2usd;
	private String gbp2usd;
	private String nzd2usd;
	private String rmb2ntd;
	private String usd2hkd;
	private String usd2jpy;
	private String usd2ntd;
	private String usd2rmb;
	private String usd2zar;

	public String getAud2usd() {
		return aud2usd;
	}

	public String getDate() {
		return date;
	}

	public String getEur2usd() {
		return eur2usd;
	}

	public String getGbp2usd() {
		return gbp2usd;
	}

	public String getNzd2usd() {
		return nzd2usd;
	}

	public String getRmb2ntd() {
		return rmb2ntd;
	}

	public String getUsd2hkd() {
		return usd2hkd;
	}

	public String getUsd2jpy() {
		return usd2jpy;
	}

	public String getUsd2ntd() {
		return usd2ntd;
	}

	public String getUsd2rmb() {
		return usd2rmb;
	}

	public String getUsd2zar() {
		return usd2zar;
	}

	public void setAud2usd(final String aud2usd) {
		this.aud2usd = aud2usd;
	}

	public void setDate(final String date) {
		this.date = date;
	}

	public void setEur2usd(final String eur2usd) {
		this.eur2usd = eur2usd;
	}

	public void setGbp2usd(final String gbp2usd) {
		this.gbp2usd = gbp2usd;
	}

	public void setNzd2usd(final String nzd2usd) {
		this.nzd2usd = nzd2usd;
	}

	public void setRmb2ntd(final String rmb2ntd) {
		this.rmb2ntd = rmb2ntd;
	}

	public void setUsd2hkd(final String usd2hkd) {
		this.usd2hkd = usd2hkd;
	}

	public void setUsd2jpy(final String usd2jpy) {
		this.usd2jpy = usd2jpy;
	}

	public void setUsd2ntd(final String usd2ntd) {
		this.usd2ntd = usd2ntd;
	}

	public void setUsd2rmb(final String usd2rmb) {
		this.usd2rmb = usd2rmb;
	}

	public void setUsd2zar(final String usd2zar) {
		this.usd2zar = usd2zar;
	}
}