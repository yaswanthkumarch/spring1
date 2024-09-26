package com.jsp.swastha.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.swastha.dto.Hospital;
import com.jsp.swastha.dto.Specialist;
import com.jsp.swastha.dto.User;
import com.jsp.swastha.exception.HospitalIsNotPresentException;
import com.jsp.swastha.exception.IdNotPresentException;
import com.jsp.swastha.exception.SpecialistNotPresentException;
import com.jsp.swastha.repo.HospitalRepo;
import com.jsp.swastha.repo.SpecialistRepo;

@Repository
public class SpecialistDao {

	@Autowired
	private SpecialistRepo specialistRepo;

	@Autowired
	private HospitalRepo hospitalRepo;

	@Autowired
	private HospitalDao hospitalDao;

	public Specialist saveSpecialist(Specialist specialist) {
		return specialistRepo.save(specialist);
	}

	public Specialist fetchByEmail(String email) {
		Specialist db = specialistRepo.fetchByEmail(email);
		if (db != null) {
			return db;
		} else {
			return null;
		}
	}
//	public Specialist fetchByEmail(String email) {
//		Specialist db = specialistRepo.fetchByEmail(email);
//		if (db != null) {
//			return db;
//		} else {
//			return null;
//		}
//	}

	public Specialist update(Specialist specialist) {
		Specialist db = this.fetchbyId(specialist.getId());
		if (db != null) {
			if (specialist.getPassword() != null) {
				db.setPassword(specialist.getPassword());
			}
			if (specialist.getSpecialization() != null) {
				db.setSpecialization(specialist.getSpecialization());
			}
			if (specialist.getExperience() != null) {
				db.setExperience(specialist.getExperience());
			}
			if (specialist.getEmail() != null) {
				db.setEmail(specialist.getEmail());
			}
			if (specialist.getFirstName() != null) {
				db.setFirstName(specialist.getFirstName());
			}
			if (specialist.getGender() != null) {
				db.setGender(specialist.getGender());
			}
			if (specialist.getLastName() != null) {
				db.setLastName(specialist.getLastName());
			}
			if (specialist.getPhone() != 0) {
				db.setPhone(specialist.getPhone());
			}
			if (specialist.getFees() != 0) {
				db.setFees(specialist.getFees());
			}
			if (specialist.getAge() != 0) {
				db.setAge(specialist.getAge());
			}
			if (specialist.getAddress() != null) {
				db.setAddress(specialist.getAddress());
			}
			if (specialist.getUsers() != null && specialist.getUsers().size() != 0) {
				db.setUsers(specialist.getUsers());
			}
			if (specialist.getAppointmentDates() != null && specialist.getAppointmentDates().size() != 0) {
				db.setAppointmentDates(specialist.getAppointmentDates());
			}
//			if (specialist.getHospital_Id() != null) {
//				Optional<Hospital> h = hospitalRepo.findById(specialist.getHospital_Id());
//				if (h.isEmpty()) {
//					throw new HospitalIsNotPresentException();
//				} else {
//					Hospital a = hospitalRepo.findById(specialist.getHospital_Id()).get();
//					db.setHospital_Id(specialist.getHospital_Id());
//				 	Hospital hospital = h.get();
//				 	List<Specialist> list = hospital.getSpecialists();
//				 	list.add(specialist);
//				 	hospital.setSpecialists(list);
//				}
//			}
			return specialistRepo.save(db);
		} else {
			throw new SpecialistNotPresentException();
		}
	}

//	public Specialist fetchbyId(String id) {
//		Specialist db = specialistRepo.findById(id).get();
//		if (db != null) {
//			return db;
//		} else {
//			throw new SpecialistNotPresentException();
//		}
//	}
	public Specialist fetchbyId(String id) {
		java.util.Optional<Specialist> db = specialistRepo.findById(id);
		if (db.isPresent()) {
			return db.get();
		} else {
			throw new SpecialistNotPresentException();
		}
	}

//	public void deletebyId(String id) {
//		specialistRepo.deleteById(id);
//	}

	public Specialist deletebyId(String id) {
		Optional<Specialist> db = specialistRepo.findById(id);
		if (db.isEmpty()) {
			throw new IdNotPresentException();
		} else {
			specialistRepo.deleteById(id);
			return db.get();
		}
	}

	public List<Specialist> fetchByName(String firstName) {
		return specialistRepo.fetchByName(firstName);
	}

	public List<Specialist> fetchByExperience(String experience) {
		return specialistRepo.fetchByExperience(experience);
	}

	public List<Specialist> fetchBySpecialization(String specialization) {
		List<Specialist> db = specialistRepo.fetchBySpecialization(specialization);
		if (db != null) {
			return db;
		} else {
			return null;
		}

	}

	public List<Specialist> fetchByCity(String city) {
		return specialistRepo.fetchByCity(city);
	}

	public List<Specialist> fetchAll() {
		return specialistRepo.findAll();
	}

}
