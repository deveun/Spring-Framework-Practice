package com.spring.ex3;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String configLocation = "classpath:applicationCTX.xml";
		AbstractApplicationContext ctx = new GenericXmlApplicationContext(configLocation);
		
		StudentInfo studentinfo = ctx.getBean("studentinfo", StudentInfo.class);
		Student student2 = ctx.getBean("student2", Student.class);
		
		studentinfo.getStudentInfo();
		System.out.println("==========================");
		
		studentinfo.setStudent(student2);
		studentinfo.getStudentInfo();	
		
		ctx.close();

	}

}
