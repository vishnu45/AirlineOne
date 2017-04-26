package com.airline.service;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;

/**
 * Session Bean implementation class CounterBean
 */
@Singleton
@LocalBean
public class CounterBean {

    private Integer count = 0;
	
	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	/**
     * Default constructor. 
     */
    public CounterBean() {
        // TODO Auto-generated constructor stub
    }
    
    public void addOneToCount() {
    	this.count += 1;
    }

}
