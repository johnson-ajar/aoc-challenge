package com.aoc.data.model;

import java.util.List;

import com.aoc.data.tokenizer.DataTokenizer;
import com.aoc.data.tokenizer.Token;
import com.aoc.days.expression.TokenType;
import com.aoc.days.visitor.IDataVisitor;

public class NavigationData  extends Data<Navigation>{
	private int index;
	
	private enum NavigationTokenType implements TokenType {
		ALPHABETS(1),
		NUMBERS(2);
		private int id;
		
		private NavigationTokenType(int id) {
			this.id = id;
		}
		
		@Override
		public int getId() {
			// TODO Auto-generated method stub
			return this.id;
		}
		
	}
	
	public NavigationData(int index, String inData) {
		super(inData);
		this.index = index;
	}
	
	
	@Override
	protected Navigation getData(String inData) {
		System.out.println(inData);
		DataTokenizer tokenizer = new DataTokenizer();
		tokenizer.addPattern("^[A-Z]", NavigationTokenType.ALPHABETS);
		tokenizer.addPattern("[0-9]+$", NavigationTokenType.NUMBERS);
		tokenizer.tokenize(inData);
		List<Token> tokens = tokenizer.getTokens();
		for(Token token : tokens) {
			System.out.println(token);
		}
		return new Navigation(tokens.get(0).value(), Float.valueOf(tokens.get(1).value()));
	}

	@Override
	public int compare(IData<Navigation> inValue) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <D extends Data<Navigation>> void accept(IDataVisitor<Navigation, D> iDataVisitor) {
		iDataVisitor.update((D)this);
	}
	
	@Override
	public String toString() {
		return String.format("%d %s", this.index, super.toString());
	}
	
}
