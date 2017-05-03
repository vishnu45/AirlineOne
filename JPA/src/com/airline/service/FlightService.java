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
    
    // to assign a pilot to a flight
    public void addPilotToFlight(String pilotId, String flightId) {
    	// use the named queries
    	// arg1 - namedQuery, arg2 - class name of flight entity
    	// based on the flightId passed into the method, the flight object is pulled up
    	TypedQuery<Flight> fQuery = em.createNamedQuery("Flight.findById", Flight.class);
    	
    	// set the id parameter in the namedQuery with that of the flight being passed
    	// to retrieve the exact flight
    	// parse the string flightId to Integer
    	fQuery.setParameter("id", Integer.parseInt(flightId));
    	
    	// get the flight object for the id passed
    	Flight f = fQuery.getSingleResult();
    	
    	TypedQuery<Pilot> pQuery = em.createNamedQuery("Pilot.findById", Pilot.class);
    	pQuery.setParameter("id", Integer.parseInt(pilotId));
    	Pilot p = pQuery.getSingleResult();
    	
    	// retrieve all pilots for the flight
    	List<Pilot> pList = f.getPilots();
    	
    	// add new pilot to existing list
    	pList.add(p);
    	f.setPilots(pList);
    	
    	// also assign the flight to the pilot
    	p.setFlightForPilot(f);
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
