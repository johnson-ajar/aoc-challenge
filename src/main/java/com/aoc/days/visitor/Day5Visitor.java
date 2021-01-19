package com.aoc.days.visitor;

import java.beans.PropertyChangeSupport;

import com.aoc.data.model.Ticket;
import com.aoc.data.model.TicketData;
import com.aoc.data.structure.DataLinkedList;

public class Day5Visitor extends BaseVisitor<Ticket, TicketData>{
	
	public Day5Visitor() {
		super();
	}
	
	
	@Override
	public void report(TicketData inData, DataLinkedList<Ticket, TicketData> dataList) {
		//int row = findRow(0, inData, 0, 127);
		//int seat = findSeat(0, inData, 0, 7);
		inData.accept(this);
	}
	
	
	@Override
	public void update(TicketData inData) {
		int row = findRow(0, inData, 0, 127);
		int seat = findSeat(0, inData, 0, 7);
		inData.setRow(row);
		inData.setSeat(seat);
		//System.out.println(inData.getSeatID());
	}
	
	private int findRow(int rPos, TicketData inData, int sRow, int eRow) {
		if(rPos > 6) {
			return sRow;
		}
		String rowRef = inData.getValue().get(rPos);
		
		if (rowRef.equals("F")) {
			eRow = sRow+(eRow-sRow)/2;
		} else if (rowRef.equals("B")) {
			sRow = eRow-(eRow-sRow)/2;
		}
		//System.out.println(String.format("%d %s %d %d", rPos, rowRef, sRow, eRow));
		return findRow(rPos+1, inData, sRow, eRow);
	}
	
	private int findSeat(int sPos, TicketData inData, int sSeat, int eSeat) {
		if (sPos > 2) {
			return sSeat;
		}
		
		String seatRef = inData.getValue().get(7+sPos);
		if(seatRef.equals("L")) {
			eSeat = sSeat+(eSeat-sSeat)/2;
		} else if (seatRef.equals("R")) {
			sSeat = eSeat - (eSeat - sSeat)/2;
		}
		//System.out.println(String.format("%d %s %d %d", sPos, seatRef, sSeat, eSeat));
		return findSeat(sPos+1, inData, sSeat, eSeat);
	}
}
