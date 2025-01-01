package business;

import data.Course;
import data.DataManager;
import data.Student;

import java.util.List;
import java.util.Map;

public class EnrollmentManager 
{
    private DataManager dataManager;
    private Map<String, Student> students;
    private Map<String, Course> courses;

    public EnrollmentManager() 
    {
        this.dataManager = new DataManager();
        this.students = dataManager.loadStudents();
        this.courses = dataManager.loadCourses();
        loadEnrollments();
    }

    // Load enrollments and associate students with courses
    private void loadEnrollments() 
    {
        List<String[]> enrollments = dataManager.loadEnrollments();
        for(String[] enrollment : enrollments) 
        {
            String studentId = enrollment[0];
            String courseId = enrollment[1];
            Student student = students.get(studentId);
            Course course = courses.get(courseId);
            if(student != null && course != null) 
            {
                boolean enrolled = course.enrollStudent(student);
                if(enrolled) 
                {
                    student.enroll(course);
                }
            }
        }
    }

    // Add a new student
    public void addStudent(String studentId, String name, String email) 
    {
        if(!students.containsKey(studentId)) 
        {
            Student student = new Student(studentId, name, email);
            students.put(studentId, student);
            dataManager.saveStudent(student);
            System.out.println("Student added successfully.");
        } 
        else 
        {
            System.out.println("Error: Student ID already exists.");
        }
    }

    // Add a new course
    public void addCourse(String courseId, String name, int capacity) 
    {
        if(!courses.containsKey(courseId)) 
        {
            Course course = new Course(courseId, name, capacity);
            courses.put(courseId, course);
            dataManager.saveCourse(course);
            System.out.println("Course added successfully.");
        } 
        else 
        {
            System.out.println("Error: Course ID already exists.");
        }
    }

    // Enroll a student in a course
    public void enroll(String studentId, String courseId) 
    {
        Student student = students.get(studentId);
        Course course = courses.get(courseId);

        if(student == null) 
        {
            System.out.println("Error: Invalid Student ID.");
            return;
        }

        if(course == null) 
        {
            System.out.println("Error: Invalid Course ID.");
            return;
        }

        if(student.getCourses().contains(course)) 
        {
            System.out.println("Error: Student is already enrolled in this course.");
            return;
        }

        if(course.enrollStudent(student)) 
        {
            student.enroll(course);
            dataManager.saveEnrollment(studentId, courseId);
            System.out.println("Enrollment successful.");
        } 
        else 
        {
            System.out.println("Error: Course capacity full or student already enrolled.");
        }
    }

    // Display all students enrolled in a specific course
    public void displayStudentsInCourse(String courseId) 
    {
        Course course = courses.get(courseId);
        if(course != null) 
        {
            List<Student> enrolled = course.getEnrolledStudents();
            if(enrolled.isEmpty()) 
            {
                System.out.println("No students are enrolled in " + course.getName() + ".");
            } 
            else 
            {
                System.out.println("Students enrolled in " + course.getName() + ":");
                for (Student student : enrolled) 
                {
                    System.out.println("- " + student.getName() + " (ID: " + student.getStudentId() + ")");
                }
            }
        } 
        else 
        {
            System.out.println("Error: Invalid Course ID.");
        }
    }

    // Display all courses a student is enrolled in
    public void displayCoursesOfStudent(String studentId) 
    {
        Student student = students.get(studentId);
        if (student != null) 
        {
            List<Course> enrolledCourses = student.getCourses();
            if(enrolledCourses.isEmpty()) 
            {
                System.out.println("Student " + student.getName() + " is not enrolled in any courses.");
            } 
            else 
            {
                System.out.println("Courses enrolled by " + student.getName() + ":");
                for(Course course : enrolledCourses) 
                {
                    System.out.println("- " + course.getName() + " (ID: " + course.getId() + ")");
                }
            }
        } 
        else 
        {
            System.out.println("Error: Invalid Student ID.");
        }
    }

    // Generate a summary report of all courses and their enrollment status
    public void generateSummaryReport() 
    {
        System.out.println("----- Course Enrollment Summary -----");
        for(Course course : courses.values()) 
        {
            System.out.println("Course: " + course.getName() + " (ID: " + course.getId() + ")");
            System.out.println("Enrolled: " + course.getEnrolledStudents().size() + "/" + course.getCapacity());
            System.out.println("--------------------------------------");
        }
    }
}
