package capstone;
public class Main {
    public static void main(String[] args){
        UndergraduateStudent undergrad = new UndergraduateStudent("Alice", "alice@email.com", "U123", 3.7, "Computer Science");
        GraduateStudent grad = new GraduateStudent("Bob", "bob@email.com", "G456", 3.9, "Data Science");
        //test graduate and undergraduate
        System.out.println("Undergrad Tuition: RWF" + undergrad.calculateTuition());
        System.out.println("Grad Tuition: RWF" + grad.calculateTuition()); }
}
