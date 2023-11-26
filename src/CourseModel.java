import java.util.ArrayList;
import java.util.HashMap;
import java.math.BigDecimal;
import java.util.List;

public class CourseModel {
    private String courseTitle;
    private String courseSubject;
    private String department;
    private InstructorModel instructor;
    private String content;
    private int level;
    private Schedule schedule;
    private ArrayList<Assignment> assignments;
    private HashMap<String, BigDecimal> grades; // HashMap to store grades with student names
    private ArrayList<Student> enrolledStudents;

    // Constructor
    public CourseModel(String courseTitle, String courseSubject, String department, InstructorModel instructor,
                         String content, int level, Schedule  schedule) {
        this.courseTitle = courseTitle;
        this.courseSubject = courseSubject;
        this.department = department;
        this.instructor = instructor;
        this.content = content;
        this.level = level;
        this.schedule = schedule;
        this.assignments = new ArrayList<>();
        this.grades = new HashMap<>();
        this.enrolledStudents = new ArrayList<>();
    }

    // Getters for attributes
    public String getCourseTitle() {
        return courseTitle;
    }

    public String getCourseSubject() {
        return courseSubject;
    }

    public String getDepartment() {
        return department;
    }

    public InstructorModel getInstructor() {
        return instructor;
    }

    public String getContent() {
        return content;
    }

    public int getLevel() {
        return level;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public ArrayList<Assignment> getAssignments() {
        return assignments;
    }

    public HashMap<String, BigDecimal> getGrades() {
        return grades;
    }

    public ArrayList<Student> getEnrolledStudents() {
        return enrolledStudents;
    }


    //Setters
    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public void setCourseSubject(String courseSubject) {
        this.courseSubject = courseSubject;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setInstructor(InstructorModel instructor) {
        this.instructor = instructor;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setSchedule(Schedule  schedule) {
        this.schedule = schedule;
    }

    // Methods to modify the state
    public void addAssignment(Assignment assignment) {
        assignments.add(assignment);
    }

    public void removeAssignment(Assignment assignment) {
        assignments.remove(assignment);
    }

    public void enrollStudent(Student student) {
        enrolledStudents.add(student);
    }
    public void removeStudent(Student student) {
        enrolledStudents.remove(student);
    }
    public void assignGrade(Student student, BigDecimal grade) {
        grades.put(student.getName(), grade);
    }

    // Static method to get a course by title
    public static CourseModel getCourseByTitle(List<CourseModel> courses, String courseTitle) throws ExceptionHandling.CourseNotFoundException {
        CourseModel course = courses.stream()
                .filter(c -> c.getCourseTitle().equals(courseTitle))
                .findFirst()
                .orElse(null);

        if (course == null) {
            throw new ExceptionHandling.CourseNotFoundException("Course not found: " + courseTitle);
        }

        return course;
    }
    @Override
    public String toString() {
        return "CourseControl{" +
                "courseTitle='" + courseTitle + '\'' +
                ", courseSubject='" + courseSubject + '\'' +
                ", department='" + department + '\'' +
                ", instructor=" + instructor +
                ", content='" + content + '\'' +
                ", level=" + level +
                ", schedule='" + schedule + '\'' +
                ", assignments=" + assignments +
                ", grades=" + grades +
                ", enrolledStudents=" + enrolledStudents +
                '}';
    }
}
