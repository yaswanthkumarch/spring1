package com.jsp.swastha.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jsp.swastha.dto.Hospital;
import com.jsp.swastha.dto.Specialist;

public interface HospitalRepo extends JpaRepository<Hospital, String> {

	@Query("select a from Specialist a where a.id=?1")
	Specialist fetchBySpecialistId(String id);

}
