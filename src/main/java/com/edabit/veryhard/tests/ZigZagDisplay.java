package com.edabit.veryhard.tests;

public class ZigZagDisplay {
	
	public void display(String[] rows) {
		for(String row : rows) {
			System.out.println(row);
		}
	}
	
	public void arrange(String str, int noRow) {
		int strLength = str.length();
		String[] charArray = new String[noRow];
		
		int count = 0;
		int row = 0;
		while(count < strLength) {
			count++;
		}
		display(charArray);
	}
	
	public static void main(String[] args) {
		ZigZagDisplay disp = new ZigZagDisplay();
		disp.arrange("PAYPALISHIRING", 3);
	}
}
