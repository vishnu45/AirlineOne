package com.airline.controllers;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.airline.service.FlightService;

/**
 * Servlet implementation class AddPassengerToFlight
 */
@WebServlet("/AddPassengerToFlight")
public class AddPassengerToFlight extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	FlightService fs;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddPassengerToFlight() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// get passenger and flight from the jsp fancyform
		String fid = request.getParameter("fid");
		String pid = request.getParameter("pid");
		
		// add passenger to flight
		fs.addPassengerToFlight(pid, fid);
		
		// after adding, redirect to flight list
		response.sendRedirect("Flights");
		
	}

}
