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

    // Enroll in a course
    public void enroll(Course course) 
    {
        if(!courses.contains(course)) 
        {
            courses.add(course);
        }
    }
}
