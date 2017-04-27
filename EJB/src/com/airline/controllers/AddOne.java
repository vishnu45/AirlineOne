package com.airline.controllers;


import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.airline.service.CounterBean;
import com.airline.service.CounterStatefulBean;

/**
 * Servlet implementation class AddOne
 */
@WebServlet("/AddOne")
public class AddOne extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	// singleton bean
	@EJB
	CounterBean cb;
	
	// stateful bean
	// @EJB
	// CounterStatefulBean cbStateful;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddOne() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		// for stateful bean instead of injecting bean here, use bean from the session listener
		HttpSession s = request.getSession();
		
		PrintWriter out = response.getWriter();
		
		// show present count and value after adding 1
		out.println("SINGLETON - Value of count: " + cb.getCount());		
		cb.addOneToCount();		
		out.println("SINGLETON - Increased value of count: " + cb.getCount());
		
		// for stateful bean - bean lives within the servlett life
		// out.println("STATEFUL - Value of count: " + cbStateful.getCount());		
		// cbStateful.addOneToCount();		
		// out.println("STATEFUL - Increased value of count: " + cbStateful.getCount());
		
		CounterStatefulBean cbStateful = (CounterStatefulBean) s.getAttribute("cbStateful");
		out.println("STATEFUL - Value of count: " + cbStateful.getCount());
		cbStateful.addOneToCount();
		out.println("STATEFUL - Increased value of count: " + cbStateful.getCount());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
