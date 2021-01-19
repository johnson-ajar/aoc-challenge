package com.aoc.days.datareader;

import com.aoc.data.model.Passport;
import com.aoc.data.model.PassportData;
import com.aoc.data.structure.DataLinkedList;
import com.aoc.days.visitor.Day4Visitor;
import com.aoc.days.visitor.IDataVisitor;
import com.soa.days.observer.Day4Observer;
import com.soa.days.observer.IObserver;

public class Day4DataReader extends AbstractReader<Passport, PassportData>{
	private final Day4Visitor visitor;
	private final Day4Observer observer;
	public Day4DataReader(DataLinkedList<Passport, PassportData> inData) {
		super(inData);
		visitor = new Day4Visitor();
		observer = new Day4Observer();
		visitor.addPropertyChangeListener(observer);
	}

	@Override
	public String getType() {
		return "Passport";
	}

	@Override
	protected IDataVisitor<Passport, PassportData> getVisitor() {
		// TODO Auto-generated method stub
		return visitor;
	}
	
	public IObserver getObserver() {
		return this.observer;
	}
}
