package com.aoc.days.visitor;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import com.aoc.data.model.Passport;
import com.aoc.data.model.PassportData;
import com.aoc.data.structure.DataLinkedList;

public class Day4Visitor implements IDataVisitor<Passport, PassportData>{
	private int passportLazyValid = 0;
	private int passportValid = 0;
	private PropertyChangeSupport support;
	
	public Day4Visitor() {
		support = new PropertyChangeSupport(this);
	}
	
	public void addPropertyChangeListener(PropertyChangeListener pcl) {
		support.addPropertyChangeListener(pcl);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener pcl) {
		support.removePropertyChangeListener(pcl);
	}
	
	@Override
	public void report(PassportData inData, DataLinkedList<Passport, PassportData> dataList) {
		if(inData.getValue().isValid()) {
			support.firePropertyChange("lazyPassportValid", passportLazyValid, passportLazyValid+1);
			passportLazyValid +=1;
		}
		
		if(inData.getValue().isValidStrongly()) {
			support.firePropertyChange("strongPassportValid", passportValid, passportValid+1);
			passportValid+=1;
		}
	}

	@Override
	public void update(PassportData inData) {
		// TODO Auto-generated method stub
		
	}

}
