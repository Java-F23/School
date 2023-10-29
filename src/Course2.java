import java.util.ArrayList;

public class Course2 extends CommonData {
    private String title;
    private String subject;
    private Department department;
    private ArrayList<WorkingDays> days;
    private Time time;
    private Level level;
    private String instructor;
    private String content;

    public Course2(String title, String subject, Department department, ArrayList<WorkingDays> days, Time time, Level level, String instructor, String content) {
        this.title = title;
        this.subject = subject;
        this.department = department;
        this.days = days;
        this.time = time;
        this.level = level;
        this.instructor = instructor;
        this.content = content;
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
}

