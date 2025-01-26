package com.coderscampus.A4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

public class StudentService {
	private String csvFilePath;

	// Arrays for each course
	private Student[] compSciStudents = new Student[100];
	private Student[] statStudents = new Student[100];
	private Student[] apmthStudents = new Student[100];

	// Counters for each course
	private int compSciCount = 0;
	private int statCount = 0;
	private int apmthCount = 0;

	public StudentService() {
		this.csvFilePath = "student-master-list.csv";
		readStudentsFromCsv();
		separateAndWriteUsersByCourse();
	}

	// Step 1: Read student-master-list.csv and create Student objects
	private void readStudentsFromCsv() {
		try (BufferedReader BufferedReader = new BufferedReader(new FileReader(csvFilePath))) {
			String line;

			if ((line = BufferedReader.readLine()) != null) {
			}

			// Read each line of the CSV file
			while ((line = BufferedReader.readLine()) != null) {
				String[] data = line.trim().split(",");
				if (data.length < 4) {
					continue;
				}

				// Create a new Student object
				int studentId = Integer.parseInt(data[0].trim());
				String studentName = data[1].trim();
				String course = data[2].trim();
				int grade = Integer.parseInt(data[3].trim());

				// Populate the student to the appropriate course array
				addStudentToCourse(course, studentId, studentName, grade);
			}

		} catch (IOException e) {
			System.err.println("Error reading the file: " + e.getMessage());
		}
	}

	// Step 2: Populate the appropriate course array
	private void addStudentToCourse(String courseWithNumber, int studentId, String studentName, int grade) {
		String course = courseWithNumber.replaceAll("\\s*\\d+$", "").trim(); // Extract course name without number
		if (course.equalsIgnoreCase("COMPSCI")) {
			if (compSciCount < compSciStudents.length) {
				compSciStudents[compSciCount++] = new Student(studentId, studentName, courseWithNumber, grade);
			} else {
				System.err.println("No more COMPSCI students.");
			}
		} else if (course.equalsIgnoreCase("STAT")) {
			if (statCount < statStudents.length) {
				statStudents[statCount++] = new Student(studentId, studentName, courseWithNumber, grade);
			} else {
				System.err.println("No more STAT students.");
			}
		} else if (course.equalsIgnoreCase("APMTH")) {
			if (apmthCount < apmthStudents.length) {
				apmthStudents[apmthCount++] = new Student(studentId, studentName, courseWithNumber, grade);
			} else {
				System.err.println("No more APMTH students.");
			}
		} else {
			System.err.println("Unknown course: " + courseWithNumber);
		}
	}

	// Step 3: Separate the students by course and write to CSV files
	private void separateAndWriteUsersByCourse() {
		// Sort each course array by grade in descending order
		Arrays.sort(compSciStudents, 0, compSciCount, Comparator.comparingInt(Student::getGrade).reversed());
		Arrays.sort(statStudents, 0, statCount, Comparator.comparingInt(Student::getGrade).reversed());
		Arrays.sort(apmthStudents, 0, apmthCount, Comparator.comparingInt(Student::getGrade).reversed());

		// Write sorted students to CSV files
		writeStudentsToCsv(compSciStudents, compSciCount, "course1.csv");
		writeStudentsToCsv(apmthStudents, apmthCount, "course2.csv");
		writeStudentsToCsv(statStudents, statCount, "course3.csv");
	}

	// Method to write students to a CSV file
	private void writeStudentsToCsv(Student[] students, int count, String fileName) {
		try (BufferedWriter BufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
			BufferedWriter.write("Student ID,Student Name,Course,Grade\n"); // Write header
			for (int i = 0; i < count; i++) {
				if (students[i] != null) {
					BufferedWriter.write(students[i].getStudentID() + "," + students[i].getStudentName() + ","
							+ students[i].getCourse() + "," + students[i].getGrade() + "\n");
				}
			}
		} catch (IOException e) {
		}
	}

	// Step 4: Method to sort students by grade in descending order
	public void sortStudentsByGrade() {
		Arrays.sort(compSciStudents, 0, compSciCount, Comparator.comparingInt(Student::getGrade).reversed());
		Arrays.sort(statStudents, 0, statCount, Comparator.comparingInt(Student::getGrade).reversed());
		Arrays.sort(apmthStudents, 0, apmthCount, Comparator.comparingInt(Student::getGrade).reversed());
	}
}