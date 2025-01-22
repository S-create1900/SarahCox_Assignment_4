package com.coderscampus.A4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

public class StudentService {
	private Student[] students; // Array to hold all students
	private int studentCount; // Counter for the number of students
	private String csvFilePath;

	// Arrays for each course
	Student[] compSciStudents = new Student[100]; // Fixed size for Computer Science students
	Student[] statStudents = new Student[100]; // Fixed size for Statistics students
	Student[] apmthStudents = new Student[100]; // Fixed size for APMTH students

	// Counters for each course
	int compSciCount = 0; // Counter for Computer Science students
	int statCount = 0; // Counter for Statistics students
	int apmthCount = 0; // Counter for APMTH students

	public StudentService() {
		this.csvFilePath = "student-master-list.csv"; // Specify the CSV file name
		this.students = new Student[100]; // Initialize the students array
		this.studentCount = 0; // Initialize student count
		loadStudentsFromCsv(); // Load student data from the CSV file
		separateAndWriteUsersByCourse(); // Separate students by course and write to CSV files
	}

	// Step 1: Read student-master-list.csv and create Student objects
	private void loadStudentsFromCsv() {
		try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
			String line;

			// Read the header line
			if ((line = br.readLine()) != null) {
			}

			// Read each line of the CSV file
			while ((line = br.readLine()) != null) {
				String[] data = line.trim().split(",");
				if (data.length < 4) { // Check for at least 4 fields
					System.err.println("Invalid line: " + line);
					continue;
				}

				// Create a new StudentB4 object
				int studentId = Integer.parseInt(data[0].trim()); // Student ID
				String fullName = data[1].trim(); // Full name
				String courseWithNumber = data[2].trim(); // Course with number
				String course = courseWithNumber.replaceAll("\\s*\\d+$", "").trim(); // Remove trailing number
				int grade = Integer.parseInt(data[3].trim()); // Convert grade to int

				// Populate the student to the appropriate course array
				addStudentToCourse(course, studentId, fullName, grade);
			}

		} catch (IOException e) {
			System.err.println("Error reading the file: " + e.getMessage()); // Print any IO exceptions
		}
	}

	// Step 2: Populate the appropriate course array
	private void addStudentToCourse(String course, int studentId, String studentName, int grade) {
		if (course.equals("COMPSCI")) {
			if (compSciCount < compSciStudents.length) {
				compSciStudents[compSciCount++] = new Student(studentId, studentName, course, grade);
			} else {
				System.err.println("Maximum limit reached for COMPSCI students.");
			}
		} else if (course.equals("STAT")) {
			if (statCount < statStudents.length) {
				statStudents[statCount++] = new Student(studentId, studentName, course, grade);
			} else {
				System.err.println("Maximum limit reached for STAT students.");
			}
		} else if (course.equals("APMTH")) {
			if (apmthCount < apmthStudents.length) {
				apmthStudents[apmthCount++] = new Student(studentId, studentName, course, grade);
			} else {
				System.err.println("Maximum limit reached for APMTH students.");
			}
		} else {
			System.err.println("Unknown course: " + course);
		}
	}

	// Step 3: Separate the students by course and write to CSV files
	private void separateAndWriteUsersByCourse() {
		// Sort each course array by grade in descending order
		Arrays.sort(compSciStudents, 0, compSciCount, Comparator.comparingInt(Student::getGrade).reversed());
		Arrays.sort(statStudents, 0, statCount, Comparator.comparingInt(Student::getGrade).reversed());
		Arrays.sort(apmthStudents, 0, apmthCount, Comparator.comparingInt(Student::getGrade).reversed());

		writeStudentsToCsv(compSciStudents, compSciCount, "course1.csv");
		writeStudentsToCsv(apmthStudents, apmthCount, "course2.csv");
		writeStudentsToCsv(statStudents, statCount, "course3.csv");

	}

	// Method to write students to a CSV file
	private void writeStudentsToCsv(Student[] students, int count, String fileName) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
			bw.write("Student ID,Full Name,Course,Grade\n"); // Write header
			for (int i = 0; i < count; i++) {
				if (students[i] != null) {
					bw.write(students[i].getStudentID() + "," + students[i].getStudentName() + ","
							+ students[i].getCourse() + "," + students[i].getGrade() + "\n");
				}
			}
			System.out.println(fileName);
		} catch (IOException e) {
			System.err.println("Error writing to file: " + e.getMessage());
		}
	}

	// Method to sort students by grade in descending order
	public void sortStudentsByGrade() {
		Arrays.sort(students, 0, studentCount, Comparator.comparingInt(Student::getGrade).reversed());
	}

	// Method to display all students
	public void displayAllStudents() {
		for (int i = 0; i < studentCount; i++) {
			System.out.println(students[i]);
		}
	}
}