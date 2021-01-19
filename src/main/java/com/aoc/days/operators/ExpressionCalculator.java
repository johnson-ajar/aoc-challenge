package com.aoc.days.operators;

import com.aoc.data.model.Expression;
import com.aoc.data.model.ExpressionData;
import com.aoc.data.structure.DataStack;
import com.aoc.data.tokenizer.Token;
import com.aoc.day1.iterator.IListIterator;
import com.aoc.days.expression.ExpressionNode;
import com.aoc.days.expression.ExpressionToken;
import com.aoc.days.expression.ExpressionTokenType;
import com.aoc.days.expression.ExpressionTokenizer;

public class ExpressionCalculator {
	private final ExpressionTokenizer tokenizer;
	public ExpressionCalculator() {
		tokenizer = new ExpressionTokenizer();
	}
	
	public String calculate(ExpressionData inData) {
		String expression = inData.getValue().getExpression();
		DataStack<Token, ExpressionToken> tokens = tokenizer.parse(expression);
		IListIterator<Token, ExpressionToken> iterator = tokens.iterator();
		
		StringBuilder builder = toRPN(tokens.iterator(), new DataStack<Token, ExpressionToken>(), new StringBuilder());
		System.out.println(builder.toString());
		return builder.toString();
	}
	
	public StringBuilder toRPN(IListIterator<Token, ExpressionToken> iterator, DataStack<Token, ExpressionToken> stack, StringBuilder builder)  {
		
		while(iterator.hasNext()) {
			ExpressionToken token = iterator.next();
			operator(token, stack, builder);
		}
		while(!stack.isEmpty()) {
			ExpressionToken operatorToken = stack.pop();
			if(operatorToken.getType() == ExpressionTokenType.LEFT_BRACE) {
				System.out.println("Left brace has missing right brace");
			}
			builder.append(operatorToken.getValue().value());
		}
		return builder;
	}
	
	private StringBuilder operator(ExpressionToken token, DataStack<Token, ExpressionToken> stack, StringBuilder builder) {
		
		switch(token.getType()) {
			case ADD:
				return add(token, stack, builder);
			case SUBSTRACT:
				return substract(token, stack, builder);
			case MULTIPLY:
				return multiply(token, stack, builder);
			case DIVISION:
				return division(token, stack, builder);
			case EXPONENTIAL:
				return exponential(token, stack, builder);
			case UNARY:
				return operand(token, stack, builder);
			default:
				return operand(token, stack, builder);
		}
	}
	
	
	private StringBuilder operand(ExpressionToken token, DataStack<Token, ExpressionToken> stack,  StringBuilder builder) {
		switch(token.getType()) {
			case LEFT_BRACE:
				return leftBrace(token, stack, builder);
			case RIGHT_BRACE:
				return rightBrace(token, stack, builder);
			default:
				builder = this.outputToken(token, builder);
				return builder;
		}
	}
	
	private StringBuilder leftBrace(ExpressionToken token, DataStack<Token, ExpressionToken> stack, StringBuilder builder) {
		//Note: Don't pop the operator just add left brace to the stack.
		stack.push(token);
		return builder;
	}
	
	private StringBuilder rightBrace(ExpressionToken token, DataStack<Token, ExpressionToken> stack, StringBuilder builder) {
		
		//Adding operators to the output inside paranthesis.
		boolean toOutput = true;
		while(!stack.isEmpty() && toOutput) {		
			ExpressionToken operatorToken = stack.pop();
			toOutput = operatorToken.getType() != ExpressionTokenType.LEFT_BRACE;
			if(toOutput) 
				builder = outputToken(operatorToken, builder);
		}
		
		//outputOperator(token, stack, builder);
		return builder;
	}
	
	private StringBuilder add(ExpressionToken token, DataStack<Token, ExpressionToken> stack, StringBuilder builder) {
		outputOperator(token, stack, builder);
		stack.push(token);
		return builder;
	}
	
	private StringBuilder substract(ExpressionToken token,DataStack<Token, ExpressionToken> stack, StringBuilder builder) {
			outputOperator(token, stack, builder);
			stack.push(token);
		return builder;
	}
	
	private StringBuilder multiply(ExpressionToken token, DataStack<Token, ExpressionToken> stack, StringBuilder builder) {
		outputOperator(token, stack, builder);
		stack.push(token);
		return builder;
	}
	
	private StringBuilder division(ExpressionToken token,DataStack<Token, ExpressionToken> stack, StringBuilder builder) {
		outputOperator(token, stack, builder);
		stack.push(token);
		return builder;
	}
	
	private StringBuilder exponential(ExpressionToken token, DataStack<Token, ExpressionToken> stack, StringBuilder builder) {
		outputOperator(token, stack, builder);
		stack.push(token);
		return builder;
	}
	
	/*
	private StringBuilder outputOperator(ExpressionToken token, DataStack<Token, ExpressionToken> stack, StringBuilder builder) {
		boolean toOutput = true;
		while(!stack.isEmpty() && toOutput) {
			//If the current token is right brace and top stack is left brace pop it.
			
			//1. Continue popping the operator stack until you reach a operator with lowest priority then current token.
			//2. If the top item of the stack is left brace stop popping the operator. Left brace can be popped only 
			toOutput = token.compare(stack.peek()) >= 0 && stack.peek().getType() != ExpressionTokenType.LEFT_BRACE;
			if(toOutput) {
				ExpressionToken operatorToken = stack.pop();
				builder.append(operatorToken.getValue().value());
			}
		}
		return builder;
	}
	*/
	
	
	private StringBuilder outputOperator(ExpressionToken token, DataStack<Token, ExpressionToken> stack, StringBuilder builder) {
		boolean toOutput = true;
		ExpressionToken operatorToken = null;
		while(!stack.isEmpty() && toOutput) {
			//If the current token is right brace and top stack is left brace pop it.
			
			//1. Continue popping the operator stack until you reach a operator with lowest priority then current token.
			//2. If the top item of the stack is left brace stop popping the operator. Left brace can be popped only
			//If the current token is right brace this section of code is skipped.
			toOutput = token.compare(stack.peek()) >= 0 && stack.peek().getType() != ExpressionTokenType.LEFT_BRACE;
			if(toOutput) {
				operatorToken = stack.pop();
				builder = outputToken(operatorToken, builder);
			}
			
			/*
			//If the current token is right brace, pop the stack till first left brace is reached. 
			if (token.getType() == ExpressionTokenType.RIGHT_BRACE) {
				operatorToken = stack.pop();
				toOutput = operatorToken.getType() != ExpressionTokenType.LEFT_BRACE;
				if(toOutput) {
					builder = outputToken(operatorToken, builder);
				}
			}
			*/
		}
		return builder;
	}
	
	private StringBuilder outputToken(ExpressionToken token, StringBuilder builder) {
		builder.append(token.getValue().value());
		return builder;
	}
	
	public static void main(String[] args) {
		Expression expr1 = new Expression("5 / 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 / 4))");
		Expression expr2 =  new Expression("1 + (2 * 3) + (4 * (5 + 6))");
		Expression expr3 = new Expression("1+(2+3)");
		Expression expr4 = new Expression("2+5+7");
		Expression expr5 = new Expression("((3+4)*5)");
		Expression expr6 = new Expression("1+2*3");
		Expression expr7 = new Expression("1+-4+-2");
		Expression expr8 = new Expression("1+^-4+-2");
		Expression expr9 = new Expression("1+(2^^3)");
		ExpressionData exprData = new ExpressionData(0,expr9);
		ExpressionCalculator calculator = new ExpressionCalculator();
		calculator.calculate(exprData);
	}
}
