package com.aoc.data.main;

import com.aoc.data.model.Expression;
import com.aoc.data.model.ExpressionData;
import com.aoc.data.model.Instruction;
import com.aoc.data.model.InstructionData;
import com.aoc.data.model.IntegerData;
import com.aoc.data.model.Navigation;
import com.aoc.data.model.NavigationData;
import com.aoc.data.model.Passport;
import com.aoc.data.model.PassportData;
import com.aoc.data.model.Password;
import com.aoc.data.model.PasswordData;
import com.aoc.data.model.Road;
import com.aoc.data.model.RoadData;
import com.aoc.data.model.Ticket;
import com.aoc.data.model.TicketData;
import com.aoc.data.structure.DataLinkedList;
import com.aoc.data.structure.SortedDataList;
import com.aoc.day1.iterator.IListIterator;
import com.aoc.days.datareader.Day12DataReader;
import com.aoc.days.datareader.Day18DataReader;
import com.aoc.days.datareader.Day1DataReader;
import com.aoc.days.datareader.Day2DataReader;
import com.aoc.days.datareader.Day3DataReader;
import com.aoc.days.datareader.Day4DataReader;
import com.aoc.days.datareader.Day5DataReader;
import com.aoc.days.datareader.Day8DataReader;
import com.aoc.days.operators.Day8Operator;

public class AocReport {
	
	public void generateDay1Report() {
		DataLinkedList<Integer, IntegerData> dataList = new SortedDataList<Integer, IntegerData>();
		Day1DataReader reader = new Day1DataReader(dataList);
		reader.read("day1_input_data.txt", false);
	}
	
	public void generateDay2Report() {
		DataLinkedList<Password, PasswordData> dataList = new DataLinkedList<Password, PasswordData>();
		Day2DataReader reader = new Day2DataReader(dataList);
		reader.read("day2_input_data.txt", false);
		System.out.println(reader.getObserver().getReport());
	}
	
	public void generateDay3Report() {
		DataLinkedList<Road, RoadData> roadList =new DataLinkedList<Road, RoadData>();
		Day3DataReader reader = new Day3DataReader(roadList);
		reader.read("day3_input_data.txt", false);
		System.out.println(reader.getObserver().getReport());
	}
	
	public void generateDay4Report() {
		DataLinkedList<Passport, PassportData> roadList = new DataLinkedList<Passport, PassportData>();
		Day4DataReader reader = new Day4DataReader(roadList);
		reader.read("day4_input_data.txt", true);
		System.out.println(reader.getObserver().getReport());
	}
	
	public void generateDay5Report() {
		DataLinkedList<Ticket, TicketData> ticketList = new SortedDataList<Ticket, TicketData>();
		Day5DataReader reader = new Day5DataReader(ticketList);
		reader.read("day5_input_data.txt", false);
		IListIterator<Ticket, TicketData> iterator = ticketList.iterator();
		int size = 0;
		
		while(iterator.hasNext()) {
			TicketData tdata = (TicketData)iterator.next();
			//System.out.println(((TicketData)iterator.next()));
			if(size > 0 && ((TicketData)tdata.prev()).getSeatID() - tdata.getSeatID() == -2) {
				System.out.println(tdata.prev());
				System.out.println(tdata);
				System.out.println(tdata.next());
				
			}
			size+=1;
		}
		
	}
	
	public void generateDay12Report() {
		DataLinkedList<Navigation, NavigationData> navigationList = new SortedDataList<Navigation, NavigationData>();
		Day12DataReader reader = new Day12DataReader(navigationList);
		reader.read("day12_input_data.txt", false);
	}
	
	public void generateDay8Report() {
		DataLinkedList<Instruction, InstructionData> instructionList = new DataLinkedList<Instruction, InstructionData>();
		Day8DataReader reader = new Day8DataReader(instructionList);
		reader.read("day8_input_data.txt", false);
		Day8Operator operator = new Day8Operator(instructionList);
		operator.operate();
	}
	
	public void generateDay18Report() {
		DataLinkedList<Expression, ExpressionData> expressionList = new DataLinkedList<Expression, ExpressionData>();
		Day18DataReader reader = new Day18DataReader(expressionList);
		reader.read("day18_input_data_test.txt", false);
	}
	
	public static void main(String[] args) {
		AocReport rr = new AocReport();
		rr.generateDay18Report();
	}
}
