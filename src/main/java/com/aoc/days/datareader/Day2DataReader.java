package com.aoc.days.datareader;

import com.aoc.data.model.Password;
import com.aoc.data.model.PasswordData;
import com.aoc.data.structure.DataLinkedList;
import com.aoc.days.visitor.Day2Visitor;
import com.aoc.days.visitor.IDataVisitor;
import com.soa.days.observer.Day2Observer;
import com.soa.days.observer.IObserver;

public class Day2DataReader extends AbstractReader<Password, PasswordData>{
	
	private final Day2Observer observer;
	private final Day2Visitor visitor;
	public Day2DataReader(DataLinkedList<Password, PasswordData> inData) {
		super(inData);
		observer = new Day2Observer();
		visitor = new Day2Visitor();
		visitor.addPropertyChangeListener(observer);
	}

	@Override
	public String getType() {
		return "Password";
	}

	@Override
	protected IDataVisitor<Password, PasswordData> getVisitor() {
		return visitor;
	}

	public IObserver getObserver() {
		return this.observer;
	}
}
