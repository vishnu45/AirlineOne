package com.airline.controllers;


import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.airline.service.CounterBean;

/**
 * Servlet implementation class AddOne
 */
@WebServlet("/AddOne")
public class AddOne extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@EJB
	CounterBean cb;
	
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

		PrintWriter out = response.getWriter();
		
		out.println("Value of count: " + cb.getCount());
		
		cb.addOneToCount();
		
		out.println("Increased value of count: " + cb.getCount());		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
