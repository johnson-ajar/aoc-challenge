package com.aoc.days.expression;

import com.aoc.data.model.Data;
import com.aoc.data.model.IData;
import com.aoc.data.structure.DataLinkedList;
import com.aoc.data.tokenizer.Token;
import com.aoc.days.visitor.IDataVisitor;

public class ExpressionToken extends Data<Token>{
	private int index;
	private TokenType type;
	private final DataLinkedList<Token, ExpressionToken> operands;
	public ExpressionToken(int index, Token inData) {
		super(inData);
		this.index = index;
		operands = new DataLinkedList<>();
	}

	public ExpressionToken(int index, String inData) {
		super(inData);
		this.index = index;
		operands = new DataLinkedList<>();
	}

	public ExpressionTokenType getType() {
		return (ExpressionTokenType)this.getValue().type();
	}
	
	public DataLinkedList<Token, ExpressionToken> getChildTokens() {
		return this.operands;
	}
	
	public void addChildToken(ExpressionToken token) {
		operands.addData(token, null);
	}
	
	public boolean containOperands() {
		return this.operands.size()>0;
	}
	
	@Override
	protected Token getData(String inData) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int compare(IData<Token> inValue) {
		ExpressionToken toCompare = (ExpressionToken) inValue;
		if(getType().priority() == toCompare.getType().priority()) {
			return 0;
		}
		//If the current token priority is less than the compared token.
		return getType().priority() < toCompare.getType().priority() ? 1: -1;
	}

	@Override
	public <D extends Data<Token>> void accept(IDataVisitor<Token, D> iDataVisitor) {
		// TODO Auto-generated method stub
		
	}
}
