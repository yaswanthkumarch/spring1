package com.jsp.swastha.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jsp.swastha.util.CustomIdGenerator;

import lombok.Data;

@Data
@Entity
public class Specialist {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "specialist_seq")
	@GenericGenerator(name = "specialist_seq", strategy = "com.jsp.swastha.util.CustomIdGenerator", parameters = {
			@Parameter(name = CustomIdGenerator.INCREMENT_PARAM, value = "50"),
			@Parameter(name = CustomIdGenerator.VALUE_PREFIX_PARAMETER, value = "Specialist_"),
			@Parameter(name = CustomIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })
	private String id;
	private String firstName;
	private String lastName;
	@Column(unique = true)
	private String Email;
	private String password;
	private long phone;
	private String specialization;
	private String experience;
	private String gender;
	private int age;
	private double fees;
	private String admin;
	@OneToOne(cascade = CascadeType.ALL)
	private Address address;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "specialists" , fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	private List<User> users;

	@OneToMany(cascade = CascadeType.ALL)
	private List<AppointmentDate> appointmentDates;

	@Override
	public String toString() {
		return "Specialist [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", Email=" + Email
				+ ", password=" + password + ", phone=" + phone + ", specialization=" + specialization + ", experience="
				+ experience + ", gender=" + gender + ", age=" + age + ", fees=" + fees + ", admin=" + admin
				+ ", address=" + address + ", appointmentDates=" + appointmentDates + "]";
	}
	
}
