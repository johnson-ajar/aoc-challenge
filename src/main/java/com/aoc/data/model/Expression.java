package com.aoc.data.model;

public class Expression {
	private final String expr;
	public Expression(String inExpr) {
		this.expr = inExpr;
	}
	
	public String getExpression() {
		return this.expr;
	}
	
	@Override
	public String toString() {
		return this.expr;
	}
}
