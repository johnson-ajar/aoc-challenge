package com.soa.days.observer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




public class Day1Observer implements PropertyChangeListener, IObserver {
	private static final Logger logger = LoggerFactory.getLogger(Day1Observer.class);
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		logger.debug("The property has been changed");
	}
	
	@Override
	public String getReport() {
		return "";
	}

}
