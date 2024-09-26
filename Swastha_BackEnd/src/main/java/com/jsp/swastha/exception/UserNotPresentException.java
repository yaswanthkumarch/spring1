package com.jsp.swastha.exception;

import lombok.Data;

@Data
public class UserNotPresentException extends RuntimeException {
	private String msg="User is not present";

	public UserNotPresentException(String msg) {
		super();
		this.msg = msg;
	}

	public UserNotPresentException() {
		super();
	}

}
