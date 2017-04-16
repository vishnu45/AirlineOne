package com.airline.controllers;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.airline.models.Passenger;

/**
 * Application Lifecycle Listener implementation class AirlineListener
 *
 */
@WebListener
public class AirlineListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public AirlineListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent event)  { 
         
    	// get the servlet context
    	ServletContext sc = event.getServletContext();
    	
    	// initialize the passenger list
    	ArrayList<Passenger> pList = (ArrayList<Passenger>) sc.getAttribute("passengers");
    	
    	// check if the list is empty
    	if (pList == null) {
    		System.out.println("No passenger list created. Creating new list ...");
    		
    		pList = new ArrayList<Passenger>();
    		sc.setAttribute("passengers", pList);
    	}
    	
    	
    }
	
}
