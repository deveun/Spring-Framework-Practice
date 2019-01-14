package com.spring.ex4;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

public class AdminConnection implements EnvironmentAware, DisposableBean, InitializingBean{
	
	private Environment env;
	private String id;
	private String pw;
	
	public Environment getEnv() {
		return env;
	}
	public void setEnv(Environment env) {
		this.env = env;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	
	////////Override Implements(Interface) Methods
	@Override
	public void setEnvironment(Environment environment) {
		// TODO Auto-generated method stub
		System.out.println("'setEnvironment()' called.");
		setEnv(environment);
	}
	//call when Initializing bean
	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("'afterPropertiesSet()' called.");
		setId(env.getProperty("admin.id"));
		setPw(env.getProperty("admin.pw"));	
	}
	//call when destroying bean
	@Override
	public void destroy() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("'destroy()' called.");
		
	}
	
	
	
}
