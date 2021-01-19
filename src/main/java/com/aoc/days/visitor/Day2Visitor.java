package com.aoc.days.visitor;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import com.aoc.data.model.Password;
import com.aoc.data.model.PasswordData;
import com.aoc.data.structure.DataLinkedList;

public class Day2Visitor implements IDataVisitor<Password, PasswordData>{
	private PropertyChangeSupport support;
	
	public Day2Visitor() {
		support = new PropertyChangeSupport(this);
	}
	
	
	public void addPropertyChangeListener(PropertyChangeListener pcl) {
		support.addPropertyChangeListener(pcl);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener pcl) {
		support.removePropertyChangeListener(pcl);
	}
	
	public void checkCharOccurences(PasswordData inData) {
		Password password = inData.getValue();
		int count = 0;
		for(char c : password.getPassword().toCharArray()) {
			if(c == inData.getValue().getAnchor()) {
				count+=1;
			}
		}
		if(count >= password.minOccur() && count <= password.maxOccur()) {
			support.firePropertyChange("charOccurence", false, true);
		} 
		
	}
	
	private void checkCharPosition(PasswordData inData) {
		Password password = inData.getValue();
		boolean pos1Char  = false;
		boolean pos2Char = false;
		if(password.getPassword().charAt(password.minOccur()-1) == password.getAnchor()) {
			pos1Char = true;
		}
		if(password.getPassword().charAt(password.maxOccur()-1) == password.getAnchor()) {
			pos2Char = true;
		}
		
		if((pos1Char && !pos2Char) || (!pos1Char && pos2Char)) {
			support.firePropertyChange("charPosition", false, true);
		}
	}
	
	@Override
	public void report(PasswordData inData, DataLinkedList<Password, PasswordData> dataList) {
		checkCharOccurences(inData);
		checkCharPosition(inData);
	}


	@Override
	public void update(PasswordData inData) {
		// TODO Auto-generated method stub
		
	}
	
}
