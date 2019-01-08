//REFERENCE===================
//https://www.youtube.com/watch?v=CsOrR-4nH-s&list=PLPvokKzUkaLeiLUDKpqRdP8jtLU8lLKgR&index=1
package com.spring.ex;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/* Without using Spring framework =========================
		MyCalculator mycalc = new MyCalculator();
		Calculator calc = new Calculator();
		
		mycalc.setCalculator(calc);
		mycalc.setFirstNum(10);
		mycalc.setSecondNum(2);
		=========================================================*/
		
		/* ===== Spring Framework ===== */
		String configLocation = "classpath:applicationCTX.xml";
		AbstractApplicationContext ctx= new GenericXmlApplicationContext(configLocation);
		MyCalculator mycalc = ctx.getBean("myCalculator", MyCalculator.class);
		
		mycalc.add();
		mycalc.sub();
		mycalc.mult();
		mycalc.div();
	}

}
