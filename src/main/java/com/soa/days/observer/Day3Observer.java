package com.soa.days.observer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Map;

public class Day3Observer implements PropertyChangeListener, IObserver {
	Map<String, Integer> treeCount = new HashMap<String, Integer>();
	@Override
	public String getReport() {
		StringBuffer strBuffer = new StringBuffer();
		for(String key : treeCount.keySet()) {
			strBuffer.append(String.format("Day3 Observer report %s %d\n", key, treeCount.get(key)));
		}
		return strBuffer.toString();
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		treeCount.put(evt.getPropertyName(), (int)evt.getNewValue());
	}
	
	
}

