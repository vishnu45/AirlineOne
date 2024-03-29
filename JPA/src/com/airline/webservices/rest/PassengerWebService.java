package com.airline.webservices.rest;

import java.net.URI;
import java.util.List;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import com.airline.models.Passenger;
import com.airline.service.PassengerService;

@Path("/passengers")
@Transactional
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

	@GET
	@Path("{passenger_id}")
	@Produces(MediaType.APPLICATION_XML)
	public Passenger getPassenger(@PathParam("passenger_id") Integer passengerId) {

		Passenger p = ps.getPassenger(passengerId);

		// if passengerId is invalid
		if (p == null) {
			// throw not found jaxrs exception
			throw new NotFoundException("The passenger with the id " + passengerId + " was not found");
		}

		return p;
	}

	@POST
	@Consumes(MediaType.APPLICATION_XML)
	// to add a passenger
	public Response addPassenger(Passenger p) {

		p = ps.addPassenger(p);
		// to build a URL path to the passenger which was created
		UriBuilder pUriBuilder = pUriInfo.getAbsolutePathBuilder();
		URI pUri = pUriBuilder.path(String.valueOf(p.getId())).build();

		return Response.created(pUri).build();
	}

	// to update - PUT
	@PUT
	// to get the passengerId as query string
	@Path("/edit/{pId}")
	// to consume the update data
	@Consumes(MediaType.APPLICATION_XML)
	// to update a passenger
	public Response updatePassenger(@PathParam("pId") Integer passengerId, Passenger pUpdated) {

		pUpdated = ps.updatePassenger(passengerId, pUpdated);

		// however for invalid passengerId
		if (pUpdated == null) {
			throw new NotFoundException("The passenger with an id " + passengerId + " was not found");
		}

		// to build a URL path to the passenger which was created
		UriBuilder pUriBuilder = pUriInfo.getAbsolutePathBuilder();
		URI pUri = pUriBuilder.path(String.valueOf(pUpdated.getId())).build();

		return Response.created(pUri).build();
	}

	// #2 - alternate way
	// to update - PUT
	@PUT
	// to get the passengerId as query string
	@Path("/edit2/{pId}")
	// to consume the update data
	@Consumes(MediaType.APPLICATION_XML)
	// to update a passenger
	public Response updatePassenger2(@PathParam("pId") Integer passengerId, Passenger pUpdated) {

		pUpdated = ps.updatePassenger2(passengerId, pUpdated);

		// however for invalid passengerId
		if (pUpdated == null) {
			throw new NotFoundException("The passenger with an id " + passengerId + " was not found");
		}

		// to build a URL path to the passenger which was created
		UriBuilder pUriBuilder = pUriInfo.getAbsolutePathBuilder();
		URI pUri = pUriBuilder.path(String.valueOf(pUpdated.getId())).build();

		return Response.created(pUri).build();
	}
	
	@DELETE
	@Path("{passenger_id}")
	// to delete a passenger
	public Response deletePassenger(@PathParam("passenger_id") Integer passengerId) {
		
		Passenger passengerToRemove = em.find(Passenger.class, passengerId);
		
		if (passengerToRemove == null) {
			throw new NotFoundException("The passenger with id " + passengerId + " was not found");
		}
		// remove passenger from em context
		em.remove(passengerToRemove);
		
		return Response.noContent().build();
	}

}
