import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;


public class Course2 extends CommonData {
    private String title;
    private String subject;
    private Department department;
    private ArrayList<WorkingDays> days;
    private Time time;
    private Level level;
    private String instructor;
    private String content;
    private ArrayList<String> enrolledStudents;
    private ArrayList<String> students = new ArrayList<>();
    private ArrayList<String> grades = new ArrayList<>();
    private ArrayList<Assignment> assignments = new ArrayList<>();

    public Course2(String title, String subject, Department department, ArrayList<WorkingDays> days, Time time, Level level, String instructor, String content) {
        this.title = title;
        this.subject = subject;
        this.department = department;
        this.days = days;
        this.time = time;
        this.level = level;
        this.instructor = instructor;
        this.content = content;
        this.enrolledStudents = new ArrayList<>();
        enrolledStudents.add("Ahmed"); //for testing
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void setDays(ArrayList<WorkingDays> days) {
        this.days = days;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public void enrollStudent(String studentName) {
        enrolledStudents.add(studentName);
    }

    public ArrayList<Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(ArrayList<Assignment> assignments) {
        this.assignments = assignments;
    }
    public String getTitle() {
        return title;
    }

    public String getSubject() {
        return subject;
    }

    public Department getDepartment() {
        return department;
    }

    public ArrayList<WorkingDays> getDays() {
        return days;
    }

    public Time getTime() {
        return time;
    }

    public Level getLevel() {
        return level;
    }

    public String getInstructor() {
        return instructor;
    }

    public String getContent() {
        return content;
    }
    public ArrayList<String> getEnrolledStudents() {
        return enrolledStudents;
    }

    // Add a student and their grade
    public void addStudentGrade(String studentName,String letterGrade) {
        String grade;
        //Intial value for the grade
        if (letterGrade == null)
        {
            grade = "A";
        }
        else
        {
            grade = letterGrade;
        }
        students.add(studentName);
        grades.add(grade);
    }

    // Retrieve the grade for a specific student
    public String getGradeForStudent(String studentName) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).equals(studentName)) {
                return grades.get(i);
            }
        }
        // Return null if the student is not found
        return null;
    }

    // Add an assignment to the course
    public void addAssignment(String name, String description, String dueDateStr) {
        // You can add validation for the assignment details here
        if (name == null || name.isEmpty() || description == null || description.isEmpty() || dueDateStr == null || dueDateStr.isEmpty()) {
            throw new IllegalArgumentException("Invalid assignment details.");
        }

        // You can replace this with a custom date parsing logic
        Date dueDate = parseDate(dueDateStr);

        Assignment newAssignment = new Assignment(name, description, dueDate);
        assignments.add(newAssignment);
    }

    // Parse a date string into a Date object
    private Date parseDate(String dateStr) {
        try {
            // Replace this with a custom date parsing logic
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return dateFormat.parse(dateStr);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date format");
        }
    }

}

