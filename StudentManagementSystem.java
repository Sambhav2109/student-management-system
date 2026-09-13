import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentManagementSystem {
    private static final String FILE_NAME = "students.txt";
    private static final List<Student> students = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        loadRecords();

        boolean running = true;
        while (running) {
            displayMenu();
            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> viewStudents();
                case 3 -> searchStudent();
                case 4 -> saveRecords();
                case 5 -> {
                    saveRecords();
                    running = false;
                    System.out.println("Thank you for using Student Management System!");
                }
                default -> System.out.println("Invalid choice. Please select 1-5.");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
        System.out.println("1. Add Student");
        System.out.println("2. View Students");
        System.out.println("3. Search Student");
        System.out.println("4. Save Records");
        System.out.println("5. Exit");
        System.out.println("=====================================");
    }

    private static void addStudent() {
        int rollNumber = readInt("Enter roll number: ");

        if (findStudent(rollNumber) != null) {
            System.out.println("A student with this roll number already exists.");
            return;
        }

        System.out.print("Enter student name: ");
        String name = scanner.nextLine().trim();
        while (name.isEmpty()) {
            System.out.print("Name cannot be empty. Enter student name: ");
            name = scanner.nextLine().trim();
        }

        double javaMarks = readMarks("Enter Java marks: ");
        double dsaMarks = readMarks("Enter DSA marks: ");
        double mathsMarks = readMarks("Enter Maths marks: ");

        students.add(new Student(rollNumber, name, javaMarks, dsaMarks, mathsMarks));
        System.out.println("Student added successfully.");
    }

    private static void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No student records found.");
            return;
        }

        System.out.println("\n----- STUDENT RECORDS -----");
        for (Student student : students) {
            System.out.println(student);
        }
    }

    private static void searchStudent() {
        int rollNumber = readInt("Enter roll number to search: ");
        Student student = findStudent(rollNumber);

        if (student == null) {
            System.out.println("Student not found.");
        } else {
            System.out.println("\nStudent found:");
            System.out.println(student);
        }
    }

    private static Student findStudent(int rollNumber) {
        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) {
                return student;
            }
        }
        return null;
    }

    private static int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid whole number.");
            }
        }
    }

    private static double readMarks(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                double marks = Double.parseDouble(scanner.nextLine().trim());
                if (marks >= 0 && marks <= 100) {
                    return marks;
                }
                System.out.println("Marks must be between 0 and 100.");
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private static void saveRecords() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Student student : students) {
                writer.println(student.toFileFormat());
            }
            System.out.println("Records saved successfully to " + FILE_NAME + ".");
        } catch (IOException e) {
            System.out.println("Could not save records: " + e.getMessage());
        }
    }

    private static void loadRecords() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 5);
                if (parts.length == 5) {
                    try {
                        int rollNumber = Integer.parseInt(parts[0].trim());
                        String name = parts[1].trim();
                        double javaMarks = Double.parseDouble(parts[2].trim());
                        double dsaMarks = Double.parseDouble(parts[3].trim());
                        double mathsMarks = Double.parseDouble(parts[4].trim());
                        students.add(new Student(rollNumber, name, javaMarks, dsaMarks, mathsMarks));
                    } catch (NumberFormatException ignored) {
                        // Skip malformed records instead of stopping the application.
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Could not load records: " + e.getMessage());
        }
    }
}
