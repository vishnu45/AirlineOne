package com.airline.webservices.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import com.airline.models.Flight;
import com.airline.service.FlightService;

@Path("/flights")
public class FlightsWebService {

	// to interact with the datasoruce we configured on the glassfish derby server
	@PersistenceContext(unitName = "airline")
	EntityManager em;
	
	@EJB
	FlightService fs;
	
	// for jax-rs webserivces context
	// useful for creating URLs to resources on the server (ex: flight resource)
	@Context
	UriInfo fUriInfo;
	
	public FlightsWebService() {
		
	}
	
	// to access this method through get request
	@GET
	// to return JSON type
	@Produces(MediaType.APPLICATION_JSON)
	public List<Flight> getFlights() {
		
		List<Flight> fList = fs.getFlights();
		
		return fList;
	}
	
}
