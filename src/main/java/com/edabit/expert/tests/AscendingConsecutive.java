package com.edabit.expert.tests;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AscendingConsecutive {
	
	private boolean checkAscending() {
		return false;
	}
	private boolean checkContinuous() {
		return false;
	}
	
	private  static String getNumbers(String number, int noDigits) {
		String num = "";
		String numberList = "";
		for(int i=0; i< noDigits; i++) {
			if(i < number.length()) {
				num+=number.charAt(i);
			}
		}
		numberList +=num;
		int diff = 0;
		for(int i=noDigits;i < number.length(); i=i+noDigits) {
			String cnum ="";
			for(int j=0; j<noDigits; j++) {
				int index = i*noDigits+j;
				if(index < number.length()) {
					
					cnum+=number.charAt(index);
				}
			}
			diff = Integer.valueOf(cnum)-Integer.valueOf(num);
			System.out.println(num+" "+cnum+" "+diff);
			if(diff > 1) {
				return "";
			}
			
			numberList+=":"+cnum;
			num = cnum;
		}
		return numberList;
	}
	
	private static boolean getContinuousNumbers(String number) {
		boolean continuous = false;
		int nodigit =1;
		List<Integer> numbers = new ArrayList<Integer>();
		while(!continuous && (nodigit < number.length()/2)) {
			numbers = numberRecursive(number, nodigit, new ArrayList<Integer>());
			System.out.println(numbers);
			int count = 0;
				for(int i=1;i< numbers.size(); i++) {
					 int diff = numbers.get(i)-numbers.get(i-1);
					 if(diff == 1 || diff == 0) count++;
				}
				System.out.println(count+ " "+ (numbers.size()-1));
				continuous = (count == numbers.size()-1)? true: false;
			nodigit+=1;
		}
		continuous  = numbers.size() == 1 ? false: continuous;
		System.out.println(continuous);
		//return numbers.stream().map(n -> Integer.toString(n)).collect(Collectors.joining(","));
		return continuous;
	}
	
	private static String numberRecursive(String number, int noDigits) {
		if(number.length()==0) {
			return "";
		} else if (number.length()<noDigits) {
			return number;
		}
		String digit = number.substring(0, noDigits);
		String rNumbers = number.replaceFirst(digit, "");
		return digit+(rNumbers.length()>0?":":"")+numberRecursive(rNumbers, noDigits);
	}
	
	
	private static List<Integer> numberRecursive(String number, int noDigits, List<Integer> values) {
		if(number.length()==0) {
			return values;
		} else if (number.length()<noDigits) {
			values.add(Integer.valueOf(number));
			return values;
		}
		String digit = number.substring(0, noDigits).trim();
		
		System.out.println(digit);
		values.add(Integer.valueOf(digit));
		String rNumbers = number.replaceFirst(digit, "");
		return numberRecursive(rNumbers, noDigits, values);
	}
	
	public static void main(String[] args) {
		//String numbers = getNumbers("12345678", 1);
		//System.out.println(numbers);
		
		//System.out.println(numberRecursive("1234567", 3));
		//List<Integer> values = new ArrayList<Integer>();
		//System.out.println(numberRecursive("1234567", 3, values));
		System.out.println(getContinuousNumbers("444445"));
	}
}
