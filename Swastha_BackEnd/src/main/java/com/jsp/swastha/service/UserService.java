package com.jsp.swastha.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.jsp.swastha.dao.UserDao;
import com.jsp.swastha.dto.Hospital;
import com.jsp.swastha.dto.User;
import com.jsp.swastha.exception.BloodGroupNotPresentException;
import com.jsp.swastha.exception.EmailWrongException;
import com.jsp.swastha.exception.IdNotPresentException;
import com.jsp.swastha.exception.IdWrongException;
import com.jsp.swastha.exception.PasswordWrongException;
import com.jsp.swastha.exception.UserNotPresentException;
import com.jsp.swastha.util.ResponseStructure;

@Service
public class UserService {
	@Autowired
	private UserDao dao;

	@Autowired
	private JavaMailSender emailSender;

	public ResponseEntity<ResponseStructure<User>> saveUser(User user) {
		ResponseStructure<User> structure = new ResponseStructure<User>();
		User u = dao.saveUser(user);
		structure.setData(u);
		structure.setMessage("user saved successfully");
		structure.setStatus(HttpStatus.CREATED.value());
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("vinayakkandregula@gmail.com");
		message.setTo(user.getEmail());
		message.setSubject("Welcome to Swastha!");
		message.setText(""
				+ "\r\n"
				+ "Dear "+u.getFirstName()+" "+u.getLastName()+",\r\n"
				+ "\r\n"
				+ "Welcome to Swatha, your trusted partner for managing your health and wellness journey! We're delighted to have you onboard.\r\n"
				+ "\r\n"
				+ "Your registration with Swatha is now complete. You're one step closer to accessing a range of features designed to help you lead a healthier lifestyle.\r\n"
				+ "\r\n"
				+ "With Swatha, you can:\r\n"
				+ "\r\n"
				+ "- Keep track of your health metrics\r\n"
				+ "- Store and access your medical records securely\r\n"
				+ "- Set and achieve your fitness goals\r\n"
				+ "- Receive personalized health tips and insights\r\n"
				+ "\r\n"
				+ "To start exploring Swatha and all it has to offer, simply sign in to your account using the credentials you provided during registration.\r\n"
				+ "\r\n"
				+ "If you have any questions or need assistance, our dedicated support team is here to help. Feel free to reach out to us at support@swatha.com.\r\n"
				+ "\r\n"
				+ "Once again, welcome to Swatha! We're excited to support you on your journey to better health and well-being.\r\n"
				+ "\r\n"
				+ "Best regards,\r\n"
				+ "The Swatha Team\r\n"
				+ "");
		emailSender.send(message);
		return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<User>> loginUser( String email , String password) {
		User db = dao.fetchByEmail(email);
		if (db != null) {
			if (db.getPassword().equals(password)) {
				ResponseStructure<User> structure = new ResponseStructure<User>();
				structure.setData(db);
				structure.setMessage("user login successfully");
				structure.setStatus(HttpStatus.FOUND.value());
				return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.FOUND);
			}
			throw new PasswordWrongException();
		}
		throw new EmailWrongException();
	}

	public ResponseEntity<ResponseStructure<Integer>> otpsend(String email) {
		User db = dao.fetchByEmail(email);
		if (db != null) {
			Random r = new Random();
			int value = r.nextInt(10000);
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
			return new ResponseEntity<ResponseStructure<Integer>>(structure1, HttpStatus.FOUND);
		}
		throw new EmailWrongException();
	}

	public ResponseEntity<ResponseStructure<User>> update(User user) {
		User db = dao.update(user);
		if (db != null) {
			ResponseStructure<User> structure = new ResponseStructure<User>();
			structure.setData(db);
			structure.setMessage("user updated successfully");
			structure.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.FOUND);
		}

