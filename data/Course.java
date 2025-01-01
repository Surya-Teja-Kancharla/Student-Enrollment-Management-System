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

    // Enroll a student if capacity allows
    public boolean enrollStudent(Student student) 
    {
        if (enrolledStudents.size() < capacity && !enrolledStudents.contains(student)) {
            enrolledStudents.add(student);
            return true;
        }
        return false;
    }
}
