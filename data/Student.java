package data;

import java.util.ArrayList;
import java.util.List;

public class Student extends Person 
{
    private String studentId;
    private List<Course> courses;

    public Student(String studentId, String name, String email) 
    {
        super(name, email); // Inherited from Person
        this.studentId = studentId;
        this.courses = new ArrayList<>();
    }

    // Getters
    public String getStudentId() 
    {
        return studentId;
    }

    public List<Course> getCourses() 
    {
        return courses;
    }

    // Setters
    public void setName(String name) 
    {
        this.name = name; // Update inherited name
    }

    public void setEmail(String email) 
    {
        this.email = email; // Update inherited email
    }

    // Enroll in a course
    public void enroll(Course course) 
    {
        if (!courses.contains(course)) 
        {
            courses.add(course);
        }
    }

    // Update student details
    public void updateDetails(String name, String email) 
    {
        setName(name);
        setEmail(email);
    }

    // Unenroll from a course
    public void unenroll(Course course) 
    {
        courses.remove(course);
    }

    // Check if the student is enrolled in a course
    public boolean isEnrolled(Course course) 
    {
        return courses.contains(course);
    }

    // Display the student's enrolled courses
    public void displayEnrolledCourses() 
    {
        if (courses.isEmpty()) 
        {
            System.out.println("No courses enrolled for student: " + name);
        } 
        else 
        {
            System.out.println("Courses enrolled by " + name + ":");
            for (Course course : courses) 
            {
                System.out.println("- " + course.getName() + " (ID: " + course.getId() + ")");
            }
        }
    }

    // Generate a summary of student's details
    public String generateSummary() 
    {
        StringBuilder summary = new StringBuilder();
        summary.append("Student ID: ").append(studentId).append("\n");
        summary.append("Name: ").append(name).append("\n");
        summary.append("Email: ").append(email).append("\n");
        summary.append("Enrolled Courses: \n");

        if (courses.isEmpty()) 
        {
            summary.append("None");
        } 
        else 
        {
            for (Course course : courses) 
            {
                summary.append("- ").append(course.getName()).append(" (ID: ").append(course.getId()).append(")\n");
            }
        }

        return summary.toString();
    }

    // Remove a course from the student's list
    public void removeCourse(Course oldCourse) 
    {
        courses.remove(oldCourse);
    }
}
