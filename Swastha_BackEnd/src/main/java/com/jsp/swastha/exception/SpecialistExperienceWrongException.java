package com.jsp.swastha.exception;

import lombok.Data;

@Data
public class SpecialistExperienceWrongException extends RuntimeException {
	private String msg="Specialist Experience is wrong";

	public SpecialistExperienceWrongException(String msg) {
		super();
		this.msg = msg;
	}

	public SpecialistExperienceWrongException() {
		super();
	}

}
