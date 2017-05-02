package com.airline.service;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

}
