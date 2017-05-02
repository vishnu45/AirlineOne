package com.airline.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Entity implementation class for Entity: Pilot
 *
 */
@Entity

public class Pilot implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public Pilot() {
		super();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Id;
	
	private String firstName;
	
	private String lastName;
	
	private Integer pilotLicense;
	
	@Enumerated(EnumType.STRING)
	private PilotRank pilotRank;
	
	// many pilots can be assigned to a single flight
	@ManyToOne
	// flight is reference from the flight entity (so foreign key from pilot)
	@JoinColumn(name = "flight_fk")
	private Flight flightForPilot;

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
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

	public Integer getPilotLicense() {
		return pilotLicense;
	}

	public void setPilotLicense(Integer pilotLicense) {
		this.pilotLicense = pilotLicense;
	}

	public PilotRank getPilotRank() {
		return pilotRank;
	}

	public void setPilotRank(PilotRank pilotRank) {
		this.pilotRank = pilotRank;
	}

	public Flight getFlightForPilot() {
		return flightForPilot;
	}

	public void setFlightForPilot(Flight flightForPilot) {
		this.flightForPilot = flightForPilot;
	}

	@Override
	public String toString() {
		return "Pilot [Id=" + Id + ", firstName=" + firstName + ", lastName=" + lastName + ", pilotLicense="
				+ pilotLicense + ", pilotRank=" + pilotRank + ", flightForPilot=" + flightForPilot + "]";
	}
	
}
