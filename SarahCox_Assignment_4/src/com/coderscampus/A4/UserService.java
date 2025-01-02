package com.coderscampus.A4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class UserService {
    private List<User> users = new ArrayList<>();
    private String csvFilePath;
    
    public UserService() {
    	this.csvFilePath = "src/student-master-list (1).numbers";
    }
    
 
    // Method to parse the master list using BufferedReader
    public void parseMasterList(String csvfilePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(csvfilePath))) {
            String line;
            br.readLine();// Skip the header

            while ((line = br.readLine()) != null) {
                String[] fields = line.split(","); 
                String studentId = fields[0];
                String studentName = fields[1];
                String course = fields[2];
                int grade = Integer.parseInt(fields[3]); 
                users.add(new User(studentId, studentName, course, grade)); 
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    
    }

    // Method to separate users into course files
    public void separateUsersByCourse() {
        User[] course1Users = new User[users.size()];
        User[] course2Users = new User[users.size()];
        User[] course3Users = new User[users.size()];

        int course1Count = 0, course2Count = 0, course3Count = 0;

        // Separate users based on course
        for (User  user : users) {
            switch (user.getCourse()) {
                case "Course1":
                    course1Users[course1Count++] = user;
                    break;
                case "Course2":
                    course2Users[course2Count++] = user;
                    break;
                case "Course3":
                    course3Users[course3Count++] = user;
                    break;
            }
        }

        // Write to CSV files
        writeToCsv("course1.csv", Arrays.copyOf(course1Users, course1Count));
        writeToCsv("course2.csv", Arrays.copyOf(course2Users, course2Count));
        writeToCsv("course3.csv", Arrays.copyOf(course3Users, course3Count));
    }

    // Method to write users to a CSV file
    private void writeToCsv(String filePath, User[] users) {
        try (FileWriter writer = new FileWriter(filePath)) {
            // Write header
            writer.append("Student ID,Student Name,Course,Grade\n");
            // Write user data
            for (User  user : users) {
                writer.append(user.getStudentID()).append(",")
                      .append(user.getStudentName()).append(",")
                      .append(user.getCourse()).append(",")
                      .append(String.valueOf(user.getGrade())).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to display users sorted by grade
    public void displayUsersByCourse(String course) {
        User[] courseUsers = users.stream()
                .filter(user -> user.getCourse().equals(course))
                .sorted(Comparator.comparingInt(User::getGrade).reversed())
                .toArray(User[]::new);

        System.out.println("Students in " + course + ":");
        for (User  user : courseUsers) {
            System.out.println(user.getStudentID() + ", " + user.getStudentName() + ", " + user.getGrade());
        }
        System.out.println();
    
    }
}