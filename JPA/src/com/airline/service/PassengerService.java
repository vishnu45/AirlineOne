package com.airline.service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.airline.models.Passenger;

/**
 * Session Bean implementation class PassengerService
 */
@Stateless
@LocalBean
public class PassengerService {

    /**
     * Default constructor. 
     */
    public PassengerService() {
        // TODO Auto-generated constructor stub
    }
    
    // unit name from the JPA persistance file
    @PersistenceContext(unitName="airline")
    private EntityManager em;
    
    // for persisting passenger details onto model
    public void addPassenger(Passenger p) {
    	
    	em.persist(p);
    }
    
    // add flight for the passenger
    public void addFlightTicketToPassenger(String flightId, String passengerId) {
    	
    }
    
    // to get list of all passengers
    public List<Passenger> getPassengers() {
    	TypedQuery<Passenger> query = em.createQuery("SELECT p from Passenger p",Passenger.class);
    	List<Passenger> pList = query.getResultList();
    	return pList;
    }

}
