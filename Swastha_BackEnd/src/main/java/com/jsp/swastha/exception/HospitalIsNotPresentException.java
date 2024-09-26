package com.jsp.swastha.exception;

import lombok.Data;

@Data
public class HospitalIsNotPresentException extends RuntimeException {
	private String msg="Hospital_Id  is not present";

	public HospitalIsNotPresentException(String msg) {
		super();
		this.msg = msg;
	}

	public HospitalIsNotPresentException() {
		super();
	}

}
