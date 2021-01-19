package com.aoc.data.model;

import com.aoc.days.visitor.IDataVisitor;

public class ExpressionData extends Data<Expression> {
	private final int index;
	public ExpressionData(int index, Expression inData) {
		super(inData);
		this.index = index;
	}
	
	public ExpressionData(int index, String inData) {
		super(inData);
		this.index = index;
	}
	
	@Override
	protected Expression getData(String inData) {
		return new Expression(inData);
	}
	@Override
	public int compare(IData<Expression> inValue) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public <D extends Data<Expression>> void accept(IDataVisitor<Expression, D> iDataVisitor) {
		iDataVisitor.update((D)this);
	}
	
	@Override
	public String toString() {
		return String.format("%d %s", index, this.getValue());
	}
	
}
