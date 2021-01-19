package com.aoc.data.model;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.aoc.data.tokenizer.DataTokenizer;
import com.aoc.data.tokenizer.Token;

public class Passport extends HashMap<String, String>{
	
	public Passport() {
		for (PassportProperties prop : PassportProperties.values()){
			this.put(prop.name(), "");
		}
	}
	
	public boolean isValid() {
		for (PassportProperties prop : PassportProperties.values()) {
			if(this.get(prop.name()).trim().length()==0 && prop != PassportProperties.CID) {
				return false;
			}
		}
		return true;
	}
	
	public boolean isValidStrongly() {
		/*
		StringBuilder strBuild = new StringBuilder();
		strBuild.append(String.format("%s %b\n", this.get(PassportProperties.BYR.name()),   checkBYR(this.get(PassportProperties.BYR.name()))));
		strBuild.append(String.format("%s %b\n", this.get(PassportProperties.IYR.name()), checkIYR(this.get(PassportProperties.IYR.name()))));
		strBuild.append(String.format("%s %b\n", this.get(PassportProperties.EYR.name()), checkEYR(this.get(PassportProperties.EYR.name()))));
		strBuild.append(String.format("%s %b\n", this.get(PassportProperties.HGT.name()), checkHGT(this.get(PassportProperties.HGT.name()))));
		strBuild.append(String.format("%s %b\n", this.get(PassportProperties.HCL.name()), checkHCL(this.get(PassportProperties.HCL.name()))));
		strBuild.append(String.format("%s %b\n", this.get(PassportProperties.ECL.name()), checkECL(this.get(PassportProperties.ECL.name()))));
		strBuild.append(String.format("%s %b\n", this.get(PassportProperties.PID.name()), checkPID(this.get(PassportProperties.PID.name()))));
		
		System.out.println(strBuild.toString());
		*/
		
		if(!checkBYR(this.get(PassportProperties.BYR.name()))
		 || !checkIYR(this.get(PassportProperties.IYR.name()))
		 || !checkEYR(this.get(PassportProperties.EYR.name()))
		 || !checkHGT(this.get(PassportProperties.HGT.name()))
		 || !checkHCL(this.get(PassportProperties.HCL.name()))
		 || !checkECL(this.get(PassportProperties.ECL.name()))
		 || !checkPID(this.get(PassportProperties.PID.name()))) {
			return false;
		}
		return true;
	}
	
	private boolean checkBYR(String value) {
		return checkYear(value, 1920, 2002);
	}
	
	private boolean checkIYR(String value) {
		return checkYear(value, 2010, 2020);
	}
	
	private boolean checkEYR(String value) {
		return checkYear(value, 2020, 2030);
	}
	
	private boolean checkHGT(String value) {
		value = value.trim();
		if (value.endsWith("cm") ) {
			int height = Integer.valueOf(value.replace("cm", "").trim());
			return height >=150 && height <=193;
		}
				
		if (value.endsWith("in")) {
			int height = Integer.valueOf(value.replace("in", "").trim());
			return height >=59 && height <=76;
		}
		return false;
	}
	
	private boolean checkHCL(String value) {
		value = value.trim();
		String ptnStr = "^(#[a-f0-9]{6}$)";
		Pattern pattern = Pattern.compile(ptnStr, 1);
		Matcher m = pattern.matcher(value);
		if (m.find()) {
			return true;
		}
		return false;
	}
	
	private boolean checkECL(String value) {
		String[] colors = {"amb", "blu", "brn", "gry", "grn", "hzl", "oth"};
		value = value.trim();
		for(String color : colors) {
			if(color.equals(value)) {
				return true;
			}
		}
		return false;
	}
	
	private boolean checkPID(String value) {
		value = value.trim();
		String prnStr = "^([0-9]{9}$)";
		Pattern pattern = Pattern.compile(prnStr);
		Matcher m = pattern.matcher(value);
		if(m.find()) {
			return true;
		}
		return false;
	}
	
	private boolean checkYear(String value, int fYear, int eYear) {
		if(value.trim().length() != 4) {
			return false;
		}
		int year = Integer.parseInt(value);
		return year >= fYear && year <=eYear;
	}
	
	
	public static void main(String[] arg) {
		Passport pass = new Passport();
		String[] value = {"#123abc #123abz 123abc"};
		String[] value1 = {"#123abc", "#123abz", "123abc", "$123abc", "#123abcff"};
		for(String v : value1) {
			System.out.println(v+" "+pass.checkHCL(v));
		}
		
		String[] pid1 = {"000000001", "0123456789", "01010"};
		for(String id : pid1) {
			System.out.println(id+" "+pass.checkPID(id));
		}
	}
}
