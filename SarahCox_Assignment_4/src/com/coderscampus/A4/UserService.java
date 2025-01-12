package com.coderscampus.A4;

import java.beans.Statement;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

public class UserService {
	private User[] student;
	private int studentCount;
	private String csvFilePath;

	public UserService() { // constructor - initializes csvFilePand, users, array, and studentCount
		this.csvFilePath = "student-master-list.csv";
		this.student = new User[100];
		this.studentCount = 0; // Initialize student count
		loadUsersFromCsv();
	}

// Read users from the CSV file
	public void loadUsersFromCsv() {
		try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
			String line;
			reader.readLine();
			while ((line = reader.readLine()) != null) {
				line = line.trim(); // Trim the line to avoid issues with leading/trailing whitespace
				if (line.isEmpty()) {
					continue; // Skip empty lines
				}

				String[] data = line.split(",");
				if (data.length < 4) {
					System.err.println("Invalid line: " + line);
					continue; // Skip this line if it doesn't have enough data
				}

				String studentId = data[0].trim();
				String studentName = data[1].trim();
				String course = data[2].trim();
				int grade;

				try {
					grade = Integer.parseInt(data[3].trim());
				} catch (NumberFormatException e) {
					System.err.println("Invalid grade for student ID " + studentId + ": " + data[3]);
					continue; // Skip this line if the grade is not a valid integer
				}

				// Add user to the array
				addUser(new User(studentId, studentName, course, grade));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Method to add a user to the array
	private void addUser(User user) {
		if (studentCount < student.length) {
			student[studentCount] = user; // Add user to the array
			studentCount++; // Increment the count
		} else {
			System.err.println("User  array is full. Cannot add more users.");
		}
	}

	// Method to separate users into course lists and write to CSV files
	public void separateAndWriteUsersByCourse() {
		User[] compsciUsers = new User[studentCount];
		User[] statUsers = new User[studentCount];
		User[] apmthUsers = new User[studentCount];

		int compsciCount = 0;
		int statCount = 0;
		int apmthCount = 0;

		// Separate users based on course prefixes
		for (int i = 0; i < studentCount; i++) {
			User user = student[i];
			String course = user.getCourse();
			if (course.startsWith("COMPSCI")) {
				compsciUsers[compsciCount++] = user;
			} else if (course.startsWith("STAT")) {
				statUsers[statCount++] = user;
			} else if (course.startsWith("APMTH")) {
				apmthUsers[apmthCount++] = user;
			}
		}

		// Sort and write to CSV files
		try {
			writeToCsv("course1.csv", sortUsers(compsciUsers, compsciCount), compsciCount);
			writeToCsv("course2.csv", sortUsers(apmthUsers, apmthCount), apmthCount);
			writeToCsv("course3.csv", sortUsers(statUsers, statCount), statCount);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Method to write users to a CSV file
	private void writeToCsv(String filePath, User[] users, int count) throws IOException {
		try (FileWriter writer = new FileWriter(filePath)) {
			// Write header
			try {
				writer.append("Student ID,Student Name,Course,Grade\n");
			} catch (IOException e) {

				e.printStackTrace();
			}
			// Write user data
			for (int i = 0; i < count; i++) {
				if (users[i] != null) {
					try {
						writer.write(users[i].getStudentID() + "," + users[i].getStudentName() + ","
								+ users[i].getCourse() + "," + users[i].getGrade() + "\n");
					} catch (IOException e) {

						e.printStackTrace();
					}
				}
			}
		}

	}

	// Sort users by grade in descending order
	public void sortUsersByGrade() {
	    Arrays.sort(student, 0, studentCount, new Comparator<User>() {
	        @Override
	        public int compare(User u1, User u2) {
	            return Integer.compare(u2.getGrade(), u1.getGrade()); // Descending order
	        }
	    });

	    // Print a separator for clarity
	    System.out.println("---- Sorted Users by Grade ----");
	    for (int i = 0; i < studentCount; i++) {
	        if (student[i] != null) {
	            System.out.println(student[i].getStudentName() + ": " + student[i].getGrade());
	        }
	    }
	}

	private User[] sortUsers(User[] users, int count) {
		// Create a new array to hold non-null users
		User[] nonNullUsers = new User[count];
		int index = 0;

		// Copy non-null users to the new array
		for (int i = 0; i < count; i++) {
			if (users[i] != null) {
				nonNullUsers[index++] = users[i];
			}
		}
		return nonNullUsers;
	}

	public void printCsvContents() {

	}

	public void printCsvContents(String csvFilePath2) {

	}
}