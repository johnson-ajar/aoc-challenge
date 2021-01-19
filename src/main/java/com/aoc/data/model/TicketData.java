package com.aoc.data.model;

import com.aoc.days.visitor.IDataVisitor;

public class TicketData extends Data<Ticket> {
	private int index;
	private int row;
	private int seat;
	
	private int seatID;
	public TicketData(int index, String inData) {
		super(inData);
		this.index = index;
	}
	
	public TicketData(int index, Ticket inData) {
		super(inData);
		this.index = index;
	}

	@Override
	protected Ticket getData(String inData) {
		Ticket ticket = new Ticket();
		for (char c : inData.toCharArray()) {
			ticket.add(String.valueOf(c));
		}
		return ticket;
	}
	
	public void setRow(int row) {
		this.row = row;
	}
	
	public void setSeat(int seat) {
		this.seat = seat;
	}
	
	public int getSeatID() {
		return this.row*8+seat;
	}
	
	@Override
	public int compare(IData<Ticket> inValue) {
		TicketData inData = (TicketData)inValue;
		return inData.getSeatID() >= this.getSeatID() ? 1: 0;
	}

	@Override
	public <D extends Data<Ticket>> void accept(IDataVisitor<Ticket, D> iDataVisitor) {
		iDataVisitor.update((D)this);
	}
	
	@Override
	public String toString() {
		String output = String.format("%d %d %d %d %s", index, row, seat, this.getSeatID(), super.toString());
		return output;
	}
}
