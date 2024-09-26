package com.jsp.swastha.dto;


import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.jsp.swastha.util.CustomIdGenerator;

import lombok.Data;

@Data
@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
	@GenericGenerator(name = "user_seq", strategy = "com.jsp.swastha.util.CustomIdGenerator", parameters = {
			@Parameter(name = CustomIdGenerator.INCREMENT_PARAM, value = "50"),
			@Parameter(name = CustomIdGenerator.VALUE_PREFIX_PARAMETER, value = "User_"),
			@Parameter(name = CustomIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })
	private String id;
	private String firstName;
	private String lastName;
	@Column(unique = true)
	private String Email;
	private String password;
	private long phone;
	private String bloodGroup;
	private String availabilty;
	private String gender; 

	@OneToOne(cascade = CascadeType.ALL)
	private Address address;

	@JoinColumn
	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	private List<Specialist> specialists;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Payment> payments;
}
