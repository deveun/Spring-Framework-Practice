//***REFERENCE***//
//https://www.youtube.com/watch?v=9cDHggH0ENA&list=PLPvokKzUkaLeiLUDKpqRdP8jtLU8lLKgR&index=8//

package com.spring.ex5;

import java.util.Scanner;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//////String configLocation = "classpath:applicationCTX.xml";
		//////GenericXmlApplicationContext ctx = new GenericXmlApplicationContext(configLocation);
		//ctx.load("applicationCTX.xml");
		//ctx.refresh();
		
		Scanner scanner = new Scanner(System.in);
		String config = null;
		String str = scanner.next();
		
		if(str.equals("dev"))
			config = "dev";
		else if(str.equals("run"))
			config = "run";
		
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		//Select context using config(profile)
		ctx.getEnvironment().setActiveProfiles(config);
		ctx.load("applicationCTX_dev.xml", "applicationCTX_run.xml");
		ctx.refresh();
		
		ServerInfo serverinfo = ctx.getBean("serverInfo",ServerInfo.class);
		System.out.println("IpNum: " + serverinfo.getIpNum());
		System.out.println("PortNum: " + serverinfo.getPortNum());
		
		ctx.close();	
	}

}
