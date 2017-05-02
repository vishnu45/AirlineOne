package com.airline.service;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.airline.models.Airplane;
import com.airline.models.Flight;

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
    	em.persist(a);
    }

}
