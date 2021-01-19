package com.aoc.data.structure;

import com.aoc.data.model.Data;
import com.aoc.data.model.ExpressionData;
import com.aoc.data.model.InstructionData;
import com.aoc.data.model.IntegerData;
import com.aoc.data.model.NavigationData;
import com.aoc.data.model.PassportData;
import com.aoc.data.model.PasswordData;
import com.aoc.data.model.RoadData;
import com.aoc.data.model.TicketData;

public class DataFactory {
	private static DataFactory instance;
	
	private DataFactory() {
		
	}
	
	public static DataFactory getInstance() {
		if (instance == null) {
			instance = new DataFactory();
		}
		return instance;
	}
	
	
	public <T> Data<T> getData(int lineNo, String inValue, String type) {
		if (type == "Integer") {
			return (Data<T>)new IntegerData(inValue);
		} else if (type == "Password") {
			return (Data<T>)new PasswordData(lineNo, inValue);
		} else if (type == "Road") {
			return (Data<T>) new RoadData(lineNo, inValue);
		} else if (type == "Passport") {
			return (Data<T>) new PassportData(lineNo, inValue);
		} else if (type == "Ticket") {
			return (Data<T>)new TicketData(lineNo, inValue);
		} else if (type == "Navigation") {
			return (Data<T>) new NavigationData(lineNo, inValue);
		} else if(type == "Instruction") {
			return (Data<T>) new InstructionData(lineNo, inValue);
		} else if(type == "expression") {
			return (Data<T>) new ExpressionData(lineNo, inValue);
		}
		return null;
	}
}
