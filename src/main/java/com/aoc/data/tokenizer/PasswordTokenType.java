package com.aoc.data.tokenizer;

import com.aoc.days.expression.TokenType;

public enum PasswordTokenType implements TokenType {
	NUMBER(1),
	HYPHEN(2),
	COLON(3),
	ALPHABETS(4);
	
	private int id;
	private PasswordTokenType(int id) {
		this.id = id;
	}
	
	@Override
	public int getId() {
		return 0;
	}

}
