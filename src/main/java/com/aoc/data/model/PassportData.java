package com.aoc.data.model;

import java.util.List;

import com.aoc.data.tokenizer.DataTokenizer;
import com.aoc.data.tokenizer.Token;
import com.aoc.days.expression.TokenType;
import com.aoc.days.visitor.IDataVisitor;

public class PassportData extends Data<Passport> {
	private int index;
	private enum PasswordTokenType implements TokenType {
		PASSPORT(1);
		private int id;
		private PasswordTokenType(int id) {
			this.id = id;
		}
		@Override
		public int getId() {
			return this.id;
		}
		
	}
	public PassportData(int lineNo, Passport inData) {
		super(inData);
		this.index  = lineNo;
	}
	
	public PassportData(int lineNo, String inData) {
		super(inData);
		this.index = lineNo;
	}

	@Override
	protected Passport getData(String inData) {
		Passport passport = new Passport();
		DataTokenizer tokenizer = new DataTokenizer();
		tokenizer.addPattern("[a-zA-Z]+\\:((#*)[a-z0-9]|(\\d+))(\\w*)", PasswordTokenType.PASSPORT);
		tokenizer.tokenize(inData);
		List<Token> tokens = tokenizer.getTokens();
		for(Token token : tokens) {
			String[] values = token.value().split(":");
			passport.put(values[0].toUpperCase(), values[1]);
		}
		return passport;
	}

	@Override
	public int compare(IData<Passport> inValue) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <D extends Data<Passport>> void accept(IDataVisitor<Passport, D> iDataVisitor) {
		iDataVisitor.update((D)this);
	}

}