		throw new UserNotPresentException();
	}
	public ResponseEntity<ResponseStructure<User>> updatepwd(User user) {
		User db = dao.update(user);
		if (db != null) {
			ResponseStructure<User> structure = new ResponseStructure<User>();
			structure.setData(db);
			structure.setMessage("user updated successfully");
			structure.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.FOUND);
		}
		throw new UserNotPresentException();
	}

	public ResponseEntity<ResponseStructure<User>> fetchbyId(String id) {
		User db = dao.fetchbyId(id);
		if (db != null) {
			ResponseStructure<User> structure = new ResponseStructure<User>();
			structure.setData(db);
			structure.setMessage("user fetched successfully");
			structure.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.FOUND);
		}
		throw new IdWrongException();
	}
	public ResponseEntity<ResponseStructure<User>> fetchByEmail(String email) {
		User db = dao.fetchByEmail(email);
		if (db != null) {
			ResponseStructure<User> structure = new ResponseStructure<User>();
			structure.setData(db);
			structure.setMessage("user fetched successfully");
			structure.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.FOUND);
		}
		throw new IdWrongException();
	}

	public ResponseEntity<ResponseStructure<User>> deletebyId(String id) {
//		User db =  dao.deleteById(id);
		User db = dao.deleteById(id);
		if(db !=null) {
			ResponseStructure<User> structure = new ResponseStructure<User>();
			structure.setData(db);
			structure.setMessage("Hospital deleted successfully");
			structure.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<User>>(structure,HttpStatus.FOUND);
		}
		throw new IdNotPresentException();
	}

	public ResponseEntity<ResponseStructure<List<User>>> fetchByBloodgroup(String bloodGroup) {
		List<User> db = dao.fetchByBloodgroup(bloodGroup);
		if (db.size() != 0) {
			ResponseStructure<List<User>> structure1 = new ResponseStructure<List<User>>();
			structure1.setData(db);
			structure1.setMessage("user fetched successfully");
			structure1.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<List<User>>>(structure1, HttpStatus.FOUND);
		}
		throw new BloodGroupNotPresentException();
	}

//	public List<User> fetchByBloodgroup(String bloodGroup) {
//		return dao.fetchByBloodgroup(bloodGroup);
//	}
	public ResponseEntity<ResponseStructure<List<User>>> fetchAllBloodGroup() {
		List<User> db = dao.fetchAllBloodGroup();
		if (db.size() != 0) {
			ResponseStructure<List<User>> structure1 = new ResponseStructure<List<User>>();
			structure1.setData(db);
			structure1.setMessage("user fetched successfully");
			structure1.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<List<User>>>(structure1, HttpStatus.FOUND);
		}
		throw new BloodGroupNotPresentException();
	}

	public ResponseEntity<ResponseStructure<List<User>>> msgDonor(String donoremail, String receiptdonoremail) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setCc(receiptdonoremail,donoremail);
		message.setSubject("Are you available");
		message.setText("Recipient's Email : "+receiptdonoremail+",\r\n"
				+ "\r\n Donor's Email : "+donoremail+""+ "\r\n"
				+ "We hope this message finds you in good health.\r\n"
				+ "\r\n"
				+ "We're writing to inform you about an urgent matter regarding blood donation and assistance for those in need.\r\n"
				+ "\r\n"
				+ "For Donors:\r\n"
				+ "Your generosity could save a life. We're organizing a blood donation drive on [date, time, location], and we would be honored to have you join us. Your donation can make a significant impact and bring hope to those in our community who rely on these life-saving transfusions.\r\n"
				+ "\r\n"
				+ "For Consumers:\r\n"
				+ "If you or someone you know requires blood, we're here to help. Please reach out to us, and we'll do everything we can to assist you in accessing the blood you need. Your well-being is our priority, and we're committed to supporting you through this challenging time.\r\n"
				+ "\r\n"
				+ "Whether you're able to donate or in need of blood, your participation is invaluable. Together, we can make a difference and ensure that everyone in our community receives the care they deserve.\r\n"
				+ "\r\n"
				+ "Thank you for your support and compassion.\r\n"
				+ "\r\n"
				+ "Best regards,");
		emailSender.send(message);
		return null;
	}

//	public List<User> fetchAllBloodGroup() {
//		return dao.fetchAllBloodGroup();
//	}
}
