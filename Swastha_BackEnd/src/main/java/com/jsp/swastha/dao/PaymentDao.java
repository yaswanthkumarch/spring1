package com.jsp.swastha.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.swastha.dto.Payment;
import com.jsp.swastha.dto.User;
import com.jsp.swastha.repo.PaymentRepo;

@Repository
public class PaymentDao {
	@Autowired
	private PaymentRepo repo;
	
	public Payment saveSlot(Payment payment) {
		return repo.save(payment);
	}
}
