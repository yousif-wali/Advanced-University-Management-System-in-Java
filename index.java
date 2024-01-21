import java.util.*;

// Person, Student, Professor, and Course classes remain the same as before

// Interface for entities that can be enrolled in courses
interface Enrollable {
    void enrollInCourse(Course course) throws EnrollmentException;
}

// Custom exception for enrollment
class EnrollmentException extends Exception {
    public EnrollmentException(String message) {
        super(message);
    }
}

// Enhanced Course class
class Course {
    private String courseName;
    private Professor instructor;
    private int courseId;
    private int capacity;
    private List<Student> enrolledStudents;

    public Course(String courseName, int courseId, Professor instructor, int capacity) {
        this.courseName = courseName;
        this.courseId = courseId;
        this.instructor = instructor;
        this.capacity = capacity;
        this.enrolledStudents = new ArrayList<>();
    }

    public void enrollStudent(Student student) throws EnrollmentException {
        if (enrolledStudents.size() >= capacity) {
            throw new EnrollmentException("Course capacity reached");
        }
        enrolledStudents.add(student);
    }

    public void displayCourseInfo() {
        System.out.println("Course: " + courseName + ", ID: " + courseId + ", Instructor: " + instructor.getName());
        System.out.println("Enrolled Students: ");
        for (Student student : enrolledStudents) {
            System.out.println(" - " + student.getName());
        }
    }
}

// Department class
class Department implements Manageable {
    private String departmentName;
    private List<Course> courses;

    public Department(String departmentName) {
        this.departmentName = departmentName;
        this.courses = new ArrayList<>();
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    @Override
    public void manage() {
        System.out.println("Managing Department: " + departmentName);
        for (Course course : courses) {
            course.displayCourseInfo();
        }
    }
}

// Enhanced Student class
class Student extends Person implements Enrollable {
    private String major;
    private List<Course> enrolledCourses;

    public Student(String name, int id, String major) {
        super(name, id);
        this.major = major;
        this.enrolledCourses = new ArrayList<>();
    }

    @Override
    public void enrollInCourse(Course course) throws EnrollmentException {
        course.enrollStudent(this);
        enrolledCourses.add(course);
    }

    @Override
    public void display() {
        super.display();
        System.out.println("Enrolled Courses: ");
        for (Course course : enrolledCourses) {
            System.out.println(" - " + course.courseName);
        }
    }
}

public class UniversityManagement {
    public static void main(String[] args) {
        try {
            Professor prof = new Professor("John Doe", 101, "Computer Science");
            Student student = new Student("Alice Smith", 501, "Computer Science");
            Course course = new Course("Intro to CS", 1101, prof, 30);

            student.enrollInCourse(course);
            Department csDepartment = new Department("Computer Science");
            csDepartment.addCourse(course);

            prof.display();
            student.display();
            course.displayCourseInfo();
            csDepartment.manage();
        } catch (EnrollmentException e) {
            System.out.println("Enrollment Error: " + e.getMessage());
        }
    }
}
