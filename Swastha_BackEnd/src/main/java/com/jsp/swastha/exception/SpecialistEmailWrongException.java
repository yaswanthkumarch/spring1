package com.jsp.swastha.exception;

import lombok.Data;

@Data
public class SpecialistEmailWrongException extends RuntimeException {
	private String msg="Specialist email is wrong";

	public SpecialistEmailWrongException(String msg) {
		super();
		this.msg = msg;
	}

	public SpecialistEmailWrongException() {
		super();
	}

}
