package com.jsp.swastha.exception;

import lombok.Data;

@Data
public class IdNotPresentException extends RuntimeException {
	private String msg="ID is not present";

	public IdNotPresentException(String msg) {
		super();
		this.msg = msg;
	}

	public IdNotPresentException() {
		super();
	}
}
