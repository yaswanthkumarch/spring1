package com.jsp.swastha.exception;

import java.net.IDN;
import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jsp.swastha.util.ResponseStructure;

@RestControllerAdvice
public class EceptionHandlerForUser {

	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<ResponseStructure<String>> sqlexception(SQLIntegrityConstraintViolationException e) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setData(e.getMessage());
		structure.setStatus(HttpStatus.BAD_REQUEST.value());
		structure.setMessage("You can't perform this operation");
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(PasswordWrongException.class)
	public ResponseEntity<ResponseStructure<String>> passwordWrong(PasswordWrongException e) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setData(e.getMsg());
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setMessage("password is wrong");
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(EmailWrongException.class)
	public ResponseEntity<ResponseStructure<String>> emailWrong(EmailWrongException e) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setData(e.getMsg());
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setMessage("entered the wrong email");
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(IdWrongException.class)
	public ResponseEntity<ResponseStructure<String>> idWrong(IdWrongException e) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setData(e.getMsg());
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setMessage("entered the wrong email");
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}


	@ExceptionHandler(IdNotPresentException.class)
	public ResponseEntity<ResponseStructure<String>> wrongId(IdNotPresentException e) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setData(e.getMsg());
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setMessage("entered the wrong Id");
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(BloodGroupNotPresentException.class)
	public ResponseEntity<ResponseStructure<String>> bloodGroupWrong(BloodGroupNotPresentException e) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setData(e.getMsg());
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setMessage("entered the wrong blood Group");
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

	
}
