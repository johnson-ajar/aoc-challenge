package com.edabit.expert.tests;

public class OneTimePad {
	
	private static String encrypt(String plncode, String pad) {
		String key = pad.substring(0, 5);
		pad = pad.substring(5);
		System.out.println(key+" "+pad);
		String codeStr = "";
		for(int i=0; i < plncode.length(); i++) {
			int diff = Integer.valueOf(plncode.charAt(i))-Integer.valueOf(pad.charAt(i));
			int code = Math.floorMod(diff,10);
			
			codeStr+=code;
		}
		return key+codeStr;
	}
	
	private static String decrypt(String cypcode, String pad) {
		String key = pad.substring(0,5);
		pad = pad.substring(5).trim();
		String actStr = "";
		cypcode = cypcode.substring(5).trim();
		for(int i=0; i < cypcode.length(); i++) {
			int c = Integer.valueOf(Character.toString(cypcode.charAt(i)));
			int p = Integer.valueOf(Character.toString(pad.charAt(i)));
			int sum = c+p;
			int code = Math.floorMod(sum, 10);
			actStr+=code;
		}
		return key+actStr;
	}
	
	public static void main(String[] args) {
		String pad = "637197877682780836504704874690100607768768";
		String codeStr = encrypt("24955184247696969", pad);
		System.out.println(codeStr);
		String actStr = decrypt(codeStr, pad);
		System.out.println(actStr);
	}
}
