package com.jsp.swastha.exception;

import lombok.Data;

@Data
public class SpecialistTableEmptyException extends RuntimeException {
	private String msg="Specialist Table is Empty";

	public SpecialistTableEmptyException(String msg) {
		super();
		this.msg = msg;
	}

	public SpecialistTableEmptyException() {
		super();
	}


}
