package com.airline.service;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;

/**
 * Session Bean implementation class CounterBean
 */
@Stateful
@LocalBean
public class CounterStatefulBean {

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
    public CounterStatefulBean() {
        // TODO Auto-generated constructor stub
    }
    
    public void addOneToCount() {
    	this.count += 1;
    }

}
