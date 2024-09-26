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

import com.jsp.swastha.dto.Specialist;
import com.jsp.swastha.service.SpecialistService;
import com.jsp.swastha.util.ResponseStructure;

import io.swagger.annotations.ApiOperation;


@ApiOperation(value = "SPECIALIST")
@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.DELETE, RequestMethod.GET, RequestMethod.PUT })
public class SpecialistController {
	@Autowired
	private SpecialistService specialistService;

	@ApiOperation(value = "specialist save")
	@PostMapping("/specialist")
	public ResponseEntity<ResponseStructure<Specialist>> saveSpecialist(@RequestBody Specialist specialist) {
		return specialistService.saveSpecialist(specialist);
	}

	@GetMapping("/loginspecialist")
	public ResponseEntity<ResponseStructure<Specialist>> login(@RequestParam String email, @RequestParam String password) {
		return specialistService.loginSpecialist(email, password);
	}

	@GetMapping("/otpspecialist")
	public ResponseEntity<ResponseStructure<Integer>> otp(@RequestParam String email) {
		return specialistService.otpsend(email);
	}
//	@GetMapping("/otpspecialist")
//	public ResponseEntity<ResponseStructure<Integer>> sendOtp(@RequestParam String email) {
//		return specialistService.otp(email);
//	}

	@PutMapping("/updatespecialist")
	public ResponseEntity<ResponseStructure<Specialist>> update(@RequestBody Specialist specialist) {
		return specialistService.update(specialist);
	}

	@GetMapping("/fetchspecialistByemail")
	public ResponseEntity<ResponseStructure<Specialist>> fetchbyEmail(@RequestParam String email) {
		return specialistService.fetchbyEmail(email);
	}
	@GetMapping("/fetchspecialist")
	public ResponseEntity<ResponseStructure<Specialist>> fetchbyId(@RequestParam String id) {
		return specialistService.fetchbyId(id);
	}

	@DeleteMapping("/deletespecialist")
	public ResponseEntity<ResponseStructure<Specialist>> delete(@RequestParam String id) {
		return specialistService.deletebyId(id);
	}

	@GetMapping("/fetchByNamespecialist")
	public ResponseEntity<ResponseStructure<List<Specialist>>> fetchByName(@RequestParam String firstName) {
		return specialistService.fetchByName(firstName);
	}

	@GetMapping("/fetchByExperiencespecialist")
	public ResponseEntity<ResponseStructure<List<Specialist>>> fetchByExperience(@RequestParam String experience) {
		return specialistService.fetchByExperience(experience);
	}

	@GetMapping("/fetchBySpecializationspecialist")
	public ResponseEntity<ResponseStructure<List<Specialist>>> fetchBySpecialization(
			@RequestParam String specialization) {
		return specialistService.fetchBySpecialization(specialization);
	}
	
	@GetMapping("/fetchByCityspecialist")
	public ResponseEntity<ResponseStructure<List<Specialist>>> fetchByCity(@RequestParam String city) {
		return specialistService.fetchByCity(city);
	}

	@GetMapping("/fetchAll")
	public ResponseEntity<ResponseStructure<List<Specialist>>> fetchAll() {
		return specialistService.fetchAll();
	}
	
	@GetMapping("/assignHospital")
	public ResponseEntity<ResponseStructure<Specialist>> assignHospital(@RequestParam String specialist_id, @RequestParam String hospital_id){
		return specialistService.assignHospital(specialist_id, hospital_id);
	}
	// @GetMapping("/fetchAll")
//	public List<Specialist> fetchAll() {
//		return specialistService.fetchAll();
//	}

//	@DeleteMapping("/deletespecialist")
//	public ResponseEntity<ResponseStructure<Specialist>> delete(@RequestParam int id) {
//		return specialistService.deletebyId(id);
//	}
}
