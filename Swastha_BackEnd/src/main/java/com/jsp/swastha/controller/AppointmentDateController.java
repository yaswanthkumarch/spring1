package com.jsp.swastha.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.swastha.dto.AppointmentDate;
import com.jsp.swastha.dto.User;
import com.jsp.swastha.service.AppointmentDateService;
import com.jsp.swastha.util.ResponseStructure;

import io.swagger.annotations.ApiOperation;

@ApiOperation(value = "AppointmentDate")
@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.DELETE, RequestMethod.GET, RequestMethod.PUT })
public class AppointmentDateController {
	
	@Autowired
	private AppointmentDateService service;
	
	@ApiOperation(value = "date save")
	@PostMapping("/date")
	public ResponseEntity<ResponseStructure<AppointmentDate>> saveslot(@RequestParam String userId,@RequestParam String specialistId,@RequestBody AppointmentDate appointmentDate) {
		return service.BookingSlot(userId, specialistId, appointmentDate);
	}
	
	@GetMapping("/fetchslot")
	public ResponseEntity<ResponseStructure<List<AppointmentDate>>> fetchslots() {
		return service.fetchSlot();
	}
	
	
}
