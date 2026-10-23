
class Student {
    private int id;
    private String name;
    private String course;

    // Constructor
    Student(int id, String name, String course) {
        this.id = id;
        this.name = name;
        this.course = course;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCourse() {
        return course;
    }

    // Method
    public void displayStudent() {
        System.out.println("Student ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Course: " + course);
    }
}

class Employee {
    private int id;
    private String name;
    private double salary;

    // Constructor
    Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public void displayEmployee() {
        System.out.println("Employee ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Salary: " + salary);
    }
}

public class OOPPractice {
    public static void main(String[] args) {
        Student s1 = new Student(1, "Pratiksha", "Java Full Stack");
        Employee e1 = new Employee(101, "Rahul", 25000);

        System.out.println("=== Student Details ===");
        s1.displayStudent();

        System.out.println("\n=== Employee Details ===");
        e1.displayEmployee();
    }
}

