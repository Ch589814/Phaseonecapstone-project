package capstone;
public class UndergraduateStudent extends Student {
    private static final double FLAT_TUITION = 500000.0;

    public UndergraduateStudent(String name, String email, String id, double GPA, String dept) {
        super(name, email, id, GPA, dept);
    }

    public double calculateTuition() {
        return FLAT_TUITION;
    }
}
