package com.aoc.data.tokenizer;

import java.util.ArrayList;
import java.util.List;

public class DataPatterns {
	private List<TokenPattern> patterns = new ArrayList<TokenPattern>();
	
	public void addPattern(TokenPattern tokPattern) {
		patterns.add(tokPattern);
	}
	
	
}
