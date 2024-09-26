package com.jsp.swastha.exception;

import lombok.Data;

@Data
public class SlotNotPresentException  extends RuntimeException{
	private String msg="Slot is not Present";

	public SlotNotPresentException(String msg) {
		super();
		this.msg = msg;
	}

	public SlotNotPresentException() {
		super();
	}

}
