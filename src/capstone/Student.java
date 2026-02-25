package capstone;
import java.util.Map;
import java.util.HashMap;

public class Student extends Person {
    private String id;
    private double GPA;
    private String dept;
    private Map<Course, Double> courseGrades = new HashMap<>();

    public Student(String name, String email, String id, double GPA, String dept) {
        super(name, email);
        this.id = id;
        this.GPA = GPA;
        this.dept = dept;
    }

    // Getters & Setters
    public String getStudentID() { return id; }
    public void setStudentID(String studentID) { this.id = id; }

    public double getGPA() { return GPA; }
    public void setGPA(double GPA) { this.GPA = GPA; }

    public String getDepartment() { return dept; }
    public void setDepartment(String department) { this.dept = dept; }

    public Map<Course, Double> getCourseGrades() { return courseGrades; }

    @Override
    public void displayInfo() {
        System.out.println("Student: " + getName() + " ( student ID: " + id + ")");
        System.out.println("GPA: " + GPA + " | Dept: " + dept);
    }
}

