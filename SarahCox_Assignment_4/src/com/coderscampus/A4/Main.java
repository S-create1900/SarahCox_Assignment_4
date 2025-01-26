package com.coderscampus.A4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // Create an instance of StudentService to read and process the CSV
        new StudentService();

        // Print the contents of the CSV files to the console
        printCsvFile("course1.csv");
        System.out.println();
        printCsvFile("course2.csv");
        System.out.println();
        printCsvFile("course3.csv");

    }

    // Method to read and print a CSV file and its contents to the console
    private static void printCsvFile(String csvFile) {  
        System.out.println(csvFile);
        System.out.println();
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            String line;
            // Read and print each line of the CSV file
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
//            System.err.println("Error reading file: " + filePath);
            e.printStackTrace();
        }
    }
}