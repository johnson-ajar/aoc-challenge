package com.aoc.data.model;

import java.util.List;

import com.aoc.data.tokenizer.DataTokenizer;
import com.aoc.data.tokenizer.Token;
import com.aoc.days.expression.TokenType;
import com.aoc.days.visitor.IDataVisitor;

public class InstructionData extends Data<Instruction> {
	private int index;
	private int callCount = 0;
	
	private enum InstructionTokenType implements TokenType {
		ALPHABETS(1),
		OPERATOR(2),
		NUMBER(3);
		
		private int id;
		private InstructionTokenType(int id) {
			this.id = id;
		}
		@Override
		public int getId() {
			return this.id;
		}
		
	}
	
	public InstructionData(int index, Instruction inData) {
		super(inData);
	}
	
	public InstructionData(int index, String inData) {
		super(inData);
		this.index = index;
	}
	
	@Override
	protected Instruction getData(String inData) {
		DataTokenizer tokenizer = new DataTokenizer();
		tokenizer.addPattern("[a-z]+", InstructionTokenType.ALPHABETS);
		tokenizer.addPattern("[+-]{1}", InstructionTokenType.OPERATOR);
		tokenizer.addPattern("[0-9]+$", InstructionTokenType.NUMBER);
		tokenizer.tokenize(inData);
		List<Token> tokens = tokenizer.getTokens();
		return new Instruction(tokens.get(0).value(), tokens.get(1).value(), Integer.valueOf(tokens.get(2).value()));
	}

	@Override
	public int compare(IData<Instruction> inValue) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <D extends Data<Instruction>> void accept(IDataVisitor<Instruction, D> iDataVisitor) {
		iDataVisitor.update((D)this);
	}
	
	@Override
	public String toString() {
		return String.format("[%d] %s callCount = %d", this.index, this.getValue(), this.callCount);
	}
	
	public void executionCount() {
		callCount+=1;
	}
	
	public boolean noCalls(int no) {
		if (callCount >= no) {
			return true;
		}
		return false;
	}
	
	public int getIndex() {
		return this.index;
	}
	
	@Override
	public void reset() {
		this.callCount = 0;
	}
}
