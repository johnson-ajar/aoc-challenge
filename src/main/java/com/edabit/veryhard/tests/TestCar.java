package com.edabit.veryhard.tests;

public class TestCar {
	private static class Car {
		private String color = "";
		public Car(String clr) {
			this.color = clr;
		}
		public String getColor() {
			return this.color;
		}
	}
	
	public static void main(String[] args) {
		Car ford = new Car("Black");
		
	}
	
	private static void updateColor(Car car ) {
		
	}
	
}
