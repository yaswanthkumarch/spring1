package com.jsp.swastha.exception;

import lombok.Data;

@Data
public class SpecialistNotAdminException extends RuntimeException {
	private String msg="Specialist is not Admin";

	public SpecialistNotAdminException(String msg) {
		super();
		this.msg = msg;
	}

	public SpecialistNotAdminException() {
		super();
	}


}
