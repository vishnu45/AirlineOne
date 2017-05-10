package com.airline.webservices.rest;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import com.airline.service.PassengerService;

@Path("/flights")
public class FlightsWebService {

	// to interact with the datasoruce we configured on the glassfish derby server
	@PersistenceContext(unitName = "airline")
	EntityManager em;
	
	@EJB
	PassengerService ps;
	
	// for jax-rs webserivces context
	// useful for creating URLs to resources on the server (ex: flight resource)
	@Context
	UriInfo fUriInfo;
	
	public FlightsWebService() {
		
	}
	
}
