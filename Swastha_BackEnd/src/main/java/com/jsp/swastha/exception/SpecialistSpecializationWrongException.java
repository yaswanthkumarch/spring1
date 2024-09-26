package com.jsp.swastha.exception;

import lombok.Data;

@Data
public class SpecialistSpecializationWrongException extends RuntimeException {
	private String msg = "Specialist Specialization is wrong";

	public SpecialistSpecializationWrongException(String msg) {
		super();
		this.msg = msg;
	}

	public SpecialistSpecializationWrongException() {
		super();
	}
}
