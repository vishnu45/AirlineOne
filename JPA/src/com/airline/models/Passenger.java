package com.airline.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entity implementation class for Entity: Passenger
 *
 */
@Entity
@XmlRootElement
public class Passenger implements Serializable {

	// for this column not to be part of the database passenger table
	@Transient
	private static final long serialVersionUID = 1L;

	public Passenger() {
		super();
	}

	// primary key of this particular Passenger entity
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

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
	
	// the list of flights/flight reservations the passenger has
	// mapped by - the passengers variable in flight class
	@ManyToMany(mappedBy = "passengers")
	// f_p_join table for the many2many mapping, flight_fk for join, 1 additional fk - passenger_fk
	private List<Flight> flights;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public FlightClass getFlightClass() {
		return flightClass;
	}

	public void setFlightClass(FlightClass flightClass) {
		this.flightClass = flightClass;
	}

	public List<Flight> getFlights() {
		return flights;
	}

	public void setFlights(List<Flight> flights) {
		this.flights = flights;
	}

	@Override
	public String toString() {
		return "Passenger [Id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", dob=" + dob
				+ ", gender=" + gender + "]";
	}

}
