package com.edabit.veryhard.tests;

import java.util.HashSet;
import java.util.Set;

public class LongestSubstring {
	public int bruteForce(String s) {
        int n = s.length();
        int ans = 0;
        for (int i = 0; i < n; i++)
            for (int j = i + 1; j <= n; j++)
                if (allUnique(s, i, j)) ans = Math.max(ans, j - i);
        return ans;
    }

    public boolean allUnique(String s, int start, int end) {
        Set<Character> set = new HashSet<>();
        for (int i = start; i < end; i++) {
            Character ch = s.charAt(i);
            if (set.contains(ch)) return false;
            set.add(ch);
        }
        return true;
    }
    
    
    public int movingWindow(String s) {
    	int i = 0;
    	int j = 0;
    	int window_size = 0;
    	Set<Character> window = new HashSet<>();
    	while(j < s.length()) {
    		if(window.contains(s.charAt(j))) {
    			
    			window.remove(s.charAt(i));
    			i++;
    		} else {
    			window.add(s.charAt(j));
    			window_size = Math.max(window.size(), j);
        		j++;
    		}
    		System.out.println(window);
    	}
    	return window.size();
    }
    
    public static void main(String[] args) {
    	//"civic, redivider, deified, radar, level, madam, rotor, refer, kayak, racecar, and reviver";
    	String s1 = "pwwkew";
    	String s2 = "dvdf";
    	LongestSubstring l = new LongestSubstring();
    	int ans = l.bruteForce(s1);
    	System.out.println( ans );
    	System.out.println(l.movingWindow(s2));
    	
    }
}
