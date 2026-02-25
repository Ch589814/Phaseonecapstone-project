package capstone;
public  abstract class Person {
    private String name;
        private String email;

        public Person(String name, String email) {
            this.name = name;
            this.email = email;
        }

        // Getters & Setters
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }

        // Abstract method to be implemented by subclasses
        public abstract void displayInfo();
    }


