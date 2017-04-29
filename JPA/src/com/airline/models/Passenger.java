package com.airline.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entity implementation class for Entity: Passenger
 *
 */
@Entity

public class Passenger implements Serializable {

	// for this column not to be part of the database passenger table
	
	private static final long serialVersionUID = 1L;

	public Passenger() {
		super();
	}
	
	// primary key of this particular Passenger entity
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Id;
	
	private String firstName;
	
	private String lastName;
	
	@Temporal(TemporalType.DATE)
	private Date dob;
	
	// so that enum type is created as a string and not a value
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	// so that enum type is created as a string and not a value
	@Enumerated(EnumType.STRING)
	private FlightClass flightClass;
   
}
