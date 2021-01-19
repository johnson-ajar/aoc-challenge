package com.aoc.days.datareader;

import com.aoc.data.model.IntegerData;
import com.aoc.data.structure.DataLinkedList;
import com.aoc.days.visitor.Day1Visitor;
import com.aoc.days.visitor.IDataVisitor;
import com.soa.days.observer.Day1Observer;

public class Day1DataReader extends AbstractReader<Integer, IntegerData> {
	
	private final Day1Observer observer;
	private final Day1Visitor visitor;
	public Day1DataReader(DataLinkedList<Integer, IntegerData> inData) {
		super(inData);
		observer = new Day1Observer();
		visitor = new Day1Visitor();
		visitor.addPropertyChangeListener(observer);
	}

	@Override
	public String getType() {
		return "Integer";
	}

	@Override
	protected IDataVisitor<Integer, IntegerData> getVisitor() {
		return visitor;
	}
	
}
