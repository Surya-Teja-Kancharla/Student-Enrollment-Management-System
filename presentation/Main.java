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
                    addStudent(manager, scanner);
                    break;
                case 2:
                    addCourse(manager, scanner);
                    break;
                case 3:
                    enrollStudent(manager, scanner);
                    break;
                case 4:
                    displayStudentsInCourse(manager, scanner);
                    break;
                case 5:
                    displayCoursesOfStudent(manager, scanner);
                    break;
                case 6:
                    updateStudentDetails(manager, scanner);
                    break;
                case 7:
                    updateCourseDetails(manager, scanner);
                    break;
                case 8:
                    updateCourseEnrollment(manager, scanner);
                    break;
                case 9:
                    deleteStudentDetails(manager, scanner);
                    break;
                case 10:
                    deleteCourseDetails(manager, scanner);
                    break;
                case 11:
                    deleteCourseEnrollment(manager, scanner);
                    break;
                case 12:
                    manager.generateSummaryReport();
                    break;
                case 13:
                    System.out.println("Exiting the system. Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please select a valid option from the menu.");
            }
        }
    }

    private static void displayMenu() 
    {
        System.out.println("\n----- Main Menu -----");
        System.out.println("1. Add Student");
        System.out.println("2. Add Course");
        System.out.println("3. Enroll Student in a Course");
        System.out.println("4. Display Students in a Course");
        System.out.println("5. Display Courses of a Student");
        System.out.println("6. Update Details of a Student");
        System.out.println("7. Update Details of a Course");
        System.out.println("8. Update Course Enrollment");
        System.out.println("9. Delete Student Details");
        System.out.println("10. Delete Course Details");
        System.out.println("11. Delete Course Enrollment");
        System.out.println("12. Generate Summary Report");
        System.out.println("13. Exit");
    }

    // Handle adding a new student
    private static void addStudent(EnrollmentManager manager, Scanner scanner) 
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
    private static void addCourse(EnrollmentManager manager, Scanner scanner) 
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
    private static void enrollStudent(EnrollmentManager manager, Scanner scanner) 
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
    private static void displayStudentsInCourse(EnrollmentManager manager, Scanner scanner) 
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
    private static void displayCoursesOfStudent(EnrollmentManager manager, Scanner scanner) 
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

    private static void updateStudentDetails(EnrollmentManager manager, Scanner scanner) {
        System.out.println("\n--- Update Student Details ---");
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine().trim();
        System.out.print("Enter New Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter New Email: ");
        String email = scanner.nextLine().trim();

        if (studentId.isEmpty() || name.isEmpty() || email.isEmpty()) {
            System.out.println("Error: All fields are required.");
            return;
        }

        manager.updateStudentDetails(studentId, name, email);
    }

    // Handle updating course details
    private static void updateCourseDetails(EnrollmentManager manager, Scanner scanner) 
    {
        System.out.println("\n--- Update Course Details ---");
        System.out.print("Enter Course ID: ");
        String courseId = scanner.nextLine().trim();
        System.out.print("Enter New Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter New Capacity: ");
        int capacity = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        if(courseId.isEmpty() || name.isEmpty() || capacity <= 0) {
            System.out.println("Error: Invalid input. All fields are required and capacity must be positive.");
            return;
        }

        manager.updateCourseDetails(courseId, name, capacity);
    }

    // Handle updating course details
    private static void updateCourseEnrollment(EnrollmentManager manager, Scanner scanner) 
    {
        System.out.println("\n--- Update Course Enrollment ---");
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine().trim();
        System.out.print("Enter Old Course ID: ");
        String oldCourseId = scanner.nextLine().trim();
        System.out.print("Enter New Course ID: ");
        String newCourseId = scanner.nextLine().trim();

        if(studentId.isEmpty() || oldCourseId.isEmpty() || newCourseId.isEmpty()) 
        {
            System.out.println("Error: All fields are required.");
            return;
        }

        manager.updateCourseEnrollment(studentId, oldCourseId, newCourseId);
    }

    // Handle deleting course details
    private static void deleteStudentDetails(EnrollmentManager manager, Scanner scanner) 
    {
        System.out.println("\n--- Delete Student Details ---");
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine().trim();

        if(studentId.isEmpty()) 
        {
            System.out.println("Error: Student ID is required.");
            return;
        }

        manager.deleteStudentDetails(studentId);
    }

    // Handle deleting course details
    private static void deleteCourseDetails(EnrollmentManager manager, Scanner scanner) 
    {
        System.out.println("\n--- Delete Course Details ---");
        System.out.print("Enter Course ID: ");
        String courseId = scanner.nextLine().trim();

        if(courseId.isEmpty()) 
        {
            System.out.println("Error: Course ID is required.");
            return;
        }

        manager.deleteCourseDetails(courseId);
    }

    // Handle deleting course enrollment
    private static void deleteCourseEnrollment(EnrollmentManager manager, Scanner scanner) {
        System.out.println("\n--- Delete Course Enrollment ---");
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine().trim();
        System.out.print("Enter Course ID: ");
        String courseId = scanner.nextLine().trim();

        if (studentId.isEmpty() || courseId.isEmpty()) {
            System.out.println("Error: Both Student ID and Course ID are required.");
            return;
        }

        manager.deleteCourseEnrollment(studentId, courseId);
    }

    // Validate email format (for college mail ID)
    private static boolean isValidEmail(String email) 
    {
        return email.matches("^[\\w.-]+@anits\\.edu\\.in$");
    }

}