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

import com.airline.models.Passenger;
import com.airline.service.PassengerService;

@Path("/passengers")
public class PassengerWebService {

	// to interact with the datasoruce we configured on the glassfish derby
	// server
	@PersistenceContext(unitName = "airline")
	EntityManager em;

	@EJB
	PassengerService ps;

	// for jax-rs webserivces context
	// useful for creating URLs to resources on the server (ex: flight resource)
	@Context
	UriInfo pUriInfo;

	public PassengerWebService() {

	}

	// to access this method through get request
	@GET
	// to return JSON type
	@Produces(MediaType.APPLICATION_XML)
	public List<Passenger> getPassengers() {

		List<Passenger> pList = ps.getPassengers();

		return pList;
	}

}
