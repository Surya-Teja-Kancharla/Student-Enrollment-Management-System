package data;

import java.io.*;
import java.util.*;

public class DataManager 
{
    private final String STUDENT_FILE = "students.csv";
    private final String COURSE_FILE = "courses.csv";
    private final String ENROLLMENT_FILE = "enrollments.csv";

    public DataManager() 
    {
        createFileIfNotExists(STUDENT_FILE);
        createFileIfNotExists(COURSE_FILE);
        createFileIfNotExists(ENROLLMENT_FILE);
    }

    // Create file if it doesn't exist
    private void createFileIfNotExists(String fileName) 
    {
        try 
        {
            File file = new File(fileName);
            if(!file.exists()) 
            {
                file.createNewFile();
            }
        } 
        catch(IOException e) 
        {
            System.err.println("Error creating file: " + fileName);
        }
    }

    // ------------------- Student Management -------------------

    public void saveStudent(Student student) 
    {
        try(PrintWriter writer = new PrintWriter(new FileWriter(STUDENT_FILE, true))) 
        {
            writer.println(student.getStudentId() + "," + escapeComma(student.getName()) + "," + escapeComma(student.getEmail()));
        } 
        catch (IOException e) 
        {
            System.err.println("Error saving student: " + e.getMessage());
        }
    }

