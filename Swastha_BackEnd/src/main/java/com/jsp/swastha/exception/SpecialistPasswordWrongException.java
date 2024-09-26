package com.jsp.swastha.exception;

import lombok.Data;

@Data
public class SpecialistPasswordWrongException extends RuntimeException {
	private String msg="Specialist password is wrong";

	public SpecialistPasswordWrongException(String msg) {
		super();
		this.msg = msg;
	}

	public SpecialistPasswordWrongException() {
		super();
	}

}
