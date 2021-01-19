package com.aoc.data.model;

public class Instruction {
	private String action;
	private String operator;
	private int value;
	
	public Instruction(String action, String operator, int value) {
		this.action = action;
		this.operator = operator;
		this.value = value;
	}
	
	public String getAction() {
		return this.action;
	}
	
	public String getOperator() {
		return this.operator;
	}
	
	public int value() {
		return this.value;
	}
	
	public void setAction(String inAction) {
		this.action = inAction;
	}
	
	@Override
	public String toString() {
		return String.format("%s %s %d", this.action, this.operator, this.value);
	}
}
