package com.jsp.swastha.exception;

import lombok.Data;

@Data
public class PasswordWrongException extends RuntimeException {
	private String msg="password is wrong";

	public PasswordWrongException(String msg) {
		super();
		this.msg = msg;
	}

	public PasswordWrongException() {
		super();
	}
	

}
