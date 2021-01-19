package com.aoc.data.tokenizer;

import com.aoc.days.expression.TokenType;

public enum BaseTokenType implements TokenType {
	;
	private int id;
	private BaseTokenType(int inId) {
		this.id = inId;
	}
	@Override
	public int getId() {
		return this.id;
	}

}
