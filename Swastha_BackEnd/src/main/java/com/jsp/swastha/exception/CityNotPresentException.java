package com.jsp.swastha.exception;

import lombok.Data;

@Data
public class CityNotPresentException extends RuntimeException{
	private String msg="City is not Present";

	public CityNotPresentException(String msg) {
		super();
		this.msg = msg;
	}

	public CityNotPresentException() {
		super();
	}
}
