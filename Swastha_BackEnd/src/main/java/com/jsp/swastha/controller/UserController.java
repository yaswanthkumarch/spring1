package com.jsp.swastha.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.swastha.dto.Hospital;
import com.jsp.swastha.dto.User;
import com.jsp.swastha.service.UserService;
import com.jsp.swastha.util.ResponseStructure;

import io.swagger.annotations.ApiOperation;

@ApiOperation(value = "USER")
@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.DELETE, RequestMethod.GET , RequestMethod.PUT })
public class UserController {
	@Autowired
	private UserService userservice;

	@ApiOperation(value = "user save")
	@PostMapping("/user")
	public ResponseEntity<ResponseStructure<User>> saveUser(@RequestBody User user) {
		return userservice.saveUser(user);
	}

//	@ApiOperation(value = "login")
	@GetMapping("/login")
	public ResponseEntity<ResponseStructure<User>> loginUser(@RequestParam String email,@RequestParam String password) {
		return userservice.loginUser(email, password);
	}

	@GetMapping("/otp")
	public ResponseEntity<ResponseStructure<Integer>> sendOtp(@RequestParam String email) {
		return userservice.otpsend(email);
	}

	@PutMapping("/update")
	public ResponseEntity<ResponseStructure<User>> update(@RequestBody User user) {
		return userservice.update(user);
	}
	@PutMapping("/updatepwd")
	public ResponseEntity<ResponseStructure<User>> updatepwd(@RequestBody User user) {
		return userservice.updatepwd(user);
	}


	@GetMapping("/fetch")
	public ResponseEntity<ResponseStructure<User>> fetchbyId(@RequestParam String id) {
		return userservice.fetchbyId(id);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<ResponseStructure<User>> delete(@RequestParam String id) {
		return userservice.deletebyId(id);
	}
//	@DeleteMapping("/deletehospital")
//	public ResponseEntity<ResponseStructure<Hospital>> delete(@RequestParam String specialist_id, @RequestParam String hospital_id) {
//		return service.deletebyId(specialist_id, hospital_id);
//	}
	@GetMapping("/fetchByEmail")
	public ResponseEntity<ResponseStructure<User>> fetchByEmail(@RequestParam String email) {
		return userservice.fetchByEmail(email);
	}
	@GetMapping("/fetchByBloodGroup")
	public ResponseEntity<ResponseStructure<List<User>>> fetchByBloodGroup(@RequestParam String bloodGroup) {
		return userservice.fetchByBloodgroup(bloodGroup);
	}

	@GetMapping("/fetchAllBloodGroup")
	public ResponseEntity<ResponseStructure<List<User>>> fetchAllBloodGroup() {
		return userservice.fetchAllBloodGroup();
	}

	@GetMapping("/msgDonor")
	public ResponseEntity<ResponseStructure<List<User>>> msgDonor(@RequestParam String donoremail,
			@RequestParam String receiptdonoremail) {
		return userservice.msgDonor(donoremail, receiptdonoremail);
	}

//	@GetMapping("/fetchByBloodGroup")
//	public ResponseEntity<ResponseStructure<User>> fetchByBloodGroup(@RequestParam String bloodGroup) {
//		return userservice.fetchByBloodgroup(bloodGroup);
//	}
}
