package com.aoc.data.tokenizer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.aoc.days.expression.TokenType;

public class TokenPattern {
	final Pattern regex;
	final TokenType tokenType;
	
	public TokenPattern(Pattern regex, TokenType tokenType) {
		this.regex = regex;
		this.tokenType = tokenType;
	}
	
	public Matcher matcher(String str) {
		return regex.matcher(str);
	}
	
	public TokenType getType() {
		return this.tokenType;
	}
}
