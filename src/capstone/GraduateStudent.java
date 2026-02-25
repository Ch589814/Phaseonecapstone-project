package capstone;
public class GraduateStudent extends Student {
    private static final double PER_CREDIT_RATE = 100000.0;
    private static final double RESEARCH_FEE = 250000.0;

    public GraduateStudent(String name, String email, String studentID, double GPA, String department) {
        super(name, email, studentID, GPA, department);
    }

    public double calculateTuition() {
        // Assume 12 credits for example — you can make this dynamic later
        return (PER_CREDIT_RATE * 12) + RESEARCH_FEE;
    }
}
