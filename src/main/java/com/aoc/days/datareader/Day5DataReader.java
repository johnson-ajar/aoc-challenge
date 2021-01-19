package com.aoc.days.datareader;

import com.aoc.data.model.Ticket;
import com.aoc.data.model.TicketData;
import com.aoc.data.structure.DataLinkedList;
import com.aoc.days.visitor.Day5Visitor;
import com.aoc.days.visitor.IDataVisitor;
import com.soa.days.observer.IObserver;

public class Day5DataReader extends AbstractReader<Ticket, TicketData>{
	
	private final Day5Visitor visitor;
	public Day5DataReader(DataLinkedList<Ticket, TicketData> inData) {
		super(inData);
		visitor = new Day5Visitor();
	}
	@Override
	public String getType() {
		return "Ticket";
	}
	@Override
	protected IDataVisitor<Ticket, TicketData> getVisitor() {
		return this.visitor;
	}
	
	public IObserver getObserver() {
		return null;
	}
}
