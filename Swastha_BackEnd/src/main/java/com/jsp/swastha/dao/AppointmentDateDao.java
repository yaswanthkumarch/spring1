package com.jsp.swastha.dao;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.swastha.dto.AppointmentDate;
import com.jsp.swastha.dto.Specialist;
import com.jsp.swastha.dto.User;
import com.jsp.swastha.repo.AppointmentDateRepo;

@Repository
public class AppointmentDateDao {
	
	@Autowired
	private AppointmentDateRepo repo;
	
	public User findUserId(String userId) {
		return repo.fetchByUserId(userId);
	}
	
	public Specialist findSpecialistId(String userId) {
		return repo.fetchBySpecialistId(userId);
	}
	public AppointmentDate saveAppointmentDate(AppointmentDate appointmentDate) {	
		return repo.save(appointmentDate);
	}
	public List<AppointmentDate> fetchAppointmentDate() {
		return repo.findAll();
	}

}
