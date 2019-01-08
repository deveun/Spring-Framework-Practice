package com.spring.ex;

public class Calculator {
	
	public void addition(int firstNum, int secondNum)
	{
		System.out.print("Addition: ");
		System.out.println(firstNum+" + "+secondNum+" = "+(firstNum+secondNum));
	}
	
	public void subtraction(int firstNum, int secondNum)
	{
		System.out.print("Subtraction: ");
		System.out.println(firstNum+" - "+secondNum+" = "+(firstNum-secondNum));
	}
	
	public void multiplication(int firstNum, int secondNum)
	{
		System.out.print("Multiplication: ");
		System.out.println(firstNum+" * "+secondNum+" = "+(firstNum*secondNum));
	}
	
	public void division(int firstNum, int secondNum)
	{
		System.out.print("Division: ");
		System.out.println(firstNum+" / "+secondNum+" = "+(firstNum/secondNum));
	}

}
