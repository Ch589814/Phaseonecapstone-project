package capstone;
public class Instructor extends Person {
    private String id;
    private String dept;

    public Instructor(String name, String email, String instructorID, String department) {
        super(name, email);
        this.id =instructorID;
        this.dept = department;
    }

    // Getters & Setters
    public String getInstructorID() { return id; }
    public void setInstructorID(String instructorID) { this.id = instructorID; }

    public String getDepartment() { return dept; }
    public void setDepartment(String department) { this.dept = department; }

    @Override
    public void displayInfo() {
        System.out.println("Instructor: " + getName() + " ( instructor ID: " + id + ")");
        System.out.println("Dept: " + dept);
    }
}
