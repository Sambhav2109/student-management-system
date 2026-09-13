# Student Management System

A beginner-friendly Java console application for managing student records, marks, grades, and academic performance.

## Features

- Add student records
- Enter marks for Java, DSA, and Maths
- Calculate average marks automatically
- Assign grades based on performance
- Search students by roll number
- View all student records
- Save records to a local text file
- Load saved records when the program starts
- Input validation and basic exception handling

## Concepts Demonstrated

- Java classes and objects
- Encapsulation
- ArrayList collections
- Methods and control flow
- File handling
- Exception handling
- Basic object-oriented programming (OOP)

## Project Structure

```text
StudentManagementSystem/
├── Student.java
├── StudentManagementSystem.java
├── students.txt          # Created automatically when records are saved
└── README.md
```

## How to Run

Make sure Java is installed, then run:

```bash
javac Student.java StudentManagementSystem.java
java StudentManagementSystem
```

## Sample Menu

```text
===== STUDENT MANAGEMENT SYSTEM =====
1. Add Student
2. View Students
3. Search Student
4. Save Records
5. Exit
=====================================
```

## Grade Scale

| Average | Grade |
|---:|:---:|
| 90-100 | A+ |
| 80-89 | A |
| 70-79 | B |
| 60-69 | C |
| 50-59 | D |
| Below 50 | F |

## Technologies

- Java
- Java Collections Framework
- Java I/O
