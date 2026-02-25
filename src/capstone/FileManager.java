package capstone;
import java.io.*;
import java.util.*;

public class FileManager {
    private static final String STUDENT_FILE = "students.txt";
    private static final String COURSE_FILE = "courses.txt";
    private static final String INSTRUCTOR_FILE = "instructors.txt";

    // SAVE ALL
    public static void saveData(UniversityManager manager) {
        saveStudents(manager.getStudents());
        saveCourses(manager.getCourses());
        saveInstructors(manager.getInstructors());
        System.out.println("Data saved successfully!");
    }

    //  LOAD ALL
    public static void loadData(UniversityManager manager) {
        loadInstructors(manager); // IMPORTANT: load instructors first
        loadStudents(manager);
        loadCourses(manager);
        System.out.println("Data loaded successfully!");
    }

    //  STUDENTS
    private static void saveStudents(List<Student> students) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(STUDENT_FILE))) {
            for (Student s : students) {
                String type = s instanceof GraduateStudent ? "Graduate" : "Undergraduate";
                writer.println(type + "|" + s.getName() + "|" + s.getEmail() + "|" +
                        s.getStudentID() + "|" + s.getGPA() + "|" + s.getDepartment());
            }
        } catch (IOException e) {
            System.out.println("Error saving students: " + e.getMessage());
        }
    }

    private static void loadStudents(UniversityManager manager) {
        try (BufferedReader reader = new BufferedReader(new FileReader(STUDENT_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length < 6) continue;

                String type = parts[0];
                String name = parts[1];
                String email = parts[2];
                String id = parts[3];
                double gpa = Double.parseDouble(parts[4]);
                String dept = parts[5];

                Student student = type.equals("Graduate")
                        ? new GraduateStudent(name, email, id, gpa, dept)
                        : new UndergraduateStudent(name, email, id, gpa, dept);

                manager.addStudent(student);
            }
        } catch (IOException e) {
            System.out.println("Error loading students: " + e.getMessage());
        }
    }

    //  COURSES
    private static void saveCourses(List<Course> courses) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(COURSE_FILE))) {
            for (Course c : courses) {
                writer.println(c.getCourseCode() + "|" + c.getTitle() + "|" +
                        c.getCredits() + "|" +
                        c.getInstructor().getInstructorID() + "|" +
                        c.getDepartment());
            }
        } catch (IOException e) {
            System.out.println("Error saving courses: " + e.getMessage());
        }
    }

    private static void loadCourses(UniversityManager manager) {
        try (BufferedReader reader = new BufferedReader(new FileReader(COURSE_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length < 5) continue;

                String code = parts[0];
                String title = parts[1];
                int credits = Integer.parseInt(parts[2]);
                String instId = parts[3];
                String dept = parts[4];

                try {
                    Instructor instructor = manager.findInstructorById(instId);
                    manager.addCourse(new Course(code, title, credits, instructor, dept));
                } catch (Exception e) {
                    System.out.println("Instructor not found for course: " + code);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading courses: " + e.getMessage());
        }
    }

    //  INSTRUCTORS
    private static void saveInstructors(List<Instructor> instructors) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(INSTRUCTOR_FILE))) {
            for (Instructor i : instructors) {
                writer.println(i.getName() + "|" + i.getEmail() + "|" +
                        i.getInstructorID() + "|" + i.getDepartment());
            }
        } catch (IOException e) {
            System.out.println("Error saving instructors: " + e.getMessage());
        }
    }

    private static void loadInstructors(UniversityManager manager) {
        try (BufferedReader reader = new BufferedReader(new FileReader(INSTRUCTOR_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length < 4) continue;

                manager.addInstructor(
                        new Instructor(parts[0], parts[1], parts[2], parts[3])
                );
            }
        } catch (IOException e) {
            System.out.println("Error loading instructors: " + e.getMessage());
        }
    }
}