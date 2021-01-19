package com.aoc.days.expression;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

import com.aoc.data.exception.ParserException;
import com.aoc.data.structure.DataStack;
import com.aoc.data.tokenizer.DataTokenizer;
import com.aoc.data.tokenizer.Token;
import com.aoc.data.tokenizer.TokenPattern;
import com.aoc.day1.iterator.IListIterator;

public class ExpressionTokenizer extends DataTokenizer<ExpressionToken> {
	/***
	 * //Common matching Symbols--------------------------------------------------------------
	 * . Matches any character
	 * ^regex 		Finds regex that must match at the beginning of the line.
	 * regex$ 		Finds regex that must match at the end of the line.
	 * [abc] 		Set definition, can match the letter a or b or c.
	 * [abc][vz] 	Set definition, can match a or b or c followed by either v or z.
	 * [^abc]		Matches any character except a or b or c.
	 * [a-d1-7]		Ranges: matches a letter between a and d and figures from 1 to 7, but not d1.
	 * X|Z			Find X or Z
	 * XZ			Find X directly followed by Z.
	 * $			Checks if a line end follows.
	 * 
	 * //Meta Characters--------------------------------------------------------------------------
	 * \d			Any digit, short for [0-9]
	 * \D			A non-digit, short for [^0-9]
	 * \s			A whitespace character, short for [\t\n\x0b\r\f]
	 * \S			A non-whitespace character, short for.
	 * \w			A word character, short for [a-zA-Z_0-9]
	 * \W			A non-word character [^\w]
	 * \S+			Several non-whitespace characters
	 * \b			Matches a word boundary where a word character is [a-zA-Z0-9_]
	 * 
	 * //Quantifier	
	 * ? 			occurs zero or one time
	 * *			occurs zero or more time, is short for {0,}
	 * +			Occurs one or more times, is short for {1,}
	 * {X}			occurs X no of times. describes the order of the preceding liberal 
	 * 				\d{3} searches for three digits, .{10} for any character sequence of length 10
	 * {X,Y}		Occurs between X and Y times
	 * 				\d{1,4} means \d must occur at least once and at a maximum of four.
	 * *?			? after a quantifier makes it a reluctant quantifier. It tries to find the 
	 * 				smallest match. This makes the regular expression stop at the first match.
	 * */
	
			
	private List<ExpressionToken> tokens = new ArrayList<>();
	private DataStack<Token, ExpressionToken>  tokenStack = new DataStack<>();
	public ExpressionTokenizer() {
		
		this.addPattern("^[0-9]+", ExpressionTokenType.NUMBER);
		this.addPattern("^[a-zA-Z0-9]+", ExpressionTokenType.VARIABLE);
		this.addPattern("^[\\*]", ExpressionTokenType.MULTIPLY); //Multiplication
		this.addPattern("^[-]", ExpressionTokenType.SUBSTRACT); //Substraction
		this.addPattern("^[+]", ExpressionTokenType.ADD); //Addition
		this.addPattern("^[/]", ExpressionTokenType.DIVISION); //Diviision
		this.addPattern("^[\\^]", ExpressionTokenType.EXPONENTIAL);// Exponential
		//this.addPattern("^[\\-+/]", 2); //Addition
		this.addPattern("^[\\(]", ExpressionTokenType.LEFT_BRACE); // left brace
		this.addPattern("^[\\)]", ExpressionTokenType.RIGHT_BRACE); // right brace
	}
	
	
	@Override
	public List<ExpressionToken> tokenize(String expression) {
		int tokenCount = 0;
		String tmpExpr = expression.trim();//removing trailing spaces;
		while(!tmpExpr.equals("") && tmpExpr.length()>0) {
			boolean containPattern = false;
			for(TokenPattern pattern : this.getPatterns()) {
				Matcher matcher = pattern.matcher(tmpExpr);
				if(matcher.find()) {
					containPattern = true;
					//matched token.
					String token  = matcher.group().trim();
					ExpressionToken exprToken = createExpressionToken(tokenCount, pattern, token);//new ExpressionToken(tokenCount, new Token(pattern.getType(), token));
					tokens.add(exprToken);
					tokenStack.push(exprToken);
					tokenCount+=1;
					//If there is a pattern match remove the string match and re-run the pattern matching.
					tmpExpr = matcher.replaceFirst("").trim();
					break;
				}
			}
			if(!containPattern) {
				throw new ParserException("Unexpected character in input "+tmpExpr);
			}
		}
		return tokens;
	}
	
	private ExpressionToken createExpressionToken(int tokenCount, TokenPattern pattern, String token) {
		boolean isUnaryOperator = pattern.getType() == ExpressionTokenType.ADD 
					|| pattern.getType() == ExpressionTokenType.SUBSTRACT;
		// Check for the unary operator.
		//If the incoming token is an operator and the last element of the stack is an operator it should be an unary operator.
		//If the stack is empty and current token is an operator it should be an unary operator.
		if(isUnaryOperator) {
			if(tokenStack.isEmpty() || (!tokenStack.isEmpty() && isOperator())) {
				return new ExpressionToken(tokenCount, new Token(ExpressionTokenType.UNARY, token));
			} 
		}
		
		return new ExpressionToken(tokenCount, new Token(pattern.getType(), token));
	}
	
	private boolean isOperator() {
		return tokenStack.last().getType() != ExpressionTokenType.NUMBER 
				&& tokenStack.last().getType() != ExpressionTokenType.VARIABLE;
	}
	
	public DataStack<Token, ExpressionToken> parse(String expression) {
		this.tokenize(expression);
		return this.tokenStack;
	}
	
	public static void main(String[] args) {
		ExpressionTokenizer tokenizer = new ExpressionTokenizer();
		String expr1 = "k+l-m*n+(o^p)*w/u/v*t+q";
		String expr2 = "-10+20*-(19+29-30)";
		DataStack<Token, ExpressionToken> tokens = tokenizer.parse(expr1);
		IListIterator<Token, ExpressionToken> iterator = tokens.iterator();
		while(iterator.hasNext() && !tokens.isEmpty()) {
			System.out.println(tokens.pop());
		}
	}
	
}
