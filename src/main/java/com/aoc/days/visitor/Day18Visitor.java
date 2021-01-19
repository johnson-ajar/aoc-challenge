package com.aoc.days.visitor;

import com.aoc.data.model.Expression;
import com.aoc.data.model.ExpressionData;
import com.aoc.data.structure.DataLinkedList;
import com.aoc.days.operators.ExpressionCalculator;

public class Day18Visitor extends BaseVisitor<Expression, ExpressionData>{
	
	private final ExpressionCalculator calculator = new ExpressionCalculator();
	
	@Override
	public void report(ExpressionData inData, DataLinkedList<Expression, ExpressionData> dataList) {
		System.out.println(inData);
		calculator.calculate(inData);
	}

	@Override
	public void update(ExpressionData inData) {
		System.out.println(inData);
	}

}
