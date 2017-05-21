package com.airline.service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.airline.models.Flight;
import com.airline.models.Pilot;

/**
 * Session Bean implementation class PilotService
 */
@Stateless
@LocalBean
public class PilotService {

    /**
     * Default constructor. 
     */
    public PilotService() {
        // TODO Auto-generated constructor stub
    }
    
    @PersistenceContext(unitName = "airline")
    EntityManager em;
    
    // to add/persist pilot to db
    public void addPilot(Pilot p) {
    	em.persist(p);
    }
    
    // to assign a pilot to a flight
    public void addNewPilotToFlight(Pilot p, String flightId) {
    	
    	// persist (store) pilot to db
    	em.persist(p);
    	
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
    	
    	// TypedQuery<Pilot> pQuery = em.createNamedQuery("Pilot.findById", Pilot.class);
    	// pQuery.setParameter("id", Integer.parseInt(pilotId));
    	// Pilot p = pQuery.getSingleResult();
    	
    	// retrieve all pilots for the flight
    	List<Pilot> pList = f.getPilots();
    	
    	// add new pilot to existing list
    	pList.add(p);
    	f.setPilots(pList);
    	
    	// also assign the flight to the pilot
    	p.setFlightForPilot(f);
    }

}
