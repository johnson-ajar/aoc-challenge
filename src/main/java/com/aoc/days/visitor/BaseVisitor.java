package com.aoc.days.visitor;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import com.aoc.data.model.Data;

public abstract class BaseVisitor<T,D extends Data<T>> implements IDataVisitor<T, D> {
	private PropertyChangeSupport support;
	
	public BaseVisitor() {
		support = new PropertyChangeSupport(this);
	}
	
	public void addPropertyChangeListener(PropertyChangeListener pcl) {
		support.addPropertyChangeListener(pcl);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener pcl) {
		support.removePropertyChangeListener(pcl);
	}
}
