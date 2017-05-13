package com.airline.service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.airline.models.Flight;
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
	@PersistenceContext(unitName = "airline")
	private EntityManager em;

	// for persisting passenger details onto model
	public void addPassenger(Passenger p) {

		em.persist(p);
	}

	// add flight for the passenger
	public void addFlightTicketToPassenger(String flightId, String passengerId) {

		// Get the passenger by Id
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Passenger> cqPassenger = builder.createQuery(Passenger.class);
		Root<Passenger> pRoot = cqPassenger.from(Passenger.class);
		// select passenger only for the Id we provided which is of integer type
		cqPassenger.select(pRoot).where(builder.equal(pRoot.get("id").as(Integer.class), passengerId));

		TypedQuery<Passenger> pQuery = em.createQuery(cqPassenger);
		Passenger p = pQuery.getSingleResult();

		// Get the flight by Id
		builder = em.getCriteriaBuilder();
		CriteriaQuery<Flight> cqFlight = builder.createQuery(Flight.class);
		Root<Flight> fRoot = cqFlight.from(Flight.class);
		// select flight only for the Id we provided which is of integer type
		cqFlight.select(fRoot).where(builder.equal(fRoot.get("id").as(Integer.class), flightId));

		TypedQuery<Flight> fQuery = em.createQuery(cqFlight);
		Flight f = fQuery.getSingleResult();

		// Link flight with passenger
		// get list of flights for the passenger
		List<Flight> fList = p.getFlights();
		fList.add(f);

		// set the list of flights as the new list
		p.setFlights(fList);
		// add to list of passengers for the given flight
		f.getPassengers().add(p);
	}

	// to get list of all passengers
	public List<Passenger> getPassengers() {
		TypedQuery<Passenger> query = em.createQuery("SELECT p from Passenger p", Passenger.class);
		List<Passenger> pList = query.getResultList();
		return pList;
	}

	// to get a passenger by Id
	public Passenger getPassenger(Integer passengerId) {

		// Get the passenger by Id
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Passenger> cqPassenger = builder.createQuery(Passenger.class);
		Root<Passenger> pRoot = cqPassenger.from(Passenger.class);
		// select passenger only for the Id we provided which is of integer type
		cqPassenger.select(pRoot).where(builder.equal(pRoot.get("id").as(Integer.class), passengerId));

		Passenger p = null;

		TypedQuery<Passenger> pQuery = em.createQuery(cqPassenger);
		try {
			p = pQuery.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}

		return p;
	}

}
