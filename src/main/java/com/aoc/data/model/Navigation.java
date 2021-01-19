package com.aoc.data.model;

public class Navigation {
	private final String action;
	private final float step;
	
	public Navigation(String action, float step) {
		this.action = action;
		this.step = step;
	}
	
	public String getAction() {
		return this.action;
	}
	
	public float getStep() {
		return this.step;
	}
	
	@Override
	public String toString() {
		return String.format("%s %f", this.action, this.step);
	}
}
