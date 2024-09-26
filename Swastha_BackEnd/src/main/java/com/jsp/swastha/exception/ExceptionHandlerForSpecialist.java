package com.jsp.swastha.exception;

import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jsp.swastha.util.ResponseStructure;

@RestControllerAdvice
public class ExceptionHandlerForSpecialist {

	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<ResponseStructure<String>> sqlexception(SQLIntegrityConstraintViolationException e) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setData(e.getMessage());
		structure.setStatus(HttpStatus.BAD_REQUEST.value());
		structure.setMessage("You can't perform this operation");
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(SpecialistEmailWrongException.class)
	public ResponseEntity<ResponseStructure<String>> emailWrong(SpecialistEmailWrongException e) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setData(e.getMsg());
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setMessage("entered the wrong email");
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(SpecialistPasswordWrongException.class)
	public ResponseEntity<ResponseStructure<String>> passwordWrong(SpecialistPasswordWrongException e) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setData(e.getMsg());
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setMessage("entered the wrong password");
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(SpecialistNotPresentException.class)
	public ResponseEntity<ResponseStructure<String>> SpecialNotPresent(SpecialistNotPresentException e) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setData(e.getMsg());
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setMessage("entered the wrong Id");
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(SpecialistTableEmptyException.class)
	public ResponseEntity<ResponseStructure<String>> SpecialTableEmpty(SpecialistTableEmptyException e) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setData(e.getMsg());
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setMessage("Table is Empty");
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(CityNotPresentException.class)
	public ResponseEntity<ResponseStructure<String>> CityWrong(CityNotPresentException e) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setData(e.getMsg());
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setMessage("entered the wrong City");
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(SpecialistExperienceWrongException.class)
	public ResponseEntity<ResponseStructure<String>> ExperienceWrong(SpecialistExperienceWrongException e) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setData(e.getMsg());
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setMessage("entered the wrong Experience");
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(SpecialistNameWrongException.class)
	public ResponseEntity<ResponseStructure<String>> NameWrong(SpecialistNameWrongException e) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setData(e.getMsg());
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setMessage("entered the wrong Name");
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(SpecialistSpecializationWrongException.class)
	public ResponseEntity<ResponseStructure<String>> SpecializationWrong(SpecialistSpecializationWrongException e) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setData(e.getMsg());
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setMessage("entered the wrong Specialization");
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

}
