package com.jsp.swastha.exception;

import lombok.Data;

@Data
public class BloodGroupNotPresentException extends RuntimeException {
	private String msg="Blood Group is not present";

	public BloodGroupNotPresentException(String msg) {
		super();
		this.msg = msg;
	}

	public BloodGroupNotPresentException() {
		super();
	}

}
