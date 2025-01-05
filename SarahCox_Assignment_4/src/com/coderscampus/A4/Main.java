// 	1. In order to sort an array, you'll need to figure out how to handle null entries in your array. If you're trying to use Arrays.sort(yourArray), and yourArray contains null values, the sort will crash. 
//	2. When writing to a file, you can use "\n" to write a new line to the file. Fore example: fileWriter.write("This is one line \n");
//  3. If you want to turn String input into an Integer, you can parse it like so: Integer myIntVal = Integer.parseInt(myStringVal);
package com.coderscampus.A4;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Create an instance of UserService
        UserService userService = new UserService();

        // Step 1: Parse the master list from the CSV file
        userService.parseMasterList();

        // Step 2: Separate users by course and write to CSV files
        userService.separateAndWriteUsersByCourse();

        // Step 3: Print a message indicating that the files have been created
        System.out.println("CSV files for each course have been created successfully.");
    }
}