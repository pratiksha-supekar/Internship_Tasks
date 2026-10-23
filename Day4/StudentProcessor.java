
import java.util.*;

// Custom exception to handle business logic errors
class InvalidInputException extends Exception {
    public InvalidInputException(String message) {
        super(message);
    }
}

// Student Model Class representing the records
class Student {
    private String id;
    private String name;
    private double marks;

    public Student(String id, String name, double marks) throws InvalidInputException {
        if (id == null || id.trim().isEmpty()) throw new InvalidInputException("ID cannot be empty.");
        if (name == null || name.trim().isEmpty()) throw new InvalidInputException("Name cannot be empty.");
        if (marks < 0 || marks > 100) throw new InvalidInputException("Marks must be between 0 and 100.");
        
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public double getMarks() { return marks; }

    @Override
    public String toString() {
        return String.format("ID: %-5s | Name: %-15s | Marks: %.2f", id, name, marks);
    }
}

// Main Application handling Processing, Searching, and Sorting
public class StudentProcessor {
    // HashMap for fast O(1) searches by ID
    private static Map<String, Student> studentMap = new HashMap<>();
    // ArrayList for order preservation and sorting operations
    private static List<Student> studentList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Day 4: Student Data Processing System ===");

        // Task 1: Store and process records (Pre-loading sample data securely)
        try {
            addStudent(new Student("S101", "Rahul Sharma", 85.5));
            addStudent(new Student("S102", "Priya Patel", 92.0));
            addStudent(new Student("S103", "Amit Verma", 64.0));
        } catch (InvalidInputException e) {
            System.out.println("Error initializing data: " + e.getMessage());
        }

        while (true) {
            System.out.println("\n--- Menu Options ---");
            System.out.println("1. Add New Student Record");
            System.out.println("2. Display All Records");
            System.out.println("3. Search Record by ID");
            System.out.println("4. Search Record by Name");
            System.out.println("5. Sort Records by Name");
            System.out.println("6. Sort Records by Marks");
            System.out.println("7. Exit");
            System.print("Choose an option (1-7): ");

            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                
                switch (choice) {
                    case 1:
                        // Task 4: Handle invalid input using exception handling framework
                        System.out.print("Enter Student ID: ");
                        String id = scanner.nextLine().trim();
                        System.out.print("Enter Student Name: ");
                        String name = scanner.nextLine().trim();
                        System.out.print("Enter Student Marks: ");
                        double marks = Double.parseDouble(scanner.nextLine().trim());

                        Student newStudent = new Student(id, name, marks);
                        addStudent(newStudent);
                        System.out.println("Record added successfully!");
                        break;

                    case 2:
                        displayRecords(studentList);
                        break;

                    case 3:
                        // Task 2: Search for a record by ID using HashMap
                        System.out.print("Enter ID to search: ");
                        String searchId = scanner.nextLine().trim();
                        Student foundById = studentMap.get(searchId);
                        if (foundById != null) {
                            System.out.println("Record Found: " + foundById);
                        } else {
                            System.out.println("No student found with ID: " + searchId);
                        }
                        break;

                    case 4:
                        // Task 2: Search for a record by Name using Java Streams API
                        System.out.print("Enter Name to search: ");
                        String searchName = scanner.nextLine().trim();
                        System.out.println("Search Results:");
                        studentList.stream()
                                .filter(s -> s.getName().equalsIgnoreCase(searchName))
                                .forEach(System.out.println);
                        break;

                    case 5:
                        // Task 3: Sort records by Name
                        studentList.sort(Comparator.comparing(Student::getName));
                        System.out.println("Sorted by Name successfully.");
                        displayRecords(studentList);
                        break;

                    case 6:
                        // Task 3: Sort records by Marks (Descending order)
                        studentList.sort(Comparator.comparingDouble(Student::getMarks).reversed());
                        System.out.println("Sorted by Marks (Highest First) successfully.");
                        displayRecords(studentList);
                        break;

                    case 7:
                        System.out.println("Exiting application. Goodbye!");
                        scanner.close();
                        System.exit(0);

                    default:
                        System.out.println("Invalid option selected. Please choose between 1 and 7.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Input Error: Please enter a valid number for menu choices and numeric values.");
            } catch (InvalidInputException e) {
                System.out.println("Validation Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }
    }

    private static void addStudent(Student student) throws InvalidInputException {
        if (studentMap.containsKey(student.getId())) {
            throw new InvalidInputException("Student with ID " + student.getId() + " already exists.");
        }
        studentMap.put(student.getId(), student);
        studentList.add(student);
    }

    private static void displayRecords(List<Student> list) {
        if (list.isEmpty()) {
            System.out.println("No student records found.");
            return;
        }
        System.out.println("\n--- Current Database Records ---");
        // Using Streams to output collection elements cleanly
        list.forEach(System.out::println);
    }
}
