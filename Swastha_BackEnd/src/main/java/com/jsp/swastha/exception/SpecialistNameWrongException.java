package com.jsp.swastha.exception;

import lombok.Data;

@Data
public class SpecialistNameWrongException extends RuntimeException {
	private String msg="Specialist Name is wrong";

	public SpecialistNameWrongException(String msg) {
		super();
		this.msg = msg;
	}

	public SpecialistNameWrongException() {
		super();
	}

}
