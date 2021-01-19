package com.soa.days.observer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Day2Observer implements PropertyChangeListener, IObserver {
	
	private int validByCharCount = 0;
	private int validByCharPos = 0;
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getPropertyName().equals("charOccurence")) {
			validByCharCount +=1;
		} else if (evt.getPropertyName().equals("charPosition")) {
			validByCharPos +=1;
		}
	}
	
	
	@Override
	public String getReport() {
		return String.format("Valid password by char count: %d \n Valid password by char occurence: %d \n", validByCharCount, validByCharPos);
	}

}
