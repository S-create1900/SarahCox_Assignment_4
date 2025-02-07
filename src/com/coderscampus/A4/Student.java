package com.coderscampus.A4;
//Pojo:  Student ID,Student Name,Course,Grade

public class Student {
	public int studentId;
	public String studentName;
	public String course;
	public int grade;

	public Student(int studentId, String studentName, String course, int grade) {
		this.studentId = studentId;
		this.studentName = studentName;
		this.course = course;
		this.grade = grade;
	}

	public int getStudentID() {
		return studentId;
	}

	public void setStudentID(int studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

}
