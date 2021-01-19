package com.aoc.days.datareader;

import com.aoc.data.model.Navigation;
import com.aoc.data.model.NavigationData;
import com.aoc.data.structure.DataLinkedList;
import com.aoc.days.visitor.Day12Visitor;
import com.aoc.days.visitor.IDataVisitor;

public class Day12DataReader extends AbstractReader<Navigation, NavigationData>{
	
	private final Day12Visitor visitor;
	public Day12DataReader(DataLinkedList<Navigation, NavigationData> inData) {
		super(inData);
		visitor = new Day12Visitor();
	}

	@Override
	public String getType() {
		return "Navigation";
	}

	@Override
	protected IDataVisitor<Navigation, NavigationData> getVisitor() {
		return this.visitor;
	}

}
