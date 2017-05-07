package com.airline.service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.airline.models.Airplane;
import com.airline.models.Flight;
import com.airline.models.Pilot;

/**
 * Session Bean implementation class FlightService
 */
@Stateless
@LocalBean
public class FlightService {

    /**
     * Default constructor. 
     */
    public FlightService() {
        // TODO Auto-generated constructor stub
    }
    
    // the entity manager will be able to use the airline persistence unit
    // and will be able to connect to db thru this (by using attributes given
    // in persistence.xml
    @PersistenceContext(unitName = "airline")
    EntityManager em;
    
    // persisting airplane and flight data onto db
    public void addFlight(Flight f, Airplane a) {
    	em.persist(f);
    	// em.persist(a); // propogated and cascaded from flight and saved automatically
    }
    
    // to add passengers to a flight
    public void addPassengerToFlight(String passengerId, String flightId) {
    	
    }
    
    // to get list of all flights present in the system
    public List<Flight> getFlights() {
    	
    	// create a named query- to retrieve all flight objects
    	// here we use createQuery instead of createNamedQuery since we are building from scratch
    	TypedQuery<Flight> query = em.createQuery("SELECT f FROM Flight f", Flight.class);
    	List<Flight> results = query.getResultList();    	
    	return results;
    }

}
