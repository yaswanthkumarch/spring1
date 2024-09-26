package com.jsp.swastha.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jsp.swastha.util.ResponseStructure;

@RestControllerAdvice
public class ExceptionHandlerForSlot {

	@ExceptionHandler(UserNotPresentException.class)
	public ResponseEntity<ResponseStructure<String>> passwordWrong(UserNotPresentException e) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setData(e.getMsg());
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setMessage("User_Id is wrong");
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(SpecialistNotPresentException.class)
	public ResponseEntity<ResponseStructure<String>> passwordWrong(SpecialistNotPresentException e) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setData(e.getMsg());
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setMessage("Specialist_Id is wrong");
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(SlotNotPresentException.class)
	public ResponseEntity<ResponseStructure<String>> slotWrong(SlotNotPresentException e) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setData(e.getMsg());
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setMessage("Slot is wrong");
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(AppointmentDateTableIsEmpty.class)
	public ResponseEntity<ResponseStructure<String>> fetchEmpty(AppointmentDateTableIsEmpty e) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setData(e.getMsg());
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setMessage("AppointmentSlot Table is empty");
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

}
