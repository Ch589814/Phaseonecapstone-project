package capstone;
import capstone.Course;
import capstone.Instructor;
import capstone.Student;
import java.util.*;
import java.util.stream.*;

public class UniversityManager {
    private List<Student> students = new ArrayList<>();
    private List<Course> courses = new ArrayList<>();
    private List<Instructor> instructors = new ArrayList<>();
    private static final int MAX_ENROLLMENT = 30;

    // method to Add
    public void addStudent(Student student) {
        students.add(student);
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void addInstructor(Instructor instructor) {
        instructors.add(instructor);
    }

    // method to Find
    public Student findStudentById(String id) throws StudentNotFoundException {
        return students.stream()
                .filter(s -> s.getStudentID().equals(id))
                .findFirst()
                .orElseThrow(() -> new StudentNotFoundException("Student not found: " + id));
    }

    public Course findCourseByCode(String code) throws CourseNotFoundException {
        return courses.stream()
                .filter(c -> c.getCourseCode().equals(code))
                .findFirst()
                .orElseThrow(() -> new CourseNotFoundException("Course not found: " + code));
    }

    public Instructor findInstructorById(String id) throws Exception {
        return instructors.stream()
                .filter(i -> i.getInstructorID().equals(id))
                .findFirst()
                .orElseThrow(() -> new Exception("Instructor not found: " + id));
    }

    // method to Remove
    public boolean removeStudent(String id) {
        return students.removeIf(s -> s.getStudentID().equals(id));
    }

    public boolean removeCourse(String code) {
        return courses.removeIf(c -> c.getCourseCode().equals(code));
    }

    public boolean removeInstructor(String id) {
        return instructors.removeIf(i -> i.getInstructorID().equals(id));
    }

    //  Enrollment
    public void enrollStudentInCourse(String studentId, String courseCode)
            throws StudentNotFoundException, CourseNotFoundException, EnrollmentException {

        Student student = findStudentById(studentId);
        Course course = findCourseByCode(courseCode);

        //  Show course details before enrolling
        System.out.println("\n--- Course Details ---");
        System.out.println("Course: " + course.getTitle() + " (" + course.getCourseCode() + ")");
        System.out.println("Department: " + course.getDepartment());
        System.out.println("Instructor: " + course.getInstructor().getName() + " (ID: " + course.getInstructor().getInstructorID() + ")");
        System.out.println("Enrolled: " + course.getRoster().size() + "/" + MAX_ENROLLMENT);

        if (course.getRoster().size() >= MAX_ENROLLMENT) {
            throw new EnrollmentException("Course " + courseCode + " is full!");
        }

        course.addStudent(student);
        System.out.println("\n " + student.getName() + " enrolled in " + course.getTitle());
    }

    // Queries
    public double getAverageGPA() {
        return students.stream()
                .mapToDouble(Student::getGPA)
                .average()
                .orElse(0.0);
    }

    public List<Student> getTopStudents(double minGPA) {
        return students.stream()
                .filter(s -> s.getGPA() >= minGPA)
                .sorted(Comparator.comparingDouble(Student::getGPA).reversed())
                .collect(Collectors.toList());
    }

    public List<Student> getStudentsByDepartment(String department) {
        return students.stream()
                .filter(s -> s.getDepartment().equalsIgnoreCase(department))
                .collect(Collectors.toList());
    }
    public List<Student> getDeansList() {
        return students.stream()
                .filter(s -> s.getGPA() > 3.5)
                .sorted(Comparator.comparingDouble(Student::getGPA).reversed())
                .collect(Collectors.toList());
    }
    // getters
    public List<Student> getStudents() {
        return new ArrayList<>(students);
    }
    public List<Instructor> getInstructors() {
        return new ArrayList<>(instructors);
    }
    public List<Course> getCourses() {
        return new ArrayList<>(courses);
    }
}


