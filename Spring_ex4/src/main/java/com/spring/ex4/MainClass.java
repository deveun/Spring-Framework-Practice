//***REFERENCE***//
//https://www.youtube.com/watch?v=9cDHggH0ENA&list=PLPvokKzUkaLeiLUDKpqRdP8jtLU8lLKgR&index=8//

package com.spring.ex4;

import java.io.IOException;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.io.support.ResourcePropertySource;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//make environment and add properties(external) using it.
		ConfigurableApplicationContext ctx = new GenericXmlApplicationContext();
		ConfigurableEnvironment env = ctx.getEnvironment();
		MutablePropertySources propertySources = env.getPropertySources();
		
		try { 
			//get context >> get environment >> get property sources
			// >> add properties(add last) >> get properties (external)  
			propertySources.addLast(new ResourcePropertySource("classpath:admin.properties"));
			System.out.println(env.getProperty("admin.id"));
			System.out.println(env.getProperty("admin.pw"));
		} catch(IOException e) {}
		///////////////////////////////////
		//using external data file, set properties.
		GenericXmlApplicationContext gctx = (GenericXmlApplicationContext) ctx;
		gctx.load("applicationCTX.xml");
		gctx.refresh();
		
		AdminConnection adminConnection = gctx.getBean("adminConnection", AdminConnection.class);
		System.out.println(adminConnection.getId());
		System.out.println(adminConnection.getPw());
		
		ctx.close();
	}
}
