package com.jsp.swastha.exception;

import lombok.Data;

@Data
public class EmailWrongException extends RuntimeException {
	private String msg="email is wrong";

	public EmailWrongException(String msg) {
		super();
		this.msg = msg;
	}

	public EmailWrongException() {
		super();
	}
	
	

}
