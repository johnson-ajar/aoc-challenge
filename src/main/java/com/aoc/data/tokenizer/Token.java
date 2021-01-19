package com.aoc.data.tokenizer;

import com.aoc.days.expression.TokenType;

public class Token {
	final TokenType token;
	final String sequence;
	
	public Token(TokenType token, String sequence) {
		this.token = token;
		this.sequence = sequence;
	}
	
	@Override
	public String toString() {
		return String.format("[%s]:{%s},", this.token, this.sequence);
	}
	
	public String value() {
		return this.sequence;
	}
	
	public TokenType type() {
		return this.token;
	}
}
