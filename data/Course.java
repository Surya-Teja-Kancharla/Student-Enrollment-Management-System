package data;

import java.util.ArrayList;
import java.util.List;

public class Course extends Entity 
{
    private int capacity;
    private List<Student> enrolledStudents;

    public Course(String courseId, String name, int capacity) 
    {
        super(courseId, name); // Inherited from Entity
        this.capacity = capacity;
        this.enrolledStudents = new ArrayList<>();
    }

    // Getters
    public int getCapacity() 
    {
        return capacity;
    }

    public List<Student> getEnrolledStudents() 
    {
        return enrolledStudents;
    }

    // Setters
    public void setName(String name) 
    {
        this.name = name; // Update inherited name
    }

    public void setCapacity(int capacity) 
    {
        this.capacity = capacity; // Update course capacity
    }

    // Enroll a student if capacity allows
    public boolean enrollStudent(Student student) 
    {
        if (enrolledStudents.size() < capacity && !enrolledStudents.contains(student)) {
            enrolledStudents.add(student);
            return true;
        }
        return false;
    }

    // Remove student from course
    public void removeStudent(Student student) 
    {
        enrolledStudents.remove(student);
    }
}
