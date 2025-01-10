package com.coderscampus.A4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
	public static void main(String[] args) {

		// Create an instance of UserService
		UserService userService = new UserService();

		// Separate users by course and write to CSV files
		userService.separateAndWriteUsersByCourse();

		// Read and print additional course CSV files
		readAndPrintCsv("course1.csv");
		readAndPrintCsv("course2.csv");
		readAndPrintCsv("course3.csv");
	}

	// Method to read and print a CSV file and its contents to the console
	private static void readAndPrintCsv(String filePath) {
		System.out.println("\n" + filePath);
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			String line;
			// Read and print each line of the CSV file
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
		} catch (IOException e) {
			System.err.println("Error reading file: " + filePath);
			e.printStackTrace();
		}
	}
}