package com.edabit.expert.tests;

public class ExpertProblems {
	
	private static int[] findSquareRange(int size) {
		int[] ranges = new int[2];
		int floor = (int)(Math.round(Math.sqrt(size)));
		int ceil = (int)(Math.ceil(Math.sqrt(size)));
		ranges[0] = Math.min(floor, ceil); //rows
		ranges[1] = Math.max(floor, ceil); //columns
		return ranges;
	}
	
	private static String stripSpace(String value) {
		return value.replace(" ", "");
	}
	
	private static String encodedText(String text) {
		String stripText = stripSpace(text);
		int[] range = findSquareRange(stripText.length());
		printRange(range);
		char[][] textArray = new char[range[0]][range[1]];
		int count = 0;
		for(int i=0; i< range[0]; i++) {
			for(int j=0; j< range[1]; j++) {
				if(count < stripText.length()) {
					textArray[i][j] = stripText.charAt(count);
					System.out.print(stripText.charAt(count));
					count++;
				}
				
			}
			System.out.println();
		}
		return printArray(textArray, range);
	}
	
	private static String printArray(char[][] textArray, int[] range) {
		String encodedStr = "";
		for(int j=0; j< range[1]; j++) {
			for(int i=0; i< range[0]; i++) {
				
					char value = textArray[i][j];
					//System.out.println(value+" "+(int)value);
					encodedStr+= value > 0 ? value: "";
					System.out.print(value);
			 }
			System.out.println();
			encodedStr+= j < range[1]-1 ? " ":"";
		}
		return encodedStr;
	}
	
	private static void printRange(int[] range) {
		System.out.println(range[0]+" "+range[1]);
	}
	
	public static void main(String[] args) {
		int[] range = findSquareRange(54);
		System.out.println(range[0]+" "+range[1]);
		System.out.println(stripSpace("hae and via ecy"));
		String text = "hae and via ecy";
		String text1 = "if man was meant to stay on the ground god would have given us roots";
		String text2 = "chillout";
		System.out.println(encodedText(text2));
	}
}
