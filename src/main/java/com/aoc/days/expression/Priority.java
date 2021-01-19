package com.aoc.days.expression;

public enum Priority {
	LEVEL0(0),
	LEVEL1(1),
	LEVEL2(2),
	LEVEL3(3),
	LEVEL4(4),
	LEVELInf(100);
	
	private int level ;
	private Priority(int level) {
		this.level = level;
	}
	
	public int level() {
		return this.level;
	}
	
}
