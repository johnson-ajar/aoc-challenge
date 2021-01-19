package com.aoc.data.model;

public class Password {
	private final int minOccur;
	private final int maxOccur;
	private final char anchorChar;
	private final String password;
	
	public Password(int inMin, int inMax, char aChar, String password) {
		this.minOccur = inMin;
		this.maxOccur = inMax;
		this.anchorChar = aChar;
		this.password = password;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public int minOccur() {
		return this.minOccur;
	}
	
	public int maxOccur() {
		return this.maxOccur;
	}
	
	public char getAnchor() {
		return this.anchorChar;
	}
	
	@Override
	public String toString() {
		return String.format(
				"[%d-%d] %c : %s"
				, this.minOccur, this.maxOccur, this.anchorChar, this.password);
	}
}
