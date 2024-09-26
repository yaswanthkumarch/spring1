package com.jsp.swastha.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.swastha.dto.AppointmentDate;
import com.jsp.swastha.dto.Hospital;
import com.jsp.swastha.dto.User;
import com.jsp.swastha.service.HospitalService;
import com.jsp.swastha.util.ResponseStructure;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.DELETE, RequestMethod.GET, RequestMethod.PUT })
public class HospitalController {

	@Autowired
	private HospitalService service;

	@PostMapping("/hospital")
	public ResponseEntity<ResponseStructure<Hospital>> saveHospital(@RequestParam String specialist_id,
			@RequestBody Hospital hospital) {
		return service.saveHospital(specialist_id, hospital);
	}

	@DeleteMapping("/deletehospital")
	public ResponseEntity<ResponseStructure<Hospital>> delete(@RequestParam String specialist_id, @RequestParam String hospital_id) {
		return service.deletebyId(specialist_id, hospital_id);
	}

	@PutMapping("/updatehospital")
	public ResponseEntity<ResponseStructure<Hospital>> update(@RequestParam String specialist_id,
			@RequestBody Hospital hospital) {
		return service.update(specialist_id, hospital);
	}

}
