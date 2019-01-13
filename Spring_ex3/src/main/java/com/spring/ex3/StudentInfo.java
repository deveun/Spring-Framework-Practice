package com.spring.ex3;

public class StudentInfo {

	private Student student;


	//constructor
	public StudentInfo() {	}
	public StudentInfo(Student student) {
		super();
		this.student = student;
	}
	
	//setter
	public void setStudent(Student student) {
		this.student = student;
	}

	public void getStudentInfo() {
		if(student != null) {
			System.out.println("Name : " + student.getName());
			System.out.println("Age : " + student.getAge());
			System.out.println("GradeNum : " + student.getGradeNum());
			System.out.println("ClassNum : " + student.getClassNum());
		}
	}
}

