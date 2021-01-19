package com.aoc.days.visitor;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import com.aoc.data.model.Data;
import com.aoc.data.model.IData;
import com.aoc.data.model.IntegerData;
import com.aoc.data.structure.DataLinkedList;
import com.aoc.day1.iterator.IListIterator;

public class Day1Visitor implements IDataVisitor<Integer, IntegerData>{
	private Integer two2020[] = {0,0};
	private Integer three2020[] = {0,0,0};
	
	private PropertyChangeSupport support;
	
	public Day1Visitor() {
		support = new PropertyChangeSupport(this);
	}
	
	public void addPropertyChangeListener(PropertyChangeListener pcl) {
		support.addPropertyChangeListener(pcl);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener pcl) {
		support.removePropertyChangeListener(pcl);
	}
	
	public void setProperty(int[] values) {
		
		
	}
	
	public void setTwo2020(Integer[] values) {
		if(values[0]+values[1] == 2020) {
			support.firePropertyChange("two2020", this.two2020, values);
			this.two2020 = values;
		}
	}
	
	public void setThree2020(Integer[] values) {
		if(values[0]+values[1]+values[2] == 2020) {
			support.firePropertyChange("three2020", this.three2020, values);
			this.three2020 = values;
		}
	}
	
	@Override
	public void report(IntegerData inData, DataLinkedList<Integer, IntegerData> dataList) {
		check2to2020(inData, dataList);
		check3to2020(inData, dataList);
	}
	
	private void check2to2020(IntegerData inData, DataLinkedList<Integer, IntegerData> dataList) {
		IListIterator<Integer, IntegerData> iterator = dataList.iterator();
		while(iterator.hasNext()) {
			IData<Integer> data = iterator.next();
			setTwo2020(new Integer[]{inData.getValue(), data.getValue()});
		}
	}
	
	private void check3to2020(IntegerData inData, DataLinkedList<Integer, IntegerData> dataList) {
		IListIterator<Integer, IntegerData> iterator1 = dataList.iterator();
		while(iterator1.hasNext()) {
			IData<Integer> data1 = iterator1.next();
			IListIterator<Integer, IntegerData> iterator2 = dataList.iterator();
			while(iterator2.hasNext()) {
				IData<Integer> data2 = iterator2.next();
				if (data1 != null && data2 != null && data1.getValue() != data2.getValue()) {
					setThree2020(new Integer[] {inData.getValue(), data1.getValue(), data2.getValue()});
				}
			}
		}
	}

	@Override
	public void update(IntegerData inData) {
		
	}

}
