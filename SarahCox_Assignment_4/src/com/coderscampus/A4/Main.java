package com.coderscampus.A4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String csvFilePath = "student-master-list.numbers"; // Ensure the path is correct

        try {
            File file = new File(csvFilePath);
            Scanner scanner = new Scanner(file);
            // Skip header if present
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(","); // Split by comma

                // Check if the data array has the expected number of elements
                if (data.length < 4) {
                    System.err.println("Invalid line: " + line);
                    continue; // Skip this line if it doesn't have enough data
                }

                // Process data as needed
                String studentId = data[0].trim();
                String studentName = data[1].trim();
                String course = data[2].trim();
                String grade = data[3].trim();

                System.out.println("Student ID: " + studentId + ", Name: " + studentName + 
                                   ", Course: " + course + ", Grade: " + grade);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
//        // Load users from the CSV file
//        userService.loadUsersFromCsv(); // Ensure this method is called to load users
//
//        // Separate users by course and write to CSV files
//        userService.separateAndWriteUsersByCourse();
//
//        // Print the contents of course CSV files
//        System.out.println("Contents of course1.csv:");
//        printCsvContents("src/course1.csv");
//        System.out.println("\nContents of course2.csv:");
//        printCsvContents("src/course2.csv");
//        System.out.println("\nContents of course3.csv:");
//        printCsvContents("src/course3.csv");
//    }
//
//    // Method to print the contents of a CSV file to the console
//    private static void printCsvContents(String filePath) {
//        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
//            String line;
//            // Read and print each line of the CSV file
//            while ((line = reader.readLine()) != null) {
//                System.out.println(line);
//            }
//        } catch (IOException e) {
//            System.err.println("Error reading file: " + filePath);
//            e.printStackTrace();
//        }
//    }
//}

//
//        // Step 1: Read and print course1.csv
//        String csvFilePath1 = "src/course1.csv";
//        System.out.println("course1.csv");
//        readAndPrintCsv(csvFilePath1);
//
//        // Step 2: Read and print course2.csv
//        String csvFilePath2 = "src/course2.csv";
//        System.out.println("\ncourse2.csv");
//        readAndPrintCsv(csvFilePath2);
//
//        // Step 3: Read and print course3.csv
//        String csvFilePath3 = "src/course3.csv";
//        System.out.println("\ncourse3.csv");
//        readAndPrintCsv(csvFilePath3);
//    }
//
//    // Method to read a CSV file and print its contents to the console
//    private static void readAndPrintCsv(String filePath) {
//        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
//            String line;
//            // Read and print each line of the CSV file
//            while ((line = reader.readLine()) != null) {
//                System.out.println(line);
//            }
//        } catch (IOException e) {
//            System.err.println("Error reading file: " + filePath);
//            e.printStackTrace();
//        }
//    }
//}