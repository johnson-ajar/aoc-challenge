package com.soa.days.observer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Day4Observer implements PropertyChangeListener, IObserver{
	
	private int lazyValidPassport  = 0;
	private int strongValidPassport = 0;
	@Override
	public String getReport() {
		return String.format("Lazy Valid Passport %d \n Strong Valid Passport %d \n", lazyValidPassport, strongValidPassport);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getPropertyName().equals("lazyPassportValid")) {
			lazyValidPassport = ((Integer)evt.getNewValue()).intValue();
		}else if(evt.getPropertyName().equals("strongPassportValid")) {
			strongValidPassport = ((Integer)evt.getNewValue()).intValue();
		}
	}

}
