package capstone;
import capstone.Instructor;
import capstone.Student;
import java.util.List;
import java.util.ArrayList;

public class Course {
    private String courseCode;
    private String title;
    private int credits;
    private Instructor instructor;
    private String dept;
    private List<Student> roster = new ArrayList<>();

    public Course(String courseCode, String title, int credits, Instructor instructor, String department) {
        this.courseCode = courseCode;
        this.title = title;
        this.credits = credits;
        this.instructor = instructor;
        this.dept = department;
    }

    // Getters & Setters
    public String getCourseCode() { return courseCode; }
    public void setCourseCode(String courseCode) { this.courseCode = courseCode; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public int getCredits() { return credits; }
    public void setCredits(int credits) { this.credits = credits; }

    public Instructor getInstructor() { return instructor; }
    public void setInstructor(Instructor instructor) { this.instructor = instructor; }

    public String getDepartment() { return dept; }
    public void setDepartment(String department) { this.dept = department; }

    public List<Student> getRoster() { return roster; }

    public void addStudent(Student student) {
        roster.add(student);
    }

    public void displayCourseInfo() {
        System.out.println("Course: " + title + " (" + courseCode + ") - " + credits + " credits");
        System.out.println("Instructor: " + instructor.getName());
        System.out.println("Department: " + dept);
        System.out.println("Enrolled Students: " + roster.size());
    }
}