package com.coderscampus.A4;
//Pojo:  Student ID,Student Name,Course,Grade

public class User {
	public String studentId;
	public String studentName;
	public String course;
	public int grade;
	
	public User(String studentID, String studentName, String course, int grade) {
		this.studentId = studentID;
		this.studentName = studentName;
		this.course = course;
		this.grade = grade;
	}

	public String getStudentID() {
		return studentId;
	}

	public void setStudentID(String studentID) {
		this.studentId = studentID;
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
