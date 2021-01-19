package com.edabit.veryhard.tests;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LongestPalindrome {
	
	public int palindrome(String s) {
		int i = 0;
		int j = 0;
		List<Character> pStr = new ArrayList<>();
		List<String> palindrome = new ArrayList<String>();
		int len = 0;
		while(j<s.length()) {
			pStr.add(s.charAt(j));
			if(checkPalindrome(pStr)) {
				String pp = pStr.stream().map(ps -> ps.toString()).collect(Collectors.joining(""));
				palindrome.add(pp);
				len = Math.max(len,pp.length());
				pStr.remove(0);
				j=i++;
			}
			j++;
			
		}
		return len;
	}
	
	public String centerPoint(String s) {
		int start = 0;
		int end = 0;
		for(int i=0; i< s.length() ;i++)
		{
			int len1 = expandFromMiddle(s, i, i);
			int len2 = expandFromMiddle(s, i, i+1);
			int len = Math.max(len1, len2);
			if(len > (end - start)) {
				start = i- len/2;
				end = i+len/2;
			}
		}
		start = start < 0 ? 0: start+1;
		return s.substring(start, end);
		
	}
	private int expandFromMiddle(String s, int left, int right) {
		while(left >=0 && right <s.length() && s.charAt(left) == s.charAt(right)) {
			left --;
			right++;
		}
		return right-left;
	}
	
	private boolean checkPalindrome(List<Character> pStr) {
		System.out.println(pStr);
		int size = pStr.size();
		int j=0;
		if(size == 0 || size == 1) {
			return false;
		}
		while(j < size) {
			if(!pStr.get(j).equals(pStr.get(size-j-1))) {
				return false;
			}
			j++;
		}
		return true;
	}
	
	
	public static void main(String[] args) {
	   	//"civic, redivider, deified, radar, level, madam, rotor, refer, kayak, racecar, and reviver";
		 
		LongestPalindrome lpd = new LongestPalindrome();
		//System.out.println(lpd.palindrome("civicredividerradar"));
		//System.out.println(lpd.palindrome("cbbd"));
		
		System.out.println(lpd.centerPoint("babad"));
		System.out.println(lpd.centerPoint("racecar"));
		System.out.println(lpd.centerPoint("civicredividerradar"));
	}
}
