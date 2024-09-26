package com.jsp.swastha.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.jsp.swastha.dao.HospitalDao;
import com.jsp.swastha.dao.SpecialistDao;
import com.jsp.swastha.dto.Hospital;
import com.jsp.swastha.dto.Specialist;
import com.jsp.swastha.dto.User;
import com.jsp.swastha.exception.HospitalIsNotPresentException;
import com.jsp.swastha.exception.SpecialistEmailWrongException;
import com.jsp.swastha.exception.SpecialistNotAdminException;
import com.jsp.swastha.exception.SpecialistNotPresentException;
import com.jsp.swastha.repo.SpecialistRepo;
import com.jsp.swastha.util.ResponseStructure;

@Service
public class HospitalService {
	@Autowired
	private HospitalDao dao;

	@Autowired
	private JavaMailSender emailSender;

	@Autowired
	private SpecialistDao specialistDao;

	public ResponseEntity<ResponseStructure<Hospital>> saveHospital(String specialist_id, Hospital hospital) {
		Specialist db = dao.findSpecialistId(specialist_id);
		if (db != null) {
			if (db.getAdmin() != null && db.getAdmin().equalsIgnoreCase("admin")) {
				ResponseStructure<Hospital> structure = new ResponseStructure<Hospital>();
				structure.setData(dao.saveHospital(hospital));
				structure.setMessage("Hospital saved successfully");
				structure.setStatus(HttpStatus.CREATED.value());
//				SimpleMailMessage message = new SimpleMailMessage();
//				message.setFrom("vinayakkandregula@gmail.com");
//				message.setTo(hospital.getEmail());
//				message.setSubject("mail registration");
//				message.setText("your mail is registered successffuly");
//				emailSender.send(message);
				return new ResponseEntity<ResponseStructure<Hospital>>(structure, HttpStatus.CREATED);
			}
			throw new SpecialistNotAdminException();
		}
		throw new SpecialistNotPresentException();
	}

	public ResponseEntity<ResponseStructure<Hospital>> deletebyId(String specialist_id, String id) {
		Specialist db = dao.findSpecialistId(specialist_id);
		if (db != null) {
			if (db.getAdmin() != null && db.getAdmin().equalsIgnoreCase("admin")) {
				Hospital hospital = dao.deleteById(id);
				if(hospital !=null) {
					ResponseStructure<Hospital> structure = new ResponseStructure<Hospital>();
					structure.setData(hospital);
					structure.setMessage("Hospital deleted successfully");
					structure.setStatus(HttpStatus.FOUND.value());
					return new ResponseEntity<ResponseStructure<Hospital>>(structure,HttpStatus.FOUND);
				}
			}
			throw new SpecialistNotAdminException();
		}
		throw new SpecialistNotPresentException();
	}

	public ResponseEntity<ResponseStructure<Hospital>> update(String id, Hospital hospital) {
		Specialist db = dao.findSpecialistId(id);
		if (db != null) {
			if (db.getAdmin() != null && db.getAdmin().equalsIgnoreCase("admin")) {
				Hospital db1 = dao.update(hospital);
				if (db1 != null) {
					ResponseStructure<Hospital> structure = new ResponseStructure<Hospital>();
					structure.setData(db1);
					structure.setMessage("Hospital updated successfully");
					structure.setStatus(HttpStatus.FOUND.value());

					return new ResponseEntity<ResponseStructure<Hospital>>(structure, HttpStatus.FOUND);
				}
				throw new HospitalIsNotPresentException();
			}
			throw new SpecialistNotAdminException();
		}
		throw new SpecialistNotPresentException();

	}
}
