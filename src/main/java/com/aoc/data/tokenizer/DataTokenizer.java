package com.aoc.data.tokenizer;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.aoc.data.exception.ParserException;
import com.aoc.days.expression.TokenType;

public class DataTokenizer<T> {
	final List<TokenPattern> patterns = new ArrayList<TokenPattern>();
	final List<T> tokens = new ArrayList<T>();
	
	public DataTokenizer() {
		
	}
	
	public void addPattern(String pattern, TokenType inType) {
		patterns.add(new TokenPattern(Pattern.compile(
				String.format("^(%s)",pattern))
				, inType));
	}
	
	public List<T> tokenize(String str) {
		String s = str.trim(); //removing the trailing spaces
		tokens.clear();
		while (!s.equals("")) { //If the string is not empty
			boolean containMatch = false;
			for(TokenPattern pattern : patterns) {
				Matcher m = pattern.matcher(s);
				if (m.find()) {
					containMatch = true;
					String tok = m.group().trim();
					s = m.replaceFirst("").trim();
					tokens.add((T)new Token(pattern.getType(), tok));
					break;
				}
			}
			if(!containMatch) {
				throw new ParserException("Unexpected character in input "+s);
			}
		}
		return tokens;
	}
	
	protected List<TokenPattern> getPatterns() {
		return this.patterns;
	}
	
	public List<T> getTokens() {
		return tokens;
	}
	
	private static void passwordTokenizer() {
		DataTokenizer<Token> tokenizer = new DataTokenizer<>();
		tokenizer.addPattern("[0-9]+", PasswordTokenType.NUMBER);
		tokenizer.addPattern("-", PasswordTokenType.HYPHEN);
		tokenizer.addPattern(":", PasswordTokenType.COLON);
		tokenizer.addPattern("[a-zA-Z]+", PasswordTokenType.ALPHABETS);
		
		tokenizer.tokenize("10-12 s: hello");
		for(Token token : tokenizer.getTokens()) {
			System.out.println(token);
		}
	}
	
	private static void passportTokenizer() {
		String value = "ecl:amb pid:690616023 byr:1994 iyr:2014 hgt:172cm hcl:#c0946f eyr:2022";
		DataTokenizer<Token> tokenizer = new DataTokenizer<>();
		
		tokenizer.addPattern("[a-zA-Z]+\\:((#*)[a-z0-9]|(\\d+))(\\w*)", PasswordTokenType.ALPHABETS);
		
		tokenizer.tokenize(value);
		for(Token token : tokenizer.getTokens()) {
			System.out.println(token);
		}
	}
	
	public static void main(String[] args) {
		passwordTokenizer();
		//passportTokenizer();
	}
}
