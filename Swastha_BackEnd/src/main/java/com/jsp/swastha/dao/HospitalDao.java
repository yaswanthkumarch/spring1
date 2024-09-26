package com.jsp.swastha.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.swastha.dto.Hospital;
import com.jsp.swastha.dto.Specialist;
import com.jsp.swastha.dto.User;
import com.jsp.swastha.exception.HospitalIsNotPresentException;
import com.jsp.swastha.exception.IdNotPresentException;
import com.jsp.swastha.repo.HospitalRepo;
import com.jsp.swastha.repo.SpecialistRepo;

@Repository
public class HospitalDao {

	@Autowired
	private HospitalRepo repo;
	@Autowired
	private SpecialistRepo sprepo;

	public Hospital saveHospital(Hospital hospital) {
		return repo.save(hospital);
	}

	public Specialist findSpecialistId(String specialist_id) {
//		return repo.fetchBySpecialistId(specialist_id);
		Optional<Specialist> db = sprepo.findById(specialist_id);
		if (db.isPresent()) {
			return db.get();
		} else {
			return null;
		}
	}

//	public void deleteById(String hospital_id) {
//		repo.deleteById(hospital_id);
//	}

	public Hospital deleteById(String id) {
		Optional<Hospital> db = repo.findById(id);
		if (db.isEmpty()) {
			throw new HospitalIsNotPresentException();
		} else {
			Hospital a = repo.findById(id).get();
			repo.deleteById(id);
			return a;
		}
	}

	public Hospital update(Hospital hospital) {
		Hospital db = this.fetchbyId(hospital.getId());
		if (db != null) {
			if (hospital.getAddress() != null) {
				db.setAddress(hospital.getAddress());
			}
			if (hospital.getEmail() != null) {
				db.setEmail(hospital.getEmail());
			}
			if (hospital.getName() != null) {
				db.setName(hospital.getName());
			}
			if (hospital.getPhoneNumber() != null) {
				db.setPhoneNumber(hospital.getPhoneNumber());
			}
			if (hospital.getPincode() != 0) {
				db.setPincode(hospital.getPincode());
			}
			if (hospital.getSpecialists() != null) {
//				List<Specialist> splist = new ArrayList<Specialist>();
//				splist.addAll(hospital.getSpecialists());
//				splist.addAll(db.getSpecialists());
//				db.setSpecialists(splist);

				db.setSpecialists(hospital.getSpecialists());
			}
			return repo.save(db);
		}
		throw new HospitalIsNotPresentException();
	}

	public Hospital fetchbyId(String id) {
		java.util.Optional<Hospital> db = repo.findById(id);
		if (db.isPresent()) {
			return db.get();
		} else {
			throw new IdNotPresentException();
		}
	}

}
