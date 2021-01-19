package com.aoc.days.datareader;

import com.aoc.data.model.Expression;
import com.aoc.data.model.ExpressionData;
import com.aoc.data.structure.DataLinkedList;
import com.aoc.days.visitor.Day18Visitor;
import com.aoc.days.visitor.IDataVisitor;

public class Day18DataReader extends AbstractReader<Expression, ExpressionData>{
	private final Day18Visitor visitor;
	public Day18DataReader(DataLinkedList<Expression, ExpressionData> inData) {
		super(inData);
		this.visitor = new Day18Visitor();
	}

	@Override
	public String getType() {
		return "expression";
	}

	@Override
	protected IDataVisitor<Expression, ExpressionData> getVisitor() {
		return this.visitor;
	}

}
