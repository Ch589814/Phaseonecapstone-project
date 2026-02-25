📖 Project Overview

This project is a console-based Java backend application designed to simulate an academic enrollment system. It demonstrates core Object-Oriented Programming principles, Java Collections, custom exception handling, file persistence, and Git/GitHub collaboration practices.

The system manages students, instructors, courses, and enrollments, enforcing real-world business rules without using a database.

🎯 Learning Objectives

Apply OOP principles: abstraction, inheritance, encapsulation, and polymorphism

Use Java Collections (List, Map, Set) to model many-to-many relationships

Implement custom checked exceptions for business logic validation

Persist data using File I/O (java.io)

Practice Git workflows: branching, pull requests, and conflict resolution

🧱 Project Structure src/ └── capstone/ ├── Person.java ├── Student.java ├── UndergraduateStudent.java ├── GraduateStudent.java ├── Instructor.java ├── Course.java ├── UniversityManager.java ├── FileManager.java ├── Main.java └── exceptions/ ├── CourseFullException.java └── StudentAlreadyEnrolledException.java 🧪 Lab Breakdown 🔹 Lab 1: Object-Oriented Design & Domain Modeling

Designed an academic hierarchy using abstract classes

Implemented Student and Instructor inheritance from Person

Modeled relationships using:

List for course rosters

Map<Course, Double> for student grades

Applied method overriding to calculate tuition differently for undergraduate and graduate students

🔹 Lab 2: Business Logic & Exception Handling

Created a UniversityManager as a central controller

Implemented enrollment logic with strict business rules

Developed custom checked exceptions:

CourseFullException

StudentAlreadyEnrolledException

Used Java Streams to:

Calculate average GPA per department

Identify top-performing students

🔹 Lab 3: Persistence & Final Integration

Implemented File I/O persistence using text/CSV files

Loaded saved data at application startup

Built a menu-driven console interface with options:

Register Student

Enroll in Course

View Student Record

Generate Dean’s List

Save and Exit

Practiced Git collaboration workflows using branches and pull requests

🛠 Technologies Used

Java (OOP, Collections, Streams)

Java File I/O (java.io)

Git & GitHub

Console-based UI

🚀 How to Run

Clone the repository

git clone

Open the project in your IDE (IntelliJ / Eclipse)

Run Main.java

Follow the console menu

🧠 Key Takeaways

Modeled real-world systems using pure Java backend logic

Handled errors safely using custom exceptions

Built persistence without databases

Practiced professional Git version control workflows

✍️ Author

NIYOMUFASHA Delphine Java Backend Development – Phase 1 Capstone
