package com.airline.service;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

}
