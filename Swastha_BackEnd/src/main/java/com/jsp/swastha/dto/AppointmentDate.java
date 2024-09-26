package com.jsp.swastha.dto;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.jsp.swastha.util.CustomIdGenerator;

import lombok.Data;

@Data
@Entity
public class AppointmentDate {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "date_seq")
	@GenericGenerator(name = "date_seq", strategy = "com.jsp.swastha.util.CustomIdGenerator", parameters = {
			@Parameter(name = CustomIdGenerator.INCREMENT_PARAM, value = "50"),
			@Parameter(name = CustomIdGenerator.VALUE_PREFIX_PARAMETER, value = "Date_"),
			@Parameter(name = CustomIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })
	private String date_id;
	private Date date;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<AppointmentSlot> appointmentSlots;
	
	
}
