package com.coderscampus.A4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class UserService {
	private User[] users; // Native array for users
	private int studentCount; // Current count of students
	private String csvFilePath;

	public UserService() {
		this.csvFilePath = "student-master-list.numbers"; // Set the CSV file path
		this.users = new User[100]; // Initialize with a fixed size (e.g., 100)
		this.studentCount = 0; // Initialize student count
		loadUsersFromCsv(); // Load users from the CSV file
	}

	// Read users from the CSV file
	public void loadUsersFromCsv() {
		try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
			String line;
			// Skip header
			reader.readLine();
			while ((line = reader.readLine()) != null) {
				// Trim the line to avoid issues with leading/trailing whitespace
				line = line.trim();
				if (line.isEmpty()) {
					continue; // Skip empty lines
				}

				String[] data = line.split(",");
				// Check if the data array has the expected number of elements
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
		if (studentCount < users.length) {
			users[studentCount] = user; // Add user to the array
			studentCount++; // Increment the count
		} else {
			System.err.println("User  array is full. Cannot add more users.");
		}
	}

	// Method to separate users into course lists and write to CSV files
	public void separateAndWriteUsersByCourse() {
		User[] compsciUsers = new User[studentCount]; // Array for COMPSCI users
		User[] statUsers = new User[studentCount]; // Array for STAT users
		User[] apmthUsers = new User[studentCount]; // Array for APMTH users

		int compsciCount = 0;
		int statCount = 0;
		int apmthCount = 0;

		// Separate users based on course prefixes
		for (int i = 0; i < studentCount; i++) {
			User user = users[i];
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
		writeToCsv("compsci_users.csv", sortUsers(compsciUsers, compsciCount), compsciCount);
		writeToCsv("stat_users.csv", sortUsers(statUsers, statCount), statCount);
		writeToCsv("apmth_users.csv", sortUsers(apmthUsers, apmthCount), apmthCount);
	}

	private void writeToCsv(String string, User[] sortUsers, int compsciCount) {
		// TODO Auto-generated method stub
		
	}

	// Method to sort users, handling null entries
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
	}