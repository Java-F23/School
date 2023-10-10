public class Course {
    private String title;

    private String subject;

    private String instructor;

    private String level;

    private String content;
    private String department;

    public Course(String title, String subject, String instructor, String level, String content,String department) {
        this.title = title;
        this.subject = subject;
        this.instructor = instructor;
        this.level = level;
        this.content = content;
        this.department = department;
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

    @Override
    public String toString() {
        return title;
    }
}


