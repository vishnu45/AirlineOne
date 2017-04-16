package com.airline.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.airline.models.Gender;
import com.airline.models.Passenger;

/**
 * Servlet implementation class AddPassenger
 */
@WebServlet("/AddPassenger")
public class AddPassenger extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddPassenger() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());

		// initialize the fields on the passenger add form to null
		request.setAttribute("first_name", "");
		request.setAttribute("last_name", "");
		request.setAttribute("dob", "");

		// request for the add passenger main page
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/add_passenger.jsp");

		// forward the request to the JSP and JSP will write the HTML to the
		// response
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// initializing errors attribute for the request
		// the response context will live only for this one request
		request.setAttribute("errors", false);

		Passenger p = new Passenger();

		// to get the passenger details from the passenger form in
		// add_passenger.jsp
		// use the name attribute to identify the field
		String firstName = request.getParameter("first-name");
		System.out.println(firstName);

		// check if first name is empty
		// we can add the error value to a new request attribute which can be
		// used later
		// for a an overall error validation
		if (firstName.length() == 0) {
			System.out.println("empty first name");
			request.setAttribute("errors", true);
			request.setAttribute("firstName_errors", true);
			request.setAttribute("first_name", "");
		} else {
			p.setFirstName(firstName);
			// add to request context so that we can persist it on the form,
			// even if error occurs
			request.setAttribute("first_name", firstName);
		}

		// process last name
		String lastName = request.getParameter("last-name");
		// check if last name is empty
		if (lastName.length() == 0) {
			System.out.println("empty last name");
			request.setAttribute("errors", true);
			request.setAttribute("lastName_errors", true);
			request.setAttribute("last_name", "");
		} else {
			p.setLastName(lastName);
			// add to request context so that we can persist it on the form,
			// even if error occurs
			request.setAttribute("last_name", lastName);
		}

		String dob_raw = request.getParameter("dob");
		String dobArray[] = dob_raw.split("\\/");

		// regex for date field
		String pattern = "^\\d{1,2}\\/\\d{1,2}\\/\\d{4}$";
		Pattern r = Pattern.compile(pattern);

		Matcher m = r.matcher(dob_raw);
		if (m.find()) {
			String month = dobArray[0];
			String day = dobArray[1];
			String year = dobArray[2];

			Calendar cal = Calendar.getInstance();

			cal.set(Calendar.YEAR, Integer.parseInt(year));
			cal.set(Calendar.MONTH, Integer.parseInt(month));
			cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(year));

			Date dob = cal.getTime();
			System.out.println(dob);

			// add to request context
			request.setAttribute("dob", dob_raw);

			p.setDob(dob);
		} else {
			System.out.println("Invalid date of birth");
			request.setAttribute("errors", true);
			request.setAttribute("date_format_error", true);
			// persist dob even if incorrect on the form
			request.setAttribute("dob", dob_raw);
			// check if the dob is empty, and persist empty dob on form
			if (dob_raw.length() == 0) {
				request.setAttribute("dob", "");
			}
		}

		String gender = request.getParameter("gender");
		System.out.println(gender);
		p.setGender(Gender.valueOf(gender));

		// check for errors from the form collected
		if ((Boolean) request.getAttribute("errors")) {
			RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/add_passenger.jsp");
			view.forward(request, response);
		} else {

			// for remembering data from this servlet context
			ServletContext sc = this.getServletContext();

			// this part should be thread safe, so sequential execution should
			// be maintained
			synchronized (this) {
				// get existing passenger list from the servlet context
				ArrayList<Passenger> pList = (ArrayList<Passenger>) sc.getAttribute("passengers");
				pList.add(p);

				// set an attribute of the servlet context with the passenger
				// list data
				// this will live as long as the application is alive
				sc.setAttribute("passengers", pList);
			}

			response.sendRedirect("");
		}
	}

}
