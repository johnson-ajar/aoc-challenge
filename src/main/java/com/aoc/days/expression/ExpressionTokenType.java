package com.aoc.days.expression;


/***
 * Associativity
 * Postfix	( ) [ ] -> . ++ --					Left to right
 * Unary	+ - ! ~ ++ -- (type)* & sizeof 		Right to left
 * Multiplicative	* / %						Left to right
 * Additive		+-								Left to right
 * Shift		<<>>							Left to right
 * Relational	< <= > >=						Left to right
 * Equality		== !=							Left to right
 * Bitwise AND	& 								Left to right
 * Bitwise XOR	|								Left to	right
 * Bitwise OR	|								Left to right
 * Logical AND	&&								Left to right
 * Logical OR	||								Left to right
 * Conditional	?:								Right to left
 * Assignment	= += -= /= %= >>= <<= &= ^= |=	Right to left
 * Comma		,								Left to right
 * */
public enum ExpressionTokenType implements TokenType {
	
	EQUAL(Priority.LEVEL0, Association.RIGHT),
	NUMBER(Priority.LEVEL0, Association.NONE),
	VARIABLE(Priority.LEVEL0, Association.NONE),
	ADD(Priority.LEVEL1, Association.LEFT),
	SUBSTRACT(Priority.LEVEL1, Association.LEFT),
	DIVISION(Priority.LEVEL2, Association.LEFT),
	MULTIPLY(Priority.LEVEL2, Association.LEFT),
	EXPONENTIAL(Priority.LEVEL3, Association.RIGHT),
	UNARY(Priority.LEVEL4, Association.LEFT),
	LEFT_BRACE(Priority.LEVEL0, Association.NONE),
	RIGHT_BRACE(Priority.LEVELInf, Association.NONE);
	
	private int id;
	private int priority;
	private Association assoc;
	private ExpressionTokenType(Priority priority, Association inAssoc) {
		this.id = this.ordinal();
		this.priority = priority.level();
		this.assoc = inAssoc;
	}
	
	@Override
	public int getId() {
		return this.id;
	}
	
	public int priority() {
		return priority;
	}
	
	public Association association() {
		return this.assoc;
	}
	
	@Override
	public String toString() {
		return this.name();
	}
}
