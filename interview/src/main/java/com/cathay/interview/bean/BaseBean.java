package com.cathay.interview.bean;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BaseBean {
	@Override
	public String toString() {
		final ObjectMapper objectMapper = new ObjectMapper();

		try {
			return objectMapper.writeValueAsString(this);
		} catch (final JsonProcessingException e) {
			e.printStackTrace();
		}

		return super.toString();
	}
}