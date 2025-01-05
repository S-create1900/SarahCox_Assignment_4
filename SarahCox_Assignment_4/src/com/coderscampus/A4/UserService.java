package com.coderscampus.A4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    private List<User> users = new ArrayList<>();
    private static final String INPUT_FILE_PATH = "src/student-master-list (1).numbers"; // Path to the source file

    // Method to parse the master list using BufferedReader
    public void parseMasterList() {
        try (BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE_PATH))) {
            String line;
            // Skip the header
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] fields = line.split(","); // Split by comma
                String studentId = fields[0].trim(); // Trim to remove any leading/trailing spaces
                String studentName = fields[1].trim();
                String course = fields[2].trim();
                int grade = Integer.parseInt(fields[3].trim()); // Parse as int
                users.add(new User(studentId, studentName, course, grade)); // Add user to the list
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to separate users into course lists and write to CSV files
    public void separateAndWriteUsersByCourse() {
        List<User> compsciUsers = new ArrayList<>();
        List<User> statUsers = new ArrayList<>();
        List<User> apmthUsers = new ArrayList<>();

        // Separate users based on course prefixes
        for (User  user : users) {
            String course = user.getCourse();
            if (course.startsWith("COMPSCI")) {
                compsciUsers.add(user);
            } else if (course.startsWith("STAT")) {
                statUsers.add(user);
            } else if (course.startsWith("APMTH")) {
                apmthUsers.add(user);
            }
        }

        // Write to CSV files
        writeToCsv("src/course1.csv", compsciUsers);
        writeToCsv("src/course2.csv", apmthUsers);
        writeToCsv("src/course3.csv", statUsers);
    }

    // Method to write users to a CSV file
    private void writeToCsv(String filePath, List<User> users) {
        try (FileWriter writer = new FileWriter(filePath)) {
            // Write header
            writer.append("Student ID,Student Name,Course,Grade\n");
            // Write user data
            for (User  user : users) {
                writer.append(user.getStudentId()).append(",")
                      .append(user.getStudentName()).append(",")
                      .append(user.getCourse()).append(",")
                      .append(String.valueOf(user.getGrade())).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to get the list of users
    public List<User> getUsers() {
        return users;
    }
}