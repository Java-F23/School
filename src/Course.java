import java.time.LocalTime;
import java.util.ArrayList;

public class Course {
    private String title;

    private String subject;

    private String instructor;

    private String level;

    private String content;
    private String department;

    private String day;
    private LocalTime time;
    private ArrayList<String> assignments;

    public Course(String title, String subject, String instructor, String level, String content,String department, String day, LocalTime time) {
        this.title = title;
        this.subject = subject;
        this.instructor = instructor;
        this.level = level;
        this.content = content;
        this.department = department;
        this.day = day;
        this.time = time;
        assignments = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public String getSubject() {
        return subject;
    }

    public String getInstructor() {
        return instructor;
    }

    public String getLevel() {
        return level;
    }

    public String getContent() {
        return content;
    }

    public String getDepartment() {
        return department;
    }
    public String getDay() {
        return day;
    }
    public LocalTime getTime() {
        return time;
    }


    public ArrayList<String> getAssignments() {
        return assignments;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }
    public void setLevel(String level) {
        this.level = level;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDepartment(String department) {
        this.department = department;
    }


    public void setDay(String day) {
        this.day = day;
    }


    public void setTime(LocalTime time) {
        this.time = time;
    }

    public void setAssignments(String assignments) {
        getAssignments().add(assignments);
    }


    @Override
    public String toString() {
        return title;
    }
}