    public Map<String, Student> loadStudents() 
    {
        Map<String, Student> students = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(STUDENT_FILE))) 
        {
            String line;
            while((line = reader.readLine()) != null) 
            {
                String[] parts = parseCSVLine(line);
                if(parts.length == 3) 
                {
                    String studentId = parts[0];
                    String name = parts[1];
                    String email = parts[2];
                    Student student = new Student(studentId, name, email);
                    students.put(studentId, student);
                }
            }
        } 
        catch (IOException e) 
        {
            System.err.println("Error loading students: " + e.getMessage());
        }
        return students;
    }

    public void updateStudent(Student student) 
    {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(STUDENT_FILE))) 
        {
            String line;
            while ((line = reader.readLine()) != null) 
            {
                String[] parts = parseCSVLine(line);
                if (parts[0].equals(student.getStudentId())) 
                {
                    lines.add(student.getStudentId() + "," + escapeComma(student.getName()) + "," + escapeComma(student.getEmail()));
                } 
                else 
                {
                    lines.add(line);
                }
            }
        } 
        catch (IOException e) 
        {
            System.err.println("Error updating student: " + e.getMessage());
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(STUDENT_FILE))) 
        {
            for (String line : lines) 
            {
                writer.println(line);
            }
        } 
        catch (IOException e) 
        {
            System.err.println("Error writing updated student data: " + e.getMessage());
        }
    }

    public void deleteStudent(Student student) 
    {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(STUDENT_FILE))) 
        {
            String line;
            while ((line = reader.readLine()) != null) 
            {
                String[] parts = parseCSVLine(line);
                if (!parts[0].equals(student.getStudentId())) 
                {
                    lines.add(line);
                }
            }
        } 
        catch (IOException e) 
        {
            System.err.println("Error deleting student: " + e.getMessage());
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(STUDENT_FILE))) 
        {
            for (String line : lines) 
            {
                writer.println(line);
            }
        } 
        catch (IOException e) 
        {
            System.err.println("Error writing updated student data: " + e.getMessage());
        }
    }

    // ------------------- Course Management -------------------

    public void saveCourse(Course course) 
    {
        try(PrintWriter writer = new PrintWriter(new FileWriter(COURSE_FILE, true))) 
        {
            writer.println(course.getId() + "," + escapeComma(course.getName()) + "," + course.getCapacity());
        } 
        catch (IOException e) 
        {
            System.err.println("Error saving course: " + e.getMessage());
        }
    }

    public Map<String, Course> loadCourses() 
    {
        Map<String, Course> courses = new HashMap<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(COURSE_FILE))) 
        {
            String line;
            while ((line = reader.readLine()) != null) 
            {
                String[] parts = parseCSVLine(line);
                if(parts.length == 3) 
                {
                    String courseId = parts[0];
                    String name = parts[1];
                    int capacity = Integer.parseInt(parts[2]);
                    Course course = new Course(courseId, name, capacity);
                    courses.put(courseId, course);
                }
            }
        } 
        catch(IOException e) 
        {
            System.err.println("Error loading courses: " + e.getMessage());
        } 
        catch(NumberFormatException e) 
        {
            System.err.println("Invalid number format in courses.csv: " + e.getMessage());
        }
        return courses;
    }

    public void updateCourse(Course course) 
    {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(COURSE_FILE))) 
        {
            String line;
            while ((line = reader.readLine()) != null) 
            {
                String[] parts = parseCSVLine(line);
                if (parts[0].equals(course.getId())) 
                {
                    lines.add(course.getId() + "," + escapeComma(course.getName()) + "," + course.getCapacity());
                } 
                else 
                {
                    lines.add(line);
                }
            }
        } 
        catch (IOException e) 
        {
            System.err.println("Error updating course: " + e.getMessage());
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(COURSE_FILE))) 
        {
            for (String line : lines) 
            {
                writer.println(line);
            }
        } 
        catch (IOException e) 
        {
            System.err.println("Error writing updated course data: " + e.getMessage());
        }
    }

    public void deleteCourse(Course course) 
    {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(COURSE_FILE))) 
        {
            String line;
            while ((line = reader.readLine()) != null) 
            {
                String[] parts = parseCSVLine(line);
                if (!parts[0].equals(course.getId())) 
                {
                    lines.add(line);
                }
            }
        } 
        catch (IOException e) 
        {
            System.err.println("Error deleting course: " + e.getMessage());
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(COURSE_FILE))) 
        {
            for (String line : lines) 
            {
                writer.println(line);
            }
        } 
        catch (IOException e) 
        {
            System.err.println("Error writing updated course data: " + e.getMessage());
        }
    }

    // ------------------- Enrollment Management -------------------

    public void saveEnrollment(String studentId, String courseId) 
    {
        try(PrintWriter writer = new PrintWriter(new FileWriter(ENROLLMENT_FILE, true))) 
        {
            writer.println(studentId + "," + courseId);
        } 
        catch(IOException e) 
        {
            System.err.println("Error saving enrollment: " + e.getMessage());
        }
    }

    public List<String[]> loadEnrollments() 
    {
        List<String[]> enrollments = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ENROLLMENT_FILE))) 
        {
            String line;
            while((line = reader.readLine()) != null) 
            {
                String[] parts = parseCSVLine(line);
                if(parts.length == 2) 
                {
                    enrollments.add(parts);
                }
            }
        } 
        catch(IOException e) 
        {
            System.err.println("Error loading enrollments: " + e.getMessage());
        }
        return enrollments;
    }

    public void deleteCourse(String studentId, String courseId) 
    {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ENROLLMENT_FILE))) 
        {
            String line;
            while ((line = reader.readLine()) != null) 
            {
                String[] parts = parseCSVLine(line);
                if (!parts[0].equals(studentId) || !parts[1].equals(courseId)) 
                {
                    lines.add(line);
                }
            }
        } 
        catch (IOException e) 
        {
            System.err.println("Error deleting enrollment: " + e.getMessage());
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(ENROLLMENT_FILE))) 
        {
            for (String line : lines) 
            {
                writer.println(line);
            }
        } 
        catch (IOException e) 
        {
            System.err.println("Error writing updated enrollment data: " + e.getMessage());
        }
    }

    public void deleteEnrollment(String studentId, String courseId) 
    {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ENROLLMENT_FILE))) 
        {
            String line;
            while ((line = reader.readLine()) != null) 
            {
                String[] parts = parseCSVLine(line);
                if (!parts[0].equals(studentId) || !parts[1].equals(courseId)) 
                {
                    lines.add(line);
                }
            }
        } 
        catch (IOException e) 
        {
            System.err.println("Error deleting enrollment: " + e.getMessage());
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(ENROLLMENT_FILE))) 
        {
            for (String line : lines) 
            {
                writer.println(line);
            }
        } 
        catch (IOException e) 
        {
            System.err.println("Error writing updated enrollment data: " + e.getMessage());
        }
    }

    // ------------------- Helper Methods -------------------

    private String escapeComma(String field) 
    {
        if(field.contains(",")) 
        {
            return "\"" + field.replace("\"", "\"\"") + "\"";
        }
        return field;
    }

    private String[] parseCSVLine(String line) 
    {
        List<String> tokens = new ArrayList<>();
        boolean inQuotes = false;
        StringBuilder sb = new StringBuilder();

        for(char c : line.toCharArray()) 
        {
            if(c == '\"') 
            {
                inQuotes = !inQuotes;
                if(inQuotes && sb.length() > 0 && sb.charAt(sb.length() - 1) == '\"') 
                {
                    sb.append('\"');
                }
            } 
            else if(c == ',' && !inQuotes) 
            {
                tokens.add(sb.toString().trim());
                sb.setLength(0);
            } 
            else 
            {
                sb.append(c);
            }
        }
        tokens.add(sb.toString().trim());
        return tokens.toArray(new String[0]);
    }
}
