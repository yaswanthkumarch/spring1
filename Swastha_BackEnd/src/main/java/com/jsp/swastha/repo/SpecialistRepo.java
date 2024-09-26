package com.jsp.swastha.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jsp.swastha.dto.Specialist;

public interface SpecialistRepo extends JpaRepository<Specialist, String> {
	
	@Query("select a from Specialist a where a.Email=?1")
	public Specialist fetchByEmail(String email);
	
	@Query("select a from Specialist a where a.firstName=?1")
	List<Specialist> fetchByName(String name);
	
	@Query("select a from Specialist a where a.experience=?1")
	List<Specialist> fetchByExperience(String experience);
	
	@Query("select a from Specialist a where a.specialization=?1")
	List<Specialist> fetchBySpecialization(String specialization);
	
	@Query("select a from Specialist a join a.address b where b.city=?1")
	List<Specialist> fetchByCity(String city);
}
