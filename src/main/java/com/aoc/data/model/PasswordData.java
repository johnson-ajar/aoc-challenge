package com.aoc.data.model;

import java.util.List;

import com.aoc.data.tokenizer.DataTokenizer;
import com.aoc.data.tokenizer.Token;
import com.aoc.days.expression.TokenType;
import com.aoc.days.visitor.IDataVisitor;

public class PasswordData extends Data<Password>{
	private int index;
	private enum PasswordToken implements TokenType {
		NUMBER(1),
		NUMBER_CM(2),
		ALPHABET(3),
		COLOR(4);
		
		private int id;
		private PasswordToken(int id) {
			this.id = id;
		}
		@Override
		public int getId() {
			return this.id;
		}
		
	}
	public PasswordData(int index, Password inData) {
		super(inData);
		this.index = index;
	}
	
	public PasswordData(int index, String inData) {
		super(inData);
		this.index = index;
	}
	
	@Override
	public Password getData(String inData) {
		// TODO Auto-generated method stub
		
		DataTokenizer tokenizer = new DataTokenizer();
		//tokenizer.addPattern("[0-9]+", 1);
		//tokenizer.addPattern("-", 2);
		//tokenizer.addPattern(":", 3);
		tokenizer.addPattern("[a-zA-Z]+\\:[a-zA-Z]+", PasswordToken.ALPHABET);
		tokenizer.addPattern("[a-zA-Z]+\\:[0-9]+", PasswordToken.NUMBER);
		tokenizer.addPattern("[a-zA-Z]+\\:[0-9]+cm", PasswordToken.NUMBER_CM);
		tokenizer.addPattern("[a-zA-Z]+\\:#c[0-9]+f", PasswordToken.COLOR);
		
		tokenizer.tokenize(inData);
		List<Token> tokens = tokenizer.getTokens();
		return new Password(Integer.parseInt(tokens.get(0).value()),
				Integer.parseInt(tokens.get(2).value()), 
				tokens.get(3).value().charAt(0), 
				tokens.get(5).value());
	}
	
	@Override
	public int compare(IData<Password> inValue) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <D extends Data<Password>> void accept(IDataVisitor<Password, D> visitor) {
		// TODO Auto-generated method stub
		
	}
	
}
