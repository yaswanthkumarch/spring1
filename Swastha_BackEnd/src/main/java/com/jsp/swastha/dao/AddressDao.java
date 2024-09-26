package com.jsp.swastha.dao;

import com.jsp.swastha.dto.Address;
import com.jsp.swastha.dto.User;
import com.jsp.swastha.repo.AddressRepo;

public class AddressDao {
	
	private AddressRepo addressRepo;
	
	public Address saveAddress(Address address) {
		return addressRepo.save(address);
	}
}
