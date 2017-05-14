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
	public Passenger addPassenger(Passenger p) {
		em.persist(p);
		return p;
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

	// to update a passenger
	public Passenger updatePassenger(Integer passengerId, Passenger pUpdated) {

		Passenger p = em.find(Passenger.class, passengerId);

		// if passenger not exits
		if (p == null) {
			return null;
		}

		// check if firstName is to be updated, then update firsname
		if (pUpdated.getFirstName() != null) {
			p.setFirstName(pUpdated.getFirstName());
		}
		// check if lastName is to be updated, then update lastname
		if (pUpdated.getLastName() != null) {
			p.setLastName(pUpdated.getLastName());
		}
		// check if dob is to be updated, then update dob
		if (pUpdated.getDob() != null) {
			p.setDob(pUpdated.getDob());
		}
		// check if gender is to be updated, then update gender
		if (pUpdated.getGender() != null) {
			p.setGender(pUpdated.getGender());
		}

		return p;
	}

	// to update a passenger
	public Passenger updatePassenger2(Integer passengerId, Passenger pUpdated) {

		pUpdated.setId(passengerId);

		// for an existing passenger
		Passenger pCheckExist = em.find(Passenger.class, passengerId);

		// if passenger not exits
		if (pCheckExist == null) {
			return null;
		}

		// uUpdated goes from being a POJO to be merged into the persistence
		// context and starts being manager by the entity manager
		em.merge(pUpdated);

		return pCheckExist;
	}
	
	// to delete a passenger
	

}
