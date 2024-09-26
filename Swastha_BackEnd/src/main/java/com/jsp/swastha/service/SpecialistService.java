package com.jsp.swastha.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
import com.jsp.swastha.exception.CityNotPresentException;
import com.jsp.swastha.exception.EmailWrongException;
import com.jsp.swastha.exception.HospitalIsNotPresentException;
import com.jsp.swastha.exception.IdNotPresentException;
import com.jsp.swastha.exception.PasswordWrongException;
import com.jsp.swastha.exception.SpecialistEmailWrongException;
import com.jsp.swastha.exception.SpecialistExperienceWrongException;
import com.jsp.swastha.exception.SpecialistNameWrongException;
import com.jsp.swastha.exception.SpecialistNotPresentException;
import com.jsp.swastha.exception.SpecialistPasswordWrongException;
import com.jsp.swastha.exception.SpecialistSpecializationWrongException;
import com.jsp.swastha.exception.SpecialistTableEmptyException;
import com.jsp.swastha.util.ResponseStructure;

@Service
public class SpecialistService {

	@Autowired
	private SpecialistDao specialistDao;
	@Autowired
	private HospitalDao hospitalDao;

	@Autowired
	private JavaMailSender emailSender;

	public ResponseEntity<ResponseStructure<Specialist>> saveSpecialist(Specialist specialist) {
		ResponseStructure<Specialist> structure = new ResponseStructure<Specialist>();
		Specialist s = specialistDao.saveSpecialist(specialist);
		structure.setData(s);
		structure.setMessage("Specialist saved successfully");
		structure.setStatus(HttpStatus.CREATED.value());
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("vinayakkandregula@gmail.com");
		message.setTo(specialist.getEmail());
		message.setSubject("Welcome to Swatha");
		message.setText(""
				+ "\r\n"
				+ "Dear "+s.getFirstName()+" "+s.getLastName()+",\r\n"
				+ "\r\n"
				+ "Welcome to Swatha, your trusted platform for connecting with patients and providing exceptional healthcare services! We're thrilled to have you join our network of healthcare professionals.\r\n"
				+ "\r\n"
				+ "With Swatha, you'll have the opportunity to:\r\n"
				+ "\r\n"
				+ "- Expand your practice and reach a broader audience of patients\r\n"
				+ "- Easily manage your appointments and schedule consultations\r\n"
				+ "- Access patient medical records securely and efficiently\r\n"
				+ "- Provide personalized care and valuable insights to your patients\r\n"
				+ "- Stay up-to-date with the latest healthcare trends and developments\r\n"
				+ "\r\n"
				+ "To get started, simply sign in to your Swatha account using the credentials you provided during registration. From there, you can set up your profile, update your availability, and start connecting with patients.\r\n"
				+ "\r\n"
				+ "If you have any questions or need assistance, our support team is here to help. Feel free to reach out to us at support@swatha.com.\r\n"
				+ "\r\n"
				+ "Once again, welcome to Swatha! We're excited to partner with you in delivering exceptional healthcare experiences to patients around the world.\r\n"
				+ "\r\n"
				+ "Best regards,\r\n"
				+ "The Swatha Team\r\n"
				+ "");
		emailSender.send(message);
		return new ResponseEntity<ResponseStructure<Specialist>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Specialist>> loginSpecialist(String email,String password) {
		Specialist db = specialistDao.fetchByEmail(email);
		if (db != null) {
			if (db.getPassword().equals(password)) {
				ResponseStructure<Specialist> structure = new ResponseStructure<Specialist>();
				structure.setData(db);
				structure.setMessage("Specialist login successfully");
				structure.setStatus(HttpStatus.FOUND.value());
				return new ResponseEntity<ResponseStructure<Specialist>>(structure, HttpStatus.FOUND);
			}
			throw new SpecialistPasswordWrongException();
		}
		throw new SpecialistEmailWrongException();
	}

	public ResponseEntity<ResponseStructure<Integer>> otpsend(String email) {
		Specialist db = specialistDao.fetchByEmail(email);
		if (db != null) {
			Random random = new Random();
			int value = random.nextInt(10000);
			ResponseStructure<Integer> structure1 = new ResponseStructure<Integer>();
			structure1.setData(value);
			structure1.setMessage("otp sent successfully");
			structure1.setStatus(HttpStatus.FOUND.value());
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom("vinayakkandregula@gmail.com");
			message.setTo(email);
			message.setSubject("otp verification");
			message.setText("Subject: Password Reset OTP\r\n"
					+ "\r\n"
					+ "Dear "+db.getFirstName()+" "+db.getLastName()+",\r\n"
					+ "\r\n"
					+ "You have requested to reset your password. Please use the following OTP (One-Time Password) to proceed with the password reset process:\r\n"
					+ "\r\n"
					+ "OTP: "+value+"\r\n"
					+ "\r\n"
					+ "This OTP is valid for 10 minutes. Please do not share this OTP with anyone for security reasons.\r\n"
					+ "\r\n"
					+ "If you did not request this password reset, you can safely ignore this email.\r\n"
					+ "\r\n"
					+ "Thank you,\r\n"
					+ "Swastha");
			emailSender.send(message);
			emailSender.send(message);
			return new ResponseEntity<ResponseStructure<Integer>>(structure1, HttpStatus.FOUND);
		}
		throw new SpecialistEmailWrongException();
	}

	public ResponseEntity<ResponseStructure<Specialist>> update(Specialist specialist) {
		Specialist db = specialistDao.update(specialist);
		if (db != null) {
			ResponseStructure<Specialist> structure = new ResponseStructure<Specialist>();
			structure.setData(db);
			structure.setMessage("Specialist updated successfully");
			structure.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<Specialist>>(structure, HttpStatus.FOUND);
		}
		throw new SpecialistEmailWrongException();
	}

	public ResponseEntity<ResponseStructure<Specialist>> fetchbyId(String id) {
		Specialist db = specialistDao.fetchbyId(id);
		if (db != null) {
			ResponseStructure<Specialist> structure = new ResponseStructure<Specialist>();
			structure.setData(db);
			structure.setMessage("user fetched successfully");
			structure.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<Specialist>>(structure, HttpStatus.FOUND);
		}
		throw new SpecialistNotPresentException();
	}
	public ResponseEntity<ResponseStructure<Specialist>> fetchbyEmail(String email) {
		Specialist db = specialistDao.fetchByEmail(email);
		if (db != null) {
			ResponseStructure<Specialist> structure = new ResponseStructure<Specialist>();
			structure.setData(db);
			structure.setMessage("user fetched successfully");
			structure.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<Specialist>>(structure, HttpStatus.FOUND);
		}
		throw new SpecialistNotPresentException();
	}
	public ResponseEntity<ResponseStructure<Specialist>> deletebyId(String id) {
		Specialist db = specialistDao.deletebyId(id);
		if (db != null) {
			ResponseStructure<Specialist> structure = new ResponseStructure<Specialist>();
			structure.setData(db);
			structure.setMessage("Specialist deleted successfully");
			structure.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<Specialist>>(structure, HttpStatus.FOUND);
		}
		throw new SpecialistNotPresentException();
	}

//	public ResponseEntity<ResponseStructure<Specialist>> deletebyId(int id) {
//		Specialist db = specialistDao.fetchbyId(id);
//		if (db != null) {
//			specialistDao.deletebyId(id);
//			ResponseStructure<Specialist> structure = new ResponseStructure<Specialist>();
//			structure.setData(db);
//			structure.setMessage("user deleted successfully");
//			structure.setStatus(HttpStatus.FOUND.value());
//			return new ResponseEntity<ResponseStructure<Specialist>>(structure, HttpStatus.FOUND);
//		}
//		throw new IdNotPresentException();
//	}
//	public List<Specialist> fetchByName(String firstName) {
//		return specialistDao.fetchByName(firstName);
//	}
	public ResponseEntity<ResponseStructure<List<Specialist>>> fetchByName(String firstName) {
		List<Specialist> db = specialistDao.fetchByName(firstName);
		if (db.size() != 0) {
			ResponseStructure<List<Specialist>> structure = new ResponseStructure<List<Specialist>>();
			structure.setData(db);
			structure.setMessage("All specialist are fetched by firstName");
			structure.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<List<Specialist>>>(structure, HttpStatus.FOUND);
		}
		throw new SpecialistNameWrongException();
	}

//	public List<Specialist> fetchByExperience(String experience) {
//		return specialistDao.fetchByExperience(experience);
//	}
	public ResponseEntity<ResponseStructure<List<Specialist>>> fetchByExperience(String experience) {
		List<Specialist> db = specialistDao.fetchByExperience(experience);
		if (db.size() != 0) {
			ResponseStructure<List<Specialist>> structure = new ResponseStructure<List<Specialist>>();
			structure.setData(db);
			structure.setMessage("All specialist are fetched by experience ");
			structure.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<List<Specialist>>>(structure, HttpStatus.FOUND);
		}
		throw new SpecialistExperienceWrongException();
	}

//	public List<Specialist> fetchBySpecialization(String specialization) {
//		return specialistDao.fetchBySpecialization(specialization);
//	}
	public ResponseEntity<ResponseStructure<List<Specialist>>> fetchBySpecialization(String specialization) {
		List<Specialist> db = specialistDao.fetchBySpecialization(specialization);
		System.out.println(db);
		if (db.size() != 0) {
			ResponseStructure<List<Specialist>> structure = new ResponseStructure<List<Specialist>>();
			structure.setData(db);
			structure.setMessage("All specialist are fetched by specialization ");
			structure.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<List<Specialist>>>(structure, HttpStatus.FOUND);
		}
		throw new SpecialistSpecializationWrongException();
	}
//	public List<Specialist> fetchByCity(String city) {
//		List<Specialist> db = specialistDao.fetchByCity(city);
//		return db;
//	}
	public ResponseEntity<ResponseStructure<List<Specialist>>> fetchByCity(String city) {
		List<Specialist> db = specialistDao.fetchByCity(city);
		if (db.size() != 0) {
			ResponseStructure<List<Specialist>> structure = new ResponseStructure<List<Specialist>>();
			structure.setData(db);
			structure.setMessage("All specialist are fetched by city ");
			structure.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<List<Specialist>>>(structure, HttpStatus.FOUND);
		}
		throw new CityNotPresentException();
	}

//	public List<Specialist> fetchAll() {
//		return specialistDao.fetchAll();
//	}

	public ResponseEntity<ResponseStructure<List<Specialist>>> fetchAll() {
		List<Specialist> db = specialistDao.fetchAll();
		if (db.size() != 0) {
			ResponseStructure<List<Specialist>> structure = new ResponseStructure<List<Specialist>>();
			structure.setData(db);
			structure.setMessage("All specialist are fetched successfully");
			structure.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<List<Specialist>>>(structure, HttpStatus.FOUND);
		}
		throw new SpecialistTableEmptyException();
	}

	public ResponseEntity<ResponseStructure<Specialist>> assignHospital(String specialist_id, String hospital_id) {
		Specialist sp = specialistDao.fetchbyId(specialist_id);
		if (sp != null) {
			Hospital hospital = hospitalDao.fetchbyId(hospital_id);
			if (hospital != null) {
				List<Specialist> list = new ArrayList<Specialist>();
				list.add(sp);
				list.addAll(hospital.getSpecialists());
				hospital.setSpecialists(list);
				ResponseStructure<Specialist> structure = new ResponseStructure<Specialist>();
				structure.setData(sp);
				structure.setMessage("All specialist are fetched successfully");
				structure.setStatus(HttpStatus.FOUND.value());
				hospitalDao.update(hospital);
				return new ResponseEntity<ResponseStructure<Specialist>>(structure, HttpStatus.FOUND);
			}
		}
		throw new SpecialistNotPresentException();
	}
}
