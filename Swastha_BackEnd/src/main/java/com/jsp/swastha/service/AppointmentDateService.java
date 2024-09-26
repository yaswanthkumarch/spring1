package com.jsp.swastha.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.jsp.swastha.dao.AppointmentDateDao;
import com.jsp.swastha.dao.PaymentDao;
import com.jsp.swastha.dao.SpecialistDao;
import com.jsp.swastha.dao.UserDao;
import com.jsp.swastha.dto.AppointmentDate;
import com.jsp.swastha.dto.AppointmentSlot;
import com.jsp.swastha.dto.Payment;
import com.jsp.swastha.dto.Specialist;
import com.jsp.swastha.dto.User;
import com.jsp.swastha.exception.AppointmentDateTableIsEmpty;
import com.jsp.swastha.exception.SpecialistNotPresentException;
import com.jsp.swastha.exception.UserNotPresentException;
import com.jsp.swastha.util.ResponseStructure;

@Service
public class AppointmentDateService {

	@Autowired
	private AppointmentDateDao dao;

	@Autowired
	private PaymentDao paymentDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private SpecialistDao specialistDao;

	@Autowired
	private JavaMailSender emailSender;

	public ResponseEntity<ResponseStructure<AppointmentDate>> BookingSlot(String userId, String specialistId,
			AppointmentDate appointmentDate) {
		User db = dao.findUserId(userId);
		if (db != null) {
			Specialist sp = dao.findSpecialistId(specialistId);
			if (sp != null) {
				AppointmentDate slot = dao.saveAppointmentDate(appointmentDate);
				List<AppointmentDate> date = new ArrayList<AppointmentDate>();
				date.add(slot);
				date.addAll(sp.getAppointmentDates());
				sp.setAppointmentDates(date);
				ResponseStructure<AppointmentDate> structure = new ResponseStructure<AppointmentDate>();
				structure.setData(slot);
				structure.setMessage("Your slot successfully booked");
				structure.setStatus(HttpStatus.CREATED.value());
				if (slot != null) {
					List<AppointmentSlot> list = slot.getAppointmentSlots();
					for (AppointmentSlot appointmentSlot : list) {
						if (appointmentSlot != null) {
							Payment pay = paymentDao.saveSlot(appointmentSlot.getPayment());
							List<Payment> payList = new ArrayList<Payment>();
							payList.add(pay);
							payList.addAll(db.getPayments());
							db.setPayments(payList);
							List<Specialist> splist = new ArrayList<Specialist>();
							splist.add(sp);
							splist.addAll(db.getSpecialists());
							db.setSpecialists(splist);
							userDao.update(db);
							SimpleMailMessage message = new SimpleMailMessage();
							message.setFrom("swasthaoffice@gmail.com");
							message.setTo(db.getEmail());
							message.setSubject("Confirmation of Your Slot Booking");
							message.setText("Dear " + db.getFirstName() + " " + db.getLastName() + "," + "\n"
									+ "We would like to confirm your upcoming appointment with " + sp.getFirstName()
									+ " " + sp.getLastName() + " on " + slot.getDate().toString() + " at " + "\n"
									+ appointmentSlot.getTime()
									+ ". We appreciate your trust in our healthcare services and look forward to" + "\n"
									+ " providing you with the best possible care." + "\n" + "\n"

									+ "Appointment Details: " + "\n" + "  Doctor: " + sp.getFirstName() + " "
									+ sp.getLastName() + "\n" + "  Date: " + slot.getDate().toString() + "\n"
									+ "  Time: " + appointmentSlot.getTime() + "\n" + "\n"
									+ "Thank you for choosing our hospital for your healthcare needs. We look" + "\n"
									+ " forward to assisting you and ensuring you have a positive and comfortable experience"
									+ "\n" + " during your visit"

//									+ "SlotDate : " + slot.getDate().toString() + "\n" + "SlotTime : "
//									+ appointmentSlot.getTime() + "\n" + "Specialist Name : " + sp.getFirstName() + " "
//									+ sp.getLastName() + "\n" + "Specialization : " + sp.getSpecialization() + "\n"
//									+ "Experience : " + sp.getExperience() + "\n" + "Fees : "
//									+ appointmentSlot.getPayment().getFees() + "\n" + "Specialist Email Id : "
//									+ sp.getEmail());
							);
							emailSender.send(message);
							SimpleMailMessage message1 = new SimpleMailMessage();
							message1.setFrom("swasthaoffice@gmail.com");
							message1.setTo(sp.getEmail());
							message1.setSubject("Confirmation of Patient Appointment on " + slot.getDate().toString()
									+ " at " + appointmentSlot.getTime() + " with " + sp.getFirstName() + " "
									+ sp.getLastName() + "");
							message1.setText("Dear " + sp.getFirstName() + " " + sp.getLastName() + "," + "\n" + "\n"

									+ "I am writing to inform you that a patient, " + db.getFirstName() + " "
									+ db.getLastName() + ", has booked an appointment with you on "
									+ slot.getDate().toString() + "at " + appointmentSlot.getTime() + "" + "\n"
									+ "The patient has specifically chosen your expertise for their medical needs."
									+ "\n" + "\n"

									+ "Appointment Details: " + "\n" + "  Patient: " + db.getFirstName() + " "
									+ db.getLastName() + "\n" + "  Date: " + slot.getDate().toString() + "\n"
									+ "  Time: " + appointmentSlot.getTime() + "\n" + "\n" +

									"Thank you for your dedication to patient care, and we look forward to your valuable"
									+ "\n" + " contribution to the well-being of " + sp.getFirstName() + " "
									+ sp.getLastName() + "."
//									"SlotDate : " + slot.getDate().toString() + "\n" + "SlotTime : "
//									+ appointmentSlot.getTime() + "\n" + "User Name : " + db.getFirstName() + " "
//									+ db.getLastName() + "\n" + "BloodGroup : " + db.getBloodGroup() + "\n" + "Email : "
//									+ db.getEmail()
							);
							emailSender.send(message1);
						}
//						throw new SlotNotPresentException();
					}
				}
				return new ResponseEntity<ResponseStructure<AppointmentDate>>(structure, HttpStatus.CREATED);
			}
			throw new SpecialistNotPresentException();
		}
		throw new UserNotPresentException();
	}

	public ResponseEntity<ResponseStructure<List<AppointmentDate>>> fetchSlot() {
		List<AppointmentDate> list = dao.fetchAppointmentDate();
		if (list.size() != 0) {
			ResponseStructure<List<AppointmentDate>> structure = new ResponseStructure<List<AppointmentDate>>();
			structure.setData(list);
			structure.setMessage("All AppointmentDate are fetched");
			structure.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<List<AppointmentDate>>>(structure, HttpStatus.FOUND);
		}
		throw new AppointmentDateTableIsEmpty();
	}
}
