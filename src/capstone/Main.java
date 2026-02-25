package capstone;
import java.util.List;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        UniversityManager manager = new UniversityManager();
        FileManager.saveData(manager);
        Scanner scanner = new Scanner(System.in);
        UndergraduateStudent undergrad = new UndergraduateStudent("Alice", "alice@email.com", "U123", 3.7, "Computer Science");
        GraduateStudent grad = new GraduateStudent("Bob", "bob@email.com", "G456", 3.9, "Data Science");
        Instructor inst1 = new Instructor("Dr. Smith", "smith@email.com", "I789", "Computer Science");
        Instructor inst2 = new Instructor("Prof. Lee", "lee@email.com", "I101", "Data Science");
        Course c1 = new Course("CS101", "Intro to CS", 3, inst1, "Computer Science");
        Course c2 = new Course("DS201", "Data Science Fundamentals", 4, inst2, "Data Science");
        //test graduate and undergraduate
        System.out.println("Undergrad Tuition: RWF" + undergrad.calculateTuition());
        System.out.println("Grad Tuition: RWF" + grad.calculateTuition());
        //test universitymanager
        manager.addInstructor(inst1);
        manager.addInstructor(inst2);
        manager.addCourse(c1);
        manager.addCourse(c2);
        manager.addStudent(undergrad);
        manager.addStudent(grad);

        while (true) {
            System.out.println("\n******************** University Management System ***********************");
            System.out.println("1. Register Student");
            System.out.println("2. Register Instructor");
            System.out.println("3. Register Course");
            System.out.println("4. Enroll Student in Course");
            System.out.println("5. View All Students");
            System.out.println("6. View Top Students (GPA ≥ 3.5)");
            System.out.println("7. View Students by Department");
            System.out.println("8. View All Courses");
            System.out.println("9. View All Instructors");
            System.out.println("10. Save Data");
            System.out.println("11. Load Data");
            System.out.println("12. Save and Exit");
            System.out.println("12. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> registerStudent(manager, scanner);
                case 2 -> registerInstructor(manager, scanner);
                case 3 -> registerCourse(manager, scanner);
                case 4 -> enrollStudent(manager, scanner);
                case 5 -> manager.getStudents().forEach(Main::displayStudent);
                case 6 -> manager.getDeansList().forEach(Main::displayStudent);
                case 7 -> {
                    System.out.print("Enter department: ");
                    String dept = scanner.nextLine();
                    manager.getStudentsByDepartment(dept).forEach(Main::displayStudent);
                }
                case 8 -> manager.getCourses().forEach(Main::displayCourse);
                case 9 -> manager.getInstructors().forEach(Main::displayInstructor);
                case 10 -> {
                    System.out.println("Goodbye!");
                    return;
                }
                case 11 -> FileManager.saveData(manager);
                case 12 -> FileManager.loadData(manager);
                case 13 -> {
                    FileManager.saveData(manager);
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void registerStudent(UniversityManager manager, Scanner scanner) {
        System.out.print("Name: "); String name = scanner.nextLine();
        System.out.print("Email: "); String email = scanner.nextLine();
        System.out.print("ID: "); String id = scanner.nextLine();
        System.out.print("GPA: "); double gpa = scanner.nextDouble();
        scanner.nextLine(); // consume newline
        System.out.print("Department: "); String dept = scanner.nextLine();

        System.out.print("Type (1=Undergrad, 2=Graduate): ");
        int type = scanner.nextInt();
        scanner.nextLine();

        Student student;
        if (type == 1) {
            student = new UndergraduateStudent(name, email, id, gpa, dept);
        } else if (type == 2) {
            student = new GraduateStudent(name, email, id, gpa, dept);
        } else {
            System.out.println("Invalid type. Defaulting to Undergraduate.");
            student = new UndergraduateStudent(name, email, id, gpa, dept);
        }

        manager.addStudent(student);
        System.out.println(" Student registered: " + student.getName() + " (" + student.getClass().getSimpleName() + ")");
    }

    private static void registerInstructor(UniversityManager manager, Scanner scanner) {
        System.out.print("Name: "); String name = scanner.nextLine();
        System.out.print("Email: "); String email = scanner.nextLine();
        System.out.print("ID: "); String id = scanner.nextLine();
        System.out.print("Department: "); String dept = scanner.nextLine();

        Instructor instructor = new Instructor(name, email, id, dept);
        manager.addInstructor(instructor);
        System.out.println("Instructor registered: " + name);
    }

    private static void registerCourse(UniversityManager manager, Scanner scanner) {
        System.out.print("Code: "); String code = scanner.nextLine();
        System.out.print("Title: "); String title = scanner.nextLine();
        System.out.print("Credits: "); int credits = scanner.nextInt();
        scanner.nextLine(); // consume newline

        // Show available instructors
        List<Instructor> instructors = manager.getInstructors();
        if (instructors.isEmpty()) {
            System.out.println(" No instructors available. Register an instructor first.");
            return;
        }

        System.out.println("\nAvailable Instructors:");
        for (int i = 0; i < instructors.size(); i++) {
            Instructor inst = instructors.get(i);
            System.out.printf("%d. %s (ID: %s, Dept: %s)%n", i + 1, inst.getName(), inst.getInstructorID(), inst.getDepartment());
        }

        System.out.print("Select instructor (1-" + instructors.size() + "): ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice < 1 || choice > instructors.size()) {
            System.out.println(" Invalid selection.");
            return;
        }

        Instructor selected = instructors.get(choice - 1);
        System.out.print("Department: "); String dept = scanner.nextLine();

        Course course = new Course(code, title, credits, selected, dept);
        manager.addCourse(course);
        System.out.println(" Course registered: " + title);
    }

    private static void enrollStudent(UniversityManager manager, Scanner scanner) {
        System.out.print("Student ID: "); String sid = scanner.nextLine();
        System.out.print("Course Code: "); String cid = scanner.nextLine();

        try {
            manager.enrollStudentInCourse(sid, cid);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void displayStudent(Student student) {
        String type = student instanceof GraduateStudent ? "Graduate" : "Undergraduate";
        System.out.println("  - " + student.getName() + " (" + type + ", GPA: " + student.getGPA() + ", Dept: " + student.getDepartment() + ")");
    }

    private static void displayCourse(Course course) {
        System.out.println("  - " + course.getTitle() + " (" + course.getCourseCode() + ") - " + course.getCredits() + " credits");
        System.out.println("    Instructor: " + course.getInstructor().getName() + " (ID: " + course.getInstructor().getInstructorID() + ")");
        System.out.println("    Department: " + course.getDepartment());
        System.out.println("    Enrolled: " + course.getRoster().size());
    }

    private static void displayInstructor(Instructor instructor) {
        System.out.println("  - " + instructor.getName() + " (ID: " + instructor.getInstructorID() + ", Dept: " + instructor.getDepartment() + ")");
    }
}
