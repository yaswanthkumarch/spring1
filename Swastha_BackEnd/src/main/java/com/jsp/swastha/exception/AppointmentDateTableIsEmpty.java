package com.jsp.swastha.exception;

import lombok.Data;

@Data
public class AppointmentDateTableIsEmpty extends RuntimeException {
	private String msg="Blood Group is not present";

	public AppointmentDateTableIsEmpty(String msg) {
		super();
		this.msg = msg;
	}

	public AppointmentDateTableIsEmpty() {
		super();
	}

}
