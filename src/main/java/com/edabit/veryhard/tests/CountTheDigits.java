package com.edabit.veryhard.tests;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CountTheDigits {
	
	public static int digitsCount(long number) {
		if(number/10 == 0) {
			return 1;
		}
		return 1+ digitsCount(number/10);
	}
	
	public static String nameString(String name) {
		/*
		 * A student learning Java was trying to make a function.
		 * His code should concatenate a passed string name with string "Edabit" 
		 * and store it in a variable called result. He needs your help to fix this code.
		 * */
		String b = "Edabit";
		String result = name+b;
		return result;
	}
	
	public static int[] encrypt(String str) {
		//The first letter of the string is the ascii code. 
		//For the  remaining next letter are the different between the current character and previous character in the list.
		int[] code = new int[str.length()];
		code[0]=((int)str.charAt(0));
		for(int i=1; i < str.length(); i++) {
			code[i] = (int)str.charAt(i)-(int)str.charAt(i-1);
		}
		return code;
	}
	
	private static void printCode(int[] code) {
		for(int i = 0; i < code.length ; i++) {
			System.out.print(code[i]+" ");
		}
		System.out.println();
	} 
	
	public static String decrypt(int[] arr) {
		char[] character = new char[arr.length];
		character[0] = (char) arr[0];
		for(int i=1; i< arr.length; i++) {
			character[i] = (char)((int)character[i-1]+arr[i]);
		}
		return String.copyValueOf(character);
	}
	
	public static String pigLatin(String str) {
		String latin = Stream.of(str.substring(0, str.length()-1).replace(".", "").split(" ")).map(c->
		c =  ("aeiouAEIOU".contains(c.substring(0,1)) ?  c + "way": c.substring(1,c.length())+c.substring(0,1).toLowerCase()+"ay") ).collect(Collectors.joining(" "));
		return latin.substring(0,1).toUpperCase()+latin.substring(1)+str.substring(str.length()-1);
	}
	
	public static int sumDigProd(Integer... values) {
		int sumValues =  sum(values);
		while(sumValues > 9) {
			sumValues  = digit(sumValues);
		}
		return sumValues;
	}
	
	public static int digit(int value) {
		if(value == 0) {
			return 1;
		}
		int rvalue = Math.abs(value/10);
		int digit = value - rvalue*10;
		return digit * digit(rvalue) ;
	}
	
	private static int sum(Integer... values) {
		int sum = 0;
		for(Integer value: values) {
			sum+=value;
		}
		return sum;
	}
	
	
	public static long multiplicationPersistence(long value) {
		int mulPer = 0;
		if(value <= 9) {
			return mulPer;
		}
		return 1+ multiplicationPersistence(multiplyDigits(value));
	}
	
	public static int additionPersistence(int value) {
		int addPer = 0;
		if (value <= 9) {
			return addPer;
		} 
		
		return 1+ additionPersistence(addDigits(value));
	}
	
	private static long multiplyDigits(long value) {
		if(value < 9) {
			return value;
		}
		
		long rValue = Math.abs(value/10);
		long digit = value - rValue*10;
		return digit*multiplyDigits(rValue);
	}
	
	private static int addDigits(int value) {
		if(value < 9) {
			return value;
		}
		int rValue = Math.abs(value/10);
		int digit = value - rValue*10;
		
		return digit+addDigits(rValue);
	}
	
	
	public static boolean arrangeStringInWord(String initial, String word) {
		char[] charMap = new char[word.length()];
		int fromIndex = 0;
		int match = 0;
		for(char c : initial.toCharArray()) {
			int index = word.indexOf(c, fromIndex);
			if(index >=0 ) {
				charMap[index] = c;
				fromIndex = index+1;
				match++;
			}
		}
		return match == initial.length();
	}
	
	
	
	public static void main(String[] args) {
		System.out.println(digitsCount(123456789));
		printCode(encrypt("The neighbours are strange.."));
		System.out.println(decrypt(encrypt("The neighbours are strange..")));
		
		 //assertEquals("Atscay areway reatgay etspay.", Challenge.pigLatin("Cats are great pets."));
		
		System.out.println(pigLatin("He told us a very exciting tale!"));
		System.out.println(sumDigProd(1,2,3,4,5,6));
		
		System.out.println(multiplicationPersistence(77));
		System.out.println(additionPersistence(1679583));
		
		System.out.println(arrangeStringInWord("bbutl", "beautiful"));
	}
}
