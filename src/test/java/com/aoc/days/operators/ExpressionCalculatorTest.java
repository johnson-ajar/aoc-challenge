package com.aoc.days.operators;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.aoc.data.model.ExpressionData;

public class ExpressionCalculatorTest {
	
	@ParameterizedTest
	@CsvSource({"1+(2+3),123++",
				"2+5+7,25+7+",
				"((3+4)*5),34+5*",
				"1+2*3,123*+",
				"1 + (2 * 3) + (4 * (5 + 6)),123*+456+*+",
				"5 / 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 / 4)),59/73*3*93*+864/++*",
				"1+-4+-2, 1-4+-2+",
				"1+^-4+-2,1-4^+-2+",
				"k+l-m*n+(o^p)*w/u/v*t+q,kl+mn*-op^w*u/v/t*+q+",
				"2^^3,23^^",
				"2^(^3),23^^",
				"(2^)^3,2^3^"
		})
	public void testParser(String actual, String expected) {
		System.out.println(actual+ "  "+ expected);
		ExpressionData exprData = new ExpressionData(0, actual);
		ExpressionCalculator1 calculator = new ExpressionCalculator1();
		String postfix = calculator.calculate(exprData);
		Assertions.assertEquals(postfix, expected);
	}
}
