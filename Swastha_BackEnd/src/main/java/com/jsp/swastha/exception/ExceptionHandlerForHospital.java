package com.jsp.swastha.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jsp.swastha.util.ResponseStructure;

@RestControllerAdvice
public class ExceptionHandlerForHospital {
	@ExceptionHandler(SpecialistNotAdminException.class)
	public ResponseEntity<ResponseStructure<String>> SpecialistNotAdmin(SpecialistNotAdminException e) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setData(e.getMsg());
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setMessage("Specialist not an Admin");
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(HospitalIsNotPresentException.class)
	public ResponseEntity<ResponseStructure<String>> passwordWrong(HospitalIsNotPresentException e) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setData(e.getMsg());
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setMessage("Hospital is not Present");
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}
	
}
