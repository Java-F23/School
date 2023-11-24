public class InstructorModel {
    private String name;
    private String course;

    // Constructor
    public InstructorModel(String name) {
        this.name = name;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getCourse() {
        return course;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "InstructorModel{" +
                "name='" + name + '\'' +
                ", course='" + course + '\'' +
                '}';
    }
}
