package com.aoc.days.operators;


import com.aoc.data.model.Expression;
import com.aoc.data.model.ExpressionData;
import com.aoc.data.structure.DataStack;
import com.aoc.data.tokenizer.Token;
import com.aoc.day1.iterator.IListIterator;
import com.aoc.days.expression.Association;
import com.aoc.days.expression.ExpressionToken;
import com.aoc.days.expression.ExpressionTokenType;
import com.aoc.days.expression.ExpressionTokenizer;

/**
 * You should formulate the algorithm using the following 6 Rules.
 * 1. Scan the input string (infix notation) from left to right. One pass is sufficient.
 * 2. If the next symbol scanned is an operand, it may be immediately appended to the postfix string.
 * 3. If the next symbol is an operator,
 * 	i. Pop and append to the postfix string every operator on the stack that
 * 		a. is above the most recently scanned left parenthesis, and
 * 		b. has precedence higher than or is a right associative operator of equal precedence to that of the 
 * 			new operator
 * 	ii. Push the new operator onto the stack.
 * 4. When a left parenthesis is seen, it must be pushed onto the stack.
 * 5. When a right parenthesis is seen, all operators down to the most recently scanned left parenthesis must be
 * 		popped and appended to the postfix string. Furthermore, this pair of parantheses must be discarded.
 * 6. When the infix string is completely scanned, the stack may still contain some operators. [Why are there
 * 		no parenthesis on the stack at this point?] All the remaining operators should be popped and appended to the postfix
 * 		string.
 * 
 * **/
public class ExpressionCalculator1 {
	private final ExpressionTokenizer tokenizer;
	private final DataStack<Token, ExpressionToken> stack;
	private final StringBuilder builder;
	public ExpressionCalculator1() {
		tokenizer = new ExpressionTokenizer();
		stack = new DataStack<Token, ExpressionToken>();
		builder = new StringBuilder();
	}
	
	public String calculate(ExpressionData inData) {
		String expression = inData.getValue().getExpression();
		DataStack<Token, ExpressionToken> tokens = tokenizer.parse(expression);
		IListIterator<Token, ExpressionToken> iterator = tokens.iterator();
		
		while(iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		StringBuilder builder = toRPN(tokens.iterator());
		System.out.println(builder.toString());
		return builder.toString();
	}
	
	public StringBuilder toRPN(IListIterator<Token, ExpressionToken> iterator)  {
		
		while(iterator.hasNext()) {
			ExpressionToken token = iterator.next();
			operator(token);
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
	
	private StringBuilder operator(ExpressionToken token) {
		
		switch(token.getType()) {
			case ADD:
				return add(token);
			case SUBSTRACT:
				return substract(token);
			case MULTIPLY:
				return multiply(token);
			case DIVISION:
				return division(token);
			case EXPONENTIAL:
				return exponential(token);
			case UNARY:
				return operand(token);
			default:
				return operand(token);
		}
	}
	
	
	private StringBuilder operand(ExpressionToken token) {
		switch(token.getType()) {
			case LEFT_BRACE:
				return leftBrace(token);
			case RIGHT_BRACE:
				return rightBrace(token);
			default:
				this.outputToken(token);
				return builder;
		}
	}
	
	private StringBuilder leftBrace(ExpressionToken token) {
		//Note: Don't pop the operator just add left brace to the stack.
		stack.push(token);
		return builder;
	}
	
	private StringBuilder rightBrace(ExpressionToken token) {
		/*
		//Adding operators to the output inside paranthesis.
		boolean toOutput = true;
		while(!stack.isEmpty() && toOutput) {		
			ExpressionToken operatorToken = stack.pop();
			toOutput = operatorToken.getType() != ExpressionTokenType.LEFT_BRACE;
			if(toOutput) 
				outputToken(operatorToken);
		}
		*/
		outputOperator(token);
		return builder;
	}
	
	private StringBuilder add(ExpressionToken token) {
		outputOperator(token);
		stack.push(token);
		return builder;
	}
	
	private StringBuilder substract(ExpressionToken token) {
			outputOperator(token);
			stack.push(token);
		return builder;
	}
	
	private StringBuilder multiply(ExpressionToken token) {
		outputOperator(token);
		stack.push(token);
		return builder;
	}
	
	private StringBuilder division(ExpressionToken token) {
		outputOperator(token);
		stack.push(token);
		return builder;
	}
	
	private StringBuilder exponential(ExpressionToken token) {
		outputOperator(token);
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
	
	
	private StringBuilder outputOperator(ExpressionToken token) {
		boolean toOutput = true;
		ExpressionToken operatorToken = null;
		while(!stack.isEmpty() && toOutput) {
			//If the current token is right brace and top stack is left brace pop it.
			
			//1. Continue popping the operator stack until you reach a operator with lowest priority then current token.
			//1.1 If the current token priority is equal to the top stack token check for left association to add to the output.
			//otherwise push it to the stack.
			//2. If the top item of the stack is left brace stop popping the operator. Left brace can be popped only
			//If the current token is right brace this section of code is skipped.
			//toOutput = (token.compare(stack.peek()) >= 0 && stack.peek().getType().association() == Association.LEFT)
			//			&& stack.peek().getType() != ExpressionTokenType.LEFT_BRACE ;
			
			toOutput = (token.compare(stack.peek()) > 0 
					||(token.compare(stack.peek()) == 0 && stack.peek().getType().association() == Association.LEFT))
					&& stack.peek().getType() != ExpressionTokenType.LEFT_BRACE;
			if(toOutput) {
				operatorToken = stack.pop();
				outputToken(operatorToken);
			}
			
			
			//If the current token is right brace, pop the stack till first left brace is reached.
			//the left brace will not be added to the output.
			if (token.getType() == ExpressionTokenType.RIGHT_BRACE) {
				operatorToken = stack.pop();
				toOutput = operatorToken.getType() != ExpressionTokenType.LEFT_BRACE;
				if(toOutput) {
					outputToken(operatorToken);
				}
			}
			
		}
		if(token.getType() == ExpressionTokenType.RIGHT_BRACE && operatorToken.getType() != ExpressionTokenType.LEFT_BRACE) {
			System.out.println("Throw an exception that there is no corresponding left brace");
		}
		return builder;
	}
	
	private StringBuilder outputToken(ExpressionToken token) {
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
		Expression expr9 = new Expression("k+l-m*n+(o^p)*w/u/v*t+q");
		Expression expr10 = new Expression("(2^^3)");
		Expression expr11 = new Expression("(A+B*(C-D))/E");
		ExpressionData exprData = new ExpressionData(0,expr11);
		ExpressionCalculator1 calculator = new ExpressionCalculator1();
		calculator.calculate(exprData);
	}
}
