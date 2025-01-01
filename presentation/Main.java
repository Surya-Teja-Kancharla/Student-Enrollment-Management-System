package presentation;

import business.EnrollmentManager;

import java.util.Scanner;

public class Main 
{
    public static void main(String[] args) 
    {
        EnrollmentManager manager = new EnrollmentManager();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Student Enrollment Management System!");

        while(true) 
        {
            displayMenu();
            System.out.print("Enter your choice: ");
            String input = scanner.nextLine().trim();
            int choice;

            try 
            {
                choice = Integer.parseInt(input);
            } 
            catch(NumberFormatException e) 
            {
                System.out.println("Invalid input. Please enter a number corresponding to the menu options.");
                continue;
            }

            switch(choice) 
            {
                case 1:
                    addStudentFlow(manager, scanner);
                    break;
                case 2:
                    addCourseFlow(manager, scanner);
                    break;
                case 3:
                    enrollStudentFlow(manager, scanner);
                    break;
                case 4:
                    displayStudentsInCourseFlow(manager, scanner);
                    break;
                case 5:
                    displayCoursesOfStudentFlow(manager, scanner);
                    break;
                case 6:
                    manager.generateSummaryReport();
                    break;
                case 7:
                    System.out.println("Exiting the system. Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please select a valid option from the menu.");
            }
        }
    }

    // Display the main menu
    private static void displayMenu() 
    {
        System.out.println("\n----- Main Menu -----");
        System.out.println("1. Add Student");
        System.out.println("2. Add Course");
        System.out.println("3. Enroll Student in a Course");
        System.out.println("4. Display Students in a Course");
        System.out.println("5. Display Courses of a Student");
        System.out.println("6. Generate Summary Report");
        System.out.println("7. Exit");
    }

    // Handle adding a new student
    private static void addStudentFlow(EnrollmentManager manager, Scanner scanner) 
    {
        System.out.println("\n--- Add New Student ---");
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine().trim();

        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine().trim();

        System.out.print("Enter Student Email: ");
        String email = scanner.nextLine().trim();

        if(studentId.isEmpty() || name.isEmpty() || email.isEmpty()) 
        {
            System.out.println("Error: All fields are required.");
            return;
        }

        if(!isValidEmail(email)) 
        {
            System.out.println("Error: Invalid email format.");
            return;
        }

        manager.addStudent(studentId, name, email);
    }

    // Handle adding a new course
    private static void addCourseFlow(EnrollmentManager manager, Scanner scanner) 
    {
        System.out.println("\n--- Add New Course ---");
        System.out.print("Enter Course ID: ");
        String courseId = scanner.nextLine().trim();

        System.out.print("Enter Course Name: ");
        String name = scanner.nextLine().trim();

        System.out.print("Enter Course Capacity: ");
        String capacityInput = scanner.nextLine().trim();
        int capacity;

        try 
        {
            capacity = Integer.parseInt(capacityInput);
            if(capacity <= 0) 
            {
                System.out.println("Error: Capacity must be a positive integer.");
                return;
            }
        } 
        catch(NumberFormatException e) 
        {
            System.out.println("Error: Invalid capacity. Please enter a valid number.");
            return;
        }

        if(courseId.isEmpty() || name.isEmpty()) 
        {
            System.out.println("Error: Course ID and Name are required.");
            return;
        }

        manager.addCourse(courseId, name, capacity);
    }

    // Handle enrolling a student in a course
    private static void enrollStudentFlow(EnrollmentManager manager, Scanner scanner) 
    {
        System.out.println("\n--- Enroll Student in a Course ---");
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine().trim();

        System.out.print("Enter Course ID: ");
        String courseId = scanner.nextLine().trim();

        if(studentId.isEmpty() || courseId.isEmpty()) 
        {
            System.out.println("Error: Both Student ID and Course ID are required.");
            return;
        }

        manager.enroll(studentId, courseId);
    }

    // Handle displaying students in a course
    private static void displayStudentsInCourseFlow(EnrollmentManager manager, Scanner scanner) 
    {
        System.out.println("\n--- Display Students in a Course ---");
        System.out.print("Enter Course ID: ");
        String courseId = scanner.nextLine().trim();

        if(courseId.isEmpty()) 
        {
            System.out.println("Error: Course ID is required.");
            return;
        }

        manager.displayStudentsInCourse(courseId);
    }

    // Handle displaying courses of a student
    private static void displayCoursesOfStudentFlow(EnrollmentManager manager, Scanner scanner) 
    {
        System.out.println("\n--- Display Courses of a Student ---");
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine().trim();

        if(studentId.isEmpty()) 
        {
            System.out.println("Error: Student ID is required.");
            return;
        }

        manager.displayCoursesOfStudent(studentId);
    }

    // Validate email format (simple regex)
    private static boolean isValidEmail(String email) 
    {
        return email.matches("^[\\w.-]+@[\\w.-]+\\.\\w{2,}$");
    }
}
