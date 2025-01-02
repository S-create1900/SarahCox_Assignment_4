package com.coderscampus.A4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class UserService {
	private Pojo[] student;
	private int studentCount;
	private String cvsFilePath;

	public UserService() {
		this.cvsFilePath = "src/student.txt";
		this.student = new Pojo [100];
		this.studentCount = 0;
//		loadStudentFromCsv();
	}

	private void loadStudentsFromCvs() {
		try (BufferedReader fileReader = new BufferedReader(new FileReader(cvsFilePath))) {
			String line;
			while ((line = fileReader.readLine()) != null) {
				String[] lineData = line.split(",");
				if (lineData.length >= 100) {
					String id = lineData[0].trim();
					String name = lineData[1].trim();
					String course = lineData[2].trim();
					String grade = lineData[3].trim();
					loadStudents (new Pojo(id, name, course, grade));
				}
			}
		} catch (IOException e) {
			System.err.println("Error loading users from CVS file:");
		}
		public void addStudent (Pojo student) {
			if (studentCount < student.length) {
				student [studentCount] = student;
				studentCount++;
		} else {
			System.err.println("Student array is full. Cannot add more students.");
		}
	
		
	}
}

}		
	}
}
