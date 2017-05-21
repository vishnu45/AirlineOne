package com.airline.controllers;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.airline.service.PassengerService;

/**
 * Servlet implementation class AddFlightTicketToPassenger
 */
@WebServlet("/AddFlightTicketToPassenger")
public class AddFlightTicketToPassenger extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	PassengerService ps;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddFlightTicketToPassenger() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// get passenger and flight from the jsp fancyform
		String fid = request.getParameter("fid");
		String pid = request.getParameter("pid");
		
		// add the passenger to the 
		ps.addFlightTicketToPassenger(fid, pid);
		
		// once the passenger is added to flight, redirect to flight list
		response.sendRedirect("Passengers");
		
	}

}
