package com.airline.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entity implementation class for Entity: Flight
 *
 */
@NamedQuery(name = "Flight.findById", query = "SELECT f from Flight f where f.id = :id")
@Entity

public class Flight implements Serializable {

	private static final long serialVersionUID = 1L;

	public Flight() {
		super();
	}

	// primary key fo the flight entity
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	// to store the enumeration type as a string rather than as an integer
	@Enumerated(EnumType.STRING)
	private FlightDestinations flightOrigin;

	@Enumerated(EnumType.STRING)
	private FlightDestinations flightDestination;

	private Integer price;

	// to store as a timestamp type in database
	@Temporal(TemporalType.TIMESTAMP)
	private Date flightTime;

	// to create a one to one mapping to airplane
	// this will be the foreign key to airplane
	// @OneToOne
	// persist - so that when flight is added, airplane gets added as well
	// remove - when flight is removed, linked airplane also gets removed
	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
	@JoinColumn(name = "airplane_fk")
	private Airplane airplaneDetail;
	
	// one2many since one flight can have multiple pilots
	// mappedBy for creating the reference to pilot (like foreign key)
	@OneToMany(mappedBy = "flightForPilot")
	private List<Pilot> pilots;
	
	// the list of passengers who have been reserved for the flight
	@ManyToMany
	// f_p_join table for the many2many mapping, flight_fk for join, 1 additional fk - passenger_fk
	@JoinTable(name = "f_p_join", joinColumns = @JoinColumn(name = "flight_fk"), inverseJoinColumns = @JoinColumn(name="passenger_fk"))
	private List<Passenger> passengers;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public FlightDestinations getFlightOrigin() {
		return flightOrigin;
	}

	public void setFlightOrigin(FlightDestinations flightOrigin) {
		this.flightOrigin = flightOrigin;
	}

	public FlightDestinations getFlightDestination() {
		return flightDestination;
	}

	public void setFlightDestination(FlightDestinations flightDestination) {
		this.flightDestination = flightDestination;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Date getFlightTime() {
		return flightTime;
	}

	public void setFlightTime(Date flightTime) {
		this.flightTime = flightTime;
	}

	public Airplane getAirplaneDetail() {
		return airplaneDetail;
	}

	public void setAirplaneDetail(Airplane airplaneDetail) {
		this.airplaneDetail = airplaneDetail;
	}

	public List<Pilot> getPilots() {
		return pilots;
	}

	public void setPilots(List<Pilot> pilots) {
		this.pilots = pilots;
	}

	public List<Passenger> getPassengers() {
		return passengers;
	}

	public void setPassengers(List<Passenger> passengers) {
		this.passengers = passengers;
	}
	
	@Override
	public String toString() {
		return "Flight [id=" + id + ", flightOrigin=" + flightOrigin + ", flightDestination=" + flightDestination
				+ ", price=" + price + ", flightTime=" + flightTime + "]";
	}	

}
