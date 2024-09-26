package com.jsp.swastha.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jsp.swastha.dto.User;

public interface UserRepo extends JpaRepository<User, String> {

	@Query("select a from User a where a.Email=?1")
	User fetchByEmail(String email);

	@Query("select a from User a where a.bloodGroup=?1 and a.availabilty='AVAILABLE'")
	List<User> fetchByBloodgroup(String bloodGroup);

	@Query("select a from User a where a.id=?1")
	User fetchById1(int id);
	
	@Query("select a from User a where a.availabilty='AVAILABLE'")
	List<User> fetchAllBloodGroup();
	
	}
