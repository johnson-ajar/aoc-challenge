package com.aoc.days.expression;

import java.util.ArrayList;
import java.util.List;

import com.aoc.data.tokenizer.Token;

public class ExpressionNode {
	private final List<ExpressionNode> childNodes;
	private ExpressionToken token;
	public ExpressionNode(ExpressionToken inToken) {
		this.token = inToken;
		childNodes = new ArrayList<>();
	}
		
	public ExpressionToken getToken() {
		return this.token;
	}
	
	public void setToken(ExpressionToken token) {
		this.token = token;
	}
	
	public void addNode(ExpressionNode inNode) {
		this.childNodes.add(inNode);
	}
	
	public List<ExpressionNode> getNodes() {
		return this.childNodes;
	}
	
	public static void main(String[] args) {
		ExpressionNode node  = new ExpressionNode(new ExpressionToken(1,new Token(ExpressionTokenType.ADD, "+")));
		ExpressionNode left = new ExpressionNode(new ExpressionToken(2, new Token(ExpressionTokenType.NUMBER, "A")));
		ExpressionNode right = new ExpressionNode(new ExpressionToken(2, new Token(ExpressionTokenType.NUMBER, "B")));
		
		System.out.println(node);
	}
}
