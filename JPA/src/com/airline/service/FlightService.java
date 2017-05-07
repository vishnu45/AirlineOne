package com.airline.service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.airline.models.Airplane;
import com.airline.models.Flight;
import com.airline.models.Passenger;

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
    	
    	// Get the passenger by Id
    	CriteriaBuilder builder = em.getCriteriaBuilder();
    	CriteriaQuery<Passenger> cqPassenger = builder.createQuery(Passenger.class);    	
    	Root<Passenger> pRoot = cqPassenger.from(Passenger.class);
    	// select passenger only for the Id we provided which is of integer type
    	cqPassenger.select(pRoot).where(builder.equal(pRoot.get("Id").as(Integer.class), passengerId));
    	
    	TypedQuery<Passenger> pQuery = em.createQuery(cqPassenger);
    	Passenger p = pQuery.getSingleResult();
    	
    	// Get the flight by Id
    	// CriteriaBuilder builder = em.getCriteriaBuilder();
    	CriteriaQuery<Flight> cqFlight = builder.createQuery(Flight.class);    	
    	Root<Flight> fRoot = cqFlight.from(Flight.class);
    	// select flight only for the Id we provided which is of integer type
    	cqFlight.select(fRoot).where(builder.equal(fRoot.get("Id").as(Integer.class), flightId));
    	
    	TypedQuery<Flight> fQuery = em.createQuery(cqFlight);
    	Flight f = fQuery.getSingleResult();
    	
    	// Link passenger with flight
    	// get list of passengers for the flight
    	List<Passenger> pList = f.getPassengers();
    	pList.add(p);
    	
    	// set the list of passengers as the new list
    	f.setPassengers(pList);
    	// add to list of flights for the given passenger
    	p.getFlights().add(f);
    	
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
