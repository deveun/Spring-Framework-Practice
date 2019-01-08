package com.spring.ex;

public class MyCalculator {
	
	public Calculator calculator;
	public int firstNum;
	public int secondNum;
	
	public void setCalculator(Calculator calculator) {
		this.calculator = calculator;
	}
	public void setFirstNum(int firstNum) {
		this.firstNum = firstNum;
	}
	public void setSecondNum(int secondNum) {
		this.secondNum = secondNum;
	}
	
	public void add()
	{
		calculator.addition(firstNum, secondNum);
	}
	public void sub()
	{
		calculator.subtraction(firstNum, secondNum);
	}
	public void mult()
	{
		calculator.multiplication(firstNum, secondNum);
	}
	public void div()
	{
		calculator.division(firstNum, secondNum);
	}


}
