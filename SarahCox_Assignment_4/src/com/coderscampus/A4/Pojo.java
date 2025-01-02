package com.coderscampus.A4;

public class Pojo {
	private String id;
	private String name;
	private String course;
	private String grade;
	
	public Pojo (String studentid, String studentname, String course, String grade) {
		this.id = id;
		this.name = name;
		this.course = course;
		this.grade = grade;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}


}
