package com.jsp.swastha.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.swastha.dto.Address;

public interface AddressRepo extends JpaRepository<Address, Integer> {

}
