package com.spring.ex3;

public class Student {
	private String name;
	private String age;
	private String gradeNum;
	private String classNum;
	
	//constructor
	public Student(String name, String age, String gradeNum, String classNum) {
		super();
		this.name = name;
		this.age = age;
		this.gradeNum = gradeNum;
		this.classNum = classNum;
	}
	//setter
	public void setName(String name) {
		this.name = name;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public void setGradeNum(String gradeNum) {
		this.gradeNum = gradeNum;
	}
	public void setClassNum(String classNum) {
		this.classNum = classNum;
	}
	//getter
	public String getName() {
		return name;
	}
	public String getAge() {
		return age;
	}
	public String getGradeNum() {
		return gradeNum;
	}
	public String getClassNum() {
		return classNum;
	}
}
