package com.jsp.swastha.exception;

import lombok.Data;

@Data
public class SpecialistNotPresentException extends RuntimeException {
	private String msg="Specialist is not present";

	public SpecialistNotPresentException(String msg) {
		super();
		this.msg = msg;
	}

	public SpecialistNotPresentException() {
		super();
	}

}
