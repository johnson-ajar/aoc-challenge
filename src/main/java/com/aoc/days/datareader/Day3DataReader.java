package com.aoc.days.datareader;

import com.aoc.data.model.Road;
import com.aoc.data.model.RoadData;
import com.aoc.data.structure.DataLinkedList;
import com.aoc.days.visitor.Day3Visitor;
import com.aoc.days.visitor.IDataVisitor;
import com.soa.days.observer.Day3Observer;
import com.soa.days.observer.IObserver;

public class Day3DataReader extends AbstractReader<Road, RoadData>{
	private final Day3Observer observer;
	private final Day3Visitor visitor;
	public Day3DataReader(DataLinkedList<Road, RoadData> inData) {
		super(inData);
		observer = new Day3Observer();
		visitor = new Day3Visitor();
		visitor.addPropertyChangeListener(observer);
	}

	@Override
	public String getType() {
		return "Road";
	}

	@Override
	protected IDataVisitor<Road, RoadData> getVisitor() {
		return visitor;
	}
	
	public IObserver getObserver() {
		return this.observer;
	}
}
